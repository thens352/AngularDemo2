package com.kayiyazilim.budaksanisakis.model.type;


public enum HareketTipi {
	IS("I�"),BAKIM("Bak�m");

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
