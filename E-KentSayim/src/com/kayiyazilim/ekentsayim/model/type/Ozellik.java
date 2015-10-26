package com.kayiyazilim.ekentsayim.model.type;

public enum Ozellik {
	
	MANYETIKLI("Manyetikli"),MANYETIKSIZ("Manyetiksiz");

	private String displayName;

	private Ozellik(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
