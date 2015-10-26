package com.kayiyazilim.ekentsayim.model.type;

public enum Alan {

	MARKA("Marka"), MODEL("Model"), OZELLIK("Özellik"), ALTTIPI("Alt Tipi"), KULLANIMSEKLI(
			"Kullanım Şekli"), KAMERASISTEMIPARCASI("Kamera Sistemi Parçası"), HIZMETVERENFIRMA(
			"Hizmet Veren Firma"), UZERINDEKIISLETIMSISTEMI(
			"Üzerindeki İşletim Sistemi"), CESIT("Çeşit"), MASATELEFONTIPI(
			"Masa Telefon Tipi");

	private String displayName;

	private Alan(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
