package com.kayiyazilim.ekentsayim.model.type;

public enum Cihaz {
	
	MASA("Masa"),SANDALYE("Sandalye"),DOLAP("Dolap"),YANGINTUPU("Yangýn Tüpü"),
	KAMERA("Kamera"),NVR("NVR"),GUCUNITESI("Güç Ünitesi"),SWITCH("Switch"),
	NOTEBOOK("NoteBook"),MASAUSTUPC("Masaüstü PC"),MONITOR("Monitör"),
	CCTVSISTEMI("CCTV Sistemi"),ALARMSISTEMI("Alarm Sistemi"),KLIMA("Klima"),
	TV("TV"),PARASAYMACIHAZI("Para Sayma Cihazý"),SAHTEPARADEDEKTORU("Sahte Para Dedektörü"),
	YAZARKASA("Yazar Kasa"),KARTSAYICI("Kart Sayýcý");

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
