package com.kayi.qrvale.model.type;

public enum Marka {
	MARKA1("Marka 1");

	private String displayName;

	private Marka(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
