package com.ens.degerleme.dao.searchobject;

import com.ens.degerleme.model.entity.Bolum;
import com.ens.degerleme.model.type.SoruTipi;

public class SoruSearch {

	private Integer id;

	private int siraNo;

	private int parametreSayisi = 0;

	private Bolum bolum;
	
	public SoruTipi[] getSoruTipleri() {
		return SoruTipi.values();
	}

	public int getSiraNo() {
		return siraNo;
	}

	public void setSiraNo(int siraNo) {
		this.siraNo = siraNo;
	}

	public int getParametreSayisi() {
		return parametreSayisi;
	}

	public void setParametreSayisi(int parametreSayisi) {
		this.parametreSayisi = parametreSayisi;
	}

	public Bolum getBolum() {
		return bolum;
	}

	public void setBolum(Bolum bolum) {
		this.bolum = bolum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}