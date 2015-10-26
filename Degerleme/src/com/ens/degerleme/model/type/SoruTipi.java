package com.ens.degerleme.model.type;

public enum SoruTipi {
	TEXT_SORU("Text Soru"), COKTAN_SECMELI_SORU("�oktan Se�meli Soru"), COKTAN_COK_SECMELI_SORU(
			"�oktan �ok Se�meli Soru"), BOSLUK_DOLDURMALI_SORU(
			"Bo�luk Doldurmal� Soru"), COKTAN_SECMELI_BOSLUK_DOLDURMALI_SORU(
			"�oktan Se�meli Bo�luk Doldurmal� Soru"), COKTAN_COK_SECMELI_TEXT_SORU(
			"�oktan �ok Se�meli Text Soru");

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
