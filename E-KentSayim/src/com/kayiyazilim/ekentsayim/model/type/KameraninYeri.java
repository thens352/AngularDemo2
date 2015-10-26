package com.kayiyazilim.ekentsayim.model.type;

public enum KameraninYeri {
	
	ON("Ön"),ORTA("Orta"),ARKA("Arka");

	private String displayName;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	private KameraninYeri(String displayName) {
		this.displayName = displayName;
	}
	
}
