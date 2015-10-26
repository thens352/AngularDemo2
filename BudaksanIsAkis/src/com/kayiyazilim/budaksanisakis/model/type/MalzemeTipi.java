package com.kayiyazilim.budaksanisakis.model.type;


public enum MalzemeTipi {
	ELEKTRIK("Elektrik"),INSAAT("Ýnþaat");

	private String displayName;

	private MalzemeTipi(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
