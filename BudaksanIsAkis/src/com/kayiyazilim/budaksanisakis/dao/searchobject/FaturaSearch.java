package com.kayiyazilim.budaksanisakis.dao.searchobject;

import java.util.Date;

import com.kayiyazilim.budaksanisakis.model.entity.Proje;

public class FaturaSearch {

	private Date minDuzenlemeTarihi;

	private Date maxDuzenlemeTarihi;

	private Date minFiiliSevkTarihi;

	private Date maxFiiliSevkTarihi;

	private Proje proje;
	
	public Proje getProje() {
		return proje;
	}

	public void setProje(Proje proje) {
		this.proje = proje;
	}

	public Date getMinDuzenlemeTarihi() {
		return minDuzenlemeTarihi;
	}

	public void setMinDuzenlemeTarihi(Date minDuzenlemeTarihi) {
		this.minDuzenlemeTarihi = minDuzenlemeTarihi;
	}

	public Date getMaxDuzenlemeTarihi() {
		return maxDuzenlemeTarihi;
	}

	public void setMaxDuzenlemeTarihi(Date maxDuzenlemeTarihi) {
		this.maxDuzenlemeTarihi = maxDuzenlemeTarihi;
	}

	public Date getMinFiiliSevkTarihi() {
		return minFiiliSevkTarihi;
	}

	public void setMinFiiliSevkTarihi(Date minFiiliSevkTarihi) {
		this.minFiiliSevkTarihi = minFiiliSevkTarihi;
	}

	public Date getMaxFiiliSevkTarihi() {
		return maxFiiliSevkTarihi;
	}

	public void setMaxFiiliSevkTarihi(Date maxFiiliSevkTarihi) {
		this.maxFiiliSevkTarihi = maxFiiliSevkTarihi;
	}

}
