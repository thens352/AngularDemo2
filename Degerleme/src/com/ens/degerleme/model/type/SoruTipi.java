package com.ens.degerleme.model.type;

public enum SoruTipi {
	TEXT_SORU("Text Soru"), COKTAN_SECMELI_SORU("Çoktan Seçmeli Soru"), COKTAN_COK_SECMELI_SORU(
			"Çoktan Çok Seçmeli Soru"), BOSLUK_DOLDURMALI_SORU(
			"Boþluk Doldurmalý Soru"), COKTAN_SECMELI_BOSLUK_DOLDURMALI_SORU(
			"Çoktan Seçmeli Boþluk Doldurmalý Soru"), COKTAN_COK_SECMELI_TEXT_SORU(
			"Çoktan Çok Seçmeli Text Soru");

	private String i18nKey;

	private SoruTipi(String i18nKey) {
		this.i18nKey = i18nKey;
	}

	public void setI18nKey(String i18nKey) {
		this.i18nKey = i18nKey;
	}

	public String getI18nKey() {
		return i18nKey;
	}
}
