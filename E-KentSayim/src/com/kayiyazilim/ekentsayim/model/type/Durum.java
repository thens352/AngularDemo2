package com.kayiyazilim.ekentsayim.model.type;

public enum Durum {

	SAHADA("Sahada"), DEPODA("Depoda"), HURDA("Hurda");

	private String displayName;

	private Durum(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
