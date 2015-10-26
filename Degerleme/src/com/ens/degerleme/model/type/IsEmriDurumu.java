package com.ens.degerleme.model.type;


public enum IsEmriDurumu{
	BANKADANYENIGELDI("Bankadan YeniGeldi"),
	UZMANAATANDI("Uzmana Atandý"),
	SAHADAISLEMGORUYOR("Sahada Ýþlem Görüyor"),
	KONTROLEGONDERILDI("Kontrole Gönderildi"),
	KONTROLEDILIYOR("Kontrol Ediliyor"),
	RAPORTAMAMLANDI("Rapor Tamamladý"),
	BANKAYAGONDERILDI("Bankaya Gönderildi");
	
	private String displayName;

	public String getDisplayName() {
		return displayName;
	}

	
	IsEmriDurumu(String name){
		displayName=name;
	}


	
}

