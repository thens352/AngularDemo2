package com.ens.degerleme.model.type;


public enum IsEmriDurumu{
	BANKADANYENIGELDI("Bankadan YeniGeldi"),
	UZMANAATANDI("Uzmana Atand�"),
	SAHADAISLEMGORUYOR("Sahada ��lem G�r�yor"),
	KONTROLEGONDERILDI("Kontrole G�nderildi"),
	KONTROLEDILIYOR("Kontrol Ediliyor"),
	RAPORTAMAMLANDI("Rapor Tamamlad�"),
	BANKAYAGONDERILDI("Bankaya G�nderildi");
	
	private String displayName;

	public String getDisplayName() {
		return displayName;
	}

	
	IsEmriDurumu(String name){
		displayName=name;
	}


	
}

