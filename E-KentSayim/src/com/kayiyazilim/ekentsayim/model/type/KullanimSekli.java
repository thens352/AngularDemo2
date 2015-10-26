package com.kayiyazilim.ekentsayim.model.type;

public enum KullanimSekli {

	WC("WC"), ENGELLI("Engelli"), ASANSOR("Asansör"), IADECIHAZI("Ýade Cihazý"), KARTKONTROLCIHAZI(
			"Kart Kontrol Cihazý"), TRAMWAY("Tramway"), OTOBUS("Otobüs"), TURNIKE(
			"Turnike");

	private String displayName;

	private KullanimSekli(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
