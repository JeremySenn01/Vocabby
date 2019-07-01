package ch.bbw.senn.Vocabby;

import java.util.UUID;

public class Term {

	private UUID id;
	private UUID id_fk;
	private String original;
	private String translated;

	public Term(UUID id, UUID id_fk, String original, String translated) {
		super();
		this.original = original;
		this.translated = translated;
		this.id_fk = id_fk;
		this.id = id;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getTranslated() {
		return translated;
	}

	public void setTranslated(String translated) {
		this.translated = translated;
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

	@Override
	public String toString() {
		return this.original + " => " + this.translated;
	}

}
