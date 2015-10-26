package com.kayiyazilim.ekentsayim.model.type;

public enum Ekran {

	e1("Validatör Otobüs Tipi"),e2("Validatör Turnike Tipi"), e3("Kiosk"), e4("Araç İçi Ekran"), e5("Ks Kamera"), e6("Ks NVR") ,
	e7("Ks Güç Ünitesi"),e8("Ks Switch"), e9(
			"Otobüs Güzergah Panosu"), e10("Araç Takip Sistemi"), e11(
			"Akıllı Durak"), e12("Parkomat"), e13("Müfettiş El Terminali"), e14(
			"Turnike"), e15("POS"), e16("UPS"), e17("Sunucu"), e18("LCD"), e19(
			"Kabinet"), e20("Nc Router"), e21("Nc Modem"), e22("Nc Switch"), e23(
			"Printer/Scanner/Kart Yazıcısı"), e24(
			"Noteebook/Masaüstü PC/Monitör"), e25("Cep Telefonu"), e26(
			"Telefon Santrali"), e27("Masa/Sandalye/Dolap/Yangın Tüpü"), e28(
			"Masa Telefonu"), e29(
			"CCTV Sistemi/Alarm Sistemi/Klima/TV/Para Sayma Cihazı/Sahte Para Dedektörü/Yazarkasa/Kart Sayıcı"), e30(
			"Personel Devam Kontrol Sistemi");

	private String displayName;

	private Ekran(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
