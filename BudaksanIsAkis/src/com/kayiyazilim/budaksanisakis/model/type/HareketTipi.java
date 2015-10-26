package com.kayiyazilim.budaksanisakis.model.type;


public enum HareketTipi {
	IS("Iþ"),BAKIM("Bakým");

	private String displayName;

	private HareketTipi(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
