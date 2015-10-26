package com.kayiyazilim.ekentsayim.model.type;

public enum KullanimSekli {

	WC("WC"), ENGELLI("Engelli"), ASANSOR("Asans�r"), IADECIHAZI("�ade Cihaz�"), KARTKONTROLCIHAZI(
			"Kart Kontrol Cihaz�"), TRAMWAY("Tramway"), OTOBUS("Otob�s"), TURNIKE(
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
