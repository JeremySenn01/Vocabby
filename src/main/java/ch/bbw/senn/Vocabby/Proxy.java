package ch.bbw.senn.Vocabby;

import java.util.List;
import java.util.Optional;

import ch.bbw.senn.Vocabby.dao.IDao;
import ch.bbw.senn.Vocabby.dao.SetDao;
import ch.bbw.senn.Vocabby.dao.TermDao;
import ch.bbw.senn.Vocabby.dao.UserDao;

public class Proxy {

	private IDao<User> userDao;
	private IDao<Set> setDao;
	private IDao<Term> termDao;
	
	public Proxy() {
		userDao = new UserDao();
		setDao = new SetDao();
		termDao = new TermDao();
	}
	
	public Optional<User> getUser(String username, String password) {
		
		Optional<User> receivedUser = userDao.getByUsernameAndPassword(username, password);
		//Found user with given username and password
		if (receivedUser.isPresent()) {
			User foundUser = receivedUser.get();
			
			//get Sets of User
			List<Set> foundSets = setDao.getAll(foundUser.getId().toString());
			foundUser.setSets(foundSets);
			
			for (Set set : foundSets) {
				List<Term> foundTerms = termDao.getAll(set.getId().toString());
				set.setTerms(foundTerms);
			}
			
			return Optional.of(foundUser);
		}
		
		return Optional.empty();
	}
	
	public boolean createUser(User newUser) {
		return userDao.save(newUser);
	}
	
	public boolean deleteSet(Set set) {
		boolean deletedTerms = termDao.deleteAll(set.getTerms());
		
		if (deletedTerms) {
			return setDao.delete(set);
		}
		return false;
	}
	
	public boolean createSet(Set set, User user) {
		set.setId_fk(user.getId());
		boolean createdSet = setDao.save(set);
			
		if (createdSet) {
			for (Term term : set.getTerms()) {
				term.setId_fk(set.getId());
				termDao.save(term);
			}
		}
		return createdSet;
	}
	
}
