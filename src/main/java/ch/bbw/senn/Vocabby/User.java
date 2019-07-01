package ch.bbw.senn.Vocabby;

import java.util.List;
import java.util.UUID;

public class User {

	private UUID id;
	private String username;
	private String password;
	private List<Set> sets;
	
	public User(String username, String password, List<Set> sets, UUID id) {
		super();
		this.username = username;
		this.password = password;
		this.sets = sets;
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Set> getSets() {
		return sets;
	}

	public void setSets(List<Set> sets) {
		this.sets = sets;
	}

	public void addSet(Set set) {
		this.sets.add(set);
	}
	
	public void removeSet(Set set) {
		this.sets.remove(set);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	
}
