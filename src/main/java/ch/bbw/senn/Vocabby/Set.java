package ch.bbw.senn.Vocabby;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Set {

	private UUID id;
	private UUID id_fk;
	private String name;
	private String theme;
	private LocalDate creationDate;
	private List<Term> terms;

	public Set(UUID id_fk, String name, String theme, LocalDate creationDate, List<Term> terms) {
		super();
		this.id_fk = id_fk;
		this.name = name;
		this.creationDate = creationDate;
		this.terms = terms;
		this.theme = theme;
		this.id = UUID.randomUUID();
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

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public void addTerm(Term set) {
		this.terms.add(set);
	}

	public void removeTerm(Term set) {
		this.terms.remove(set);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getId_fk() {
		return id_fk;
	}

	public void setId_fk(UUID id_fk) {
		this.id_fk = id_fk;
	}

}
