package com.kayi.qrvale.model.type;

public enum Renk {
	RENK1("Renk 1");

	private String displayName;

	private Renk(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
