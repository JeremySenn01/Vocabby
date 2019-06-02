package ch.bbw.senn.Vocabby;

public class Term {

	private String original;
	private String translated;

	public Term(String original, String translated) {
		super();
		this.original = original;
		this.translated = translated;
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
	
	@Override
	public String toString() {
		return this.original + " => " + this.translated; 
	}

}
