package com.kayiyazilim.ekentsayim.model.type;

public enum AltTipi {

	OTOBUS("Otob�s"),DIKEY("Dikey");
	
	private String displayName;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	private AltTipi(String displayName) {
		this.displayName = displayName;
	}
	
}
