package ch.bbw.senn.Vocabby;

import java.time.LocalDate;
import java.util.List;

public class Set {

	private String name;
	private LocalDate creationDate;
	private List<Term> terms;

	public Set(String name, LocalDate creationDate, List<Term> terms) {
		super();
		this.name = name;
		this.creationDate = creationDate;
		this.terms = terms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public List<Term> getTerms() {
		return terms;
	}

	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}

	public void addTerm(Term set) {
		this.terms.add(set);
	}
	
	public void removeTerm(Term set) {
		this.terms.remove(set);
	}
	
}
