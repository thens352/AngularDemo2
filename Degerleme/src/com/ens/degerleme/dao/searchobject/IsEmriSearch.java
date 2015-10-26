package com.ens.degerleme.dao.searchobject;


import java.util.Date;

import com.ens.degerleme.model.entity.Banka;
import com.ens.degerleme.model.entity.Bina;
import com.ens.degerleme.model.entity.Firma;
import com.ens.degerleme.model.entity.Kisi;
import com.ens.degerleme.model.entity.User;
import com.ens.degerleme.model.type.IsEmriDurumu;

public class IsEmriSearch {

	private Integer id;
	private Banka banka;
	private Kisi kisi;
	private Firma firma;
	private Bina bina;
	private Date tarih;
	private User kontrolEdenUser;
	private User atananUser;
	private IsEmriDurumu isEmriDurumu;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
	}

	public Kisi getKisi() {
		return kisi;
	}

	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
	}

	public Firma getFirma() {
		return firma;
	}

	public void setFirma(Firma firma) {
		this.firma = firma;
	}

	public Bina getBina() {
		return bina;
	}

	public void setBina(Bina bina) {
		this.bina = bina;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

	public User getKontrolEdenUser() {
		return kontrolEdenUser;
	}

	public void setKontrolEdenUser(User kontrolEdenUser) {
		this.kontrolEdenUser = kontrolEdenUser;
	}

	public User getAtananUser() {
		return atananUser;
	}

	public void setAtananUser(User atananUser) {
		this.atananUser = atananUser;
	}

	public IsEmriDurumu getIsEmriDurumu() {
		return isEmriDurumu;
	}

	public void setIsEmriDurumu(IsEmriDurumu isEmriDurumu) {
		this.isEmriDurumu = isEmriDurumu;
	}
}
