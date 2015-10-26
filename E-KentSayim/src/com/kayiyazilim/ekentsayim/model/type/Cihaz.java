package com.kayiyazilim.ekentsayim.model.type;

public enum Cihaz {
	
	MASA("Masa"),SANDALYE("Sandalye"),DOLAP("Dolap"),YANGINTUPU("Yang�n T�p�"),
	KAMERA("Kamera"),NVR("NVR"),GUCUNITESI("G�� �nitesi"),SWITCH("Switch"),
	NOTEBOOK("NoteBook"),MASAUSTUPC("Masa�st� PC"),MONITOR("Monit�r"),
	CCTVSISTEMI("CCTV Sistemi"),ALARMSISTEMI("Alarm Sistemi"),KLIMA("Klima"),
	TV("TV"),PARASAYMACIHAZI("Para Sayma Cihaz�"),SAHTEPARADEDEKTORU("Sahte Para Dedekt�r�"),
	YAZARKASA("Yazar Kasa"),KARTSAYICI("Kart Say�c�");

	private String displayName;

	private Cihaz(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
