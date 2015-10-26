package com.kayiyazilim.ekentsayim.model.type;

public enum Statu {

	PASIF("Pasif"),AKTIF("Aktif");
	
	private String displayName;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	private Statu(String displayName) {
		this.displayName = displayName;
	}
}
