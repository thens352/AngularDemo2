package com.ens.degerleme.model.type;

public enum SecenekTipi {
	RADIO("secenekTipi.radio"),
	CHECKBOX("secenekTipi.checkbox"),
	TEXTBOX("secenekTipi.textbox");
	
private String i18nKey;

private SecenekTipi(String i18nKey)
{
	this.i18nKey = i18nKey;
}
public String getI18nKey() {
	return i18nKey;
}
}
