package com.kayi.qrvale.dao.search;

import java.util.Date;

import com.kayi.qrvale.model.entity.Arac;
import com.kayi.qrvale.model.entity.Isletme;
import com.kayi.qrvale.model.entity.Vale;

public class HareketSearch {

	private Arac arac;

	private Date minAlisTarihi;

	private Date maxAlisTarihi;

	private Vale alanVale;

	private Date minIstekTarihi;

	private Date maxIstekTarihi;

	private Date minVerisTarihi;

	private Date maxVerisTarihi;

	private Vale verenVale;

	private Date minTamamlanmaTarihi;

	private Date maxTamamlanmaTarihi;

	private Isletme isletme;

	public Arac getArac() {
		return arac;
	}

	public void setArac(Arac arac) {
		this.arac = arac;
	}

	public Vale getAlanVale() {
		return alanVale;
	}

	public void setAlanVale(Vale alanVale) {
		this.alanVale = alanVale;
	}

	public Date getMinIstekTarihi() {
		return minIstekTarihi;
	}

	public void setMinIstekTarihi(Date minIstekTarihi) {
		this.minIstekTarihi = minIstekTarihi;
	}

	public Date getMaxIstekTarihi() {
		return maxIstekTarihi;
	}

	public void setMaxIstekTarihi(Date maxIstekTarihi) {
		this.maxIstekTarihi = maxIstekTarihi;
	}

	public Vale getVerenVale() {
		return verenVale;
	}

	public void setVerenVale(Vale verenVale) {
		this.verenVale = verenVale;
	}

	public Isletme getIsletme() {
		return isletme;
	}

	public void setIsletme(Isletme isletme) {
		this.isletme = isletme;
	}

	public Date getMinAlisTarihi() {
		return minAlisTarihi;
	}

	public void setMinAlisTarihi(Date minAlisTarihi) {
		this.minAlisTarihi = minAlisTarihi;
	}

	public Date getMaxAlisTarihi() {
		return maxAlisTarihi;
	}

	public void setMaxAlisTarihi(Date maxAlisTarihi) {
		this.maxAlisTarihi = maxAlisTarihi;
	}

	public Date getMinVerisTarihi() {
		return minVerisTarihi;
	}

	public void setMinVerisTarihi(Date minVerisTarihi) {
		this.minVerisTarihi = minVerisTarihi;
	}

	public Date getMaxVerisTarihi() {
		return maxVerisTarihi;
	}

	public void setMaxVerisTarihi(Date maxVerisTarihi) {
		this.maxVerisTarihi = maxVerisTarihi;
	}

	public Date getMinTamamlanmaTarihi() {
		return minTamamlanmaTarihi;
	}

	public void setMinTamamlanmaTarihi(Date minTamamlanmaTarihi) {
		this.minTamamlanmaTarihi = minTamamlanmaTarihi;
	}

	public Date getMaxTamamlanmaTarihi() {
		return maxTamamlanmaTarihi;
	}

	public void setMaxTamamlanmaTarihi(Date maxTamamlanmaTarihi) {
		this.maxTamamlanmaTarihi = maxTamamlanmaTarihi;
	}
}
