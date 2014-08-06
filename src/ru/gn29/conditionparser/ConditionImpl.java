package ru.gn29.conditionparser;

public class ConditionImpl implements Condition {
	private String expectedWord;
	
	public ConditionImpl(String expectedWord) {
		super();
		this.expectedWord = expectedWord;
	}

	@Override
	public boolean isSatisfy(String word) {
		return expectedWord.equalsIgnoreCase(word);
	}

}
