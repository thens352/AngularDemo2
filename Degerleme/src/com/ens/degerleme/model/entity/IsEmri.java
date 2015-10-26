package com.ens.degerleme.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.ens.degerleme.model.type.IsEmriDurumu;

@Entity
public class IsEmri implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4377903232195256317L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Id
	private Integer id;

	@ManyToOne
	private Banka banka;

	@ManyToOne
	private BankaSubesi bankaSubesi;

	@ManyToOne
	private Form form;

	@ManyToOne
	private Kisi kisi;

	@ManyToOne
	private Firma firma;

	@ManyToOne
	private Bina bina;

	private Date tarih = new Date();

	@OneToOne(mappedBy = "isEmri")
	private Rapor rapor;

	@ManyToOne
	private User kontrolEdenUser;

	@ManyToOne
	private User atananUser;

	@Enumerated(EnumType.STRING)
	private IsEmriDurumu isEmriDurumu = IsEmriDurumu.BANKADANYENIGELDI;

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
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

	public Rapor getRapor() {
		return rapor;
	}

	public void setRapor(Rapor rapor) {
		this.rapor = rapor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

	public IsEmriDurumu getIsEmriDurumu() {
		return isEmriDurumu;
	}

	public void setIsEmriDurumu(IsEmriDurumu isEmriDurumu) {
		this.isEmriDurumu = isEmriDurumu;
	}

	public User getAtananUser() {
		return atananUser;
	}

	public void setAtananUser(User atananUser) {
		this.atananUser = atananUser;
	}

	public User getKontrolEdenUser() {
		return kontrolEdenUser;
	}

	public void setKontrolEdenUser(User kontrolEdenUser) {
		this.kontrolEdenUser = kontrolEdenUser;
	}

	public BankaSubesi getBankaSubesi() {
		return bankaSubesi;
	}

	public void setBankaSubesi(BankaSubesi bankaSubesi) {
		this.bankaSubesi = bankaSubesi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((atananUser == null) ? 0 : atananUser.hashCode());
		result = prime * result + ((banka == null) ? 0 : banka.hashCode());
		result = prime * result
				+ ((bankaSubesi == null) ? 0 : bankaSubesi.hashCode());
		result = prime * result + ((bina == null) ? 0 : bina.hashCode());
		result = prime * result + ((firma == null) ? 0 : firma.hashCode());
		result = prime * result + ((form == null) ? 0 : form.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isEmriDurumu == null) ? 0 : isEmriDurumu.hashCode());
		result = prime * result + ((kisi == null) ? 0 : kisi.hashCode());
		result = prime * result
				+ ((kontrolEdenUser == null) ? 0 : kontrolEdenUser.hashCode());
		result = prime * result + ((rapor == null) ? 0 : rapor.hashCode());
		result = prime * result + ((tarih == null) ? 0 : tarih.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IsEmri other = (IsEmri) obj;
		if (atananUser == null) {
			if (other.atananUser != null)
				return false;
		} else if (!atananUser.equals(other.atananUser))
			return false;
		if (banka == null) {
			if (other.banka != null)
				return false;
		} else if (!banka.equals(other.banka))
			return false;
		if (bankaSubesi == null) {
			if (other.bankaSubesi != null)
				return false;
		} else if (!bankaSubesi.equals(other.bankaSubesi))
			return false;
		if (bina == null) {
			if (other.bina != null)
				return false;
		} else if (!bina.equals(other.bina))
			return false;
		if (firma == null) {
			if (other.firma != null)
				return false;
		} else if (!firma.equals(other.firma))
			return false;
		if (form == null) {
			if (other.form != null)
				return false;
		} else if (!form.equals(other.form))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isEmriDurumu != other.isEmriDurumu)
			return false;
		if (kisi == null) {
			if (other.kisi != null)
				return false;
		} else if (!kisi.equals(other.kisi))
			return false;
		if (kontrolEdenUser == null) {
			if (other.kontrolEdenUser != null)
				return false;
		} else if (!kontrolEdenUser.equals(other.kontrolEdenUser))
			return false;
		if (rapor == null) {
			if (other.rapor != null)
				return false;
		} else if (!rapor.equals(other.rapor))
			return false;
		if (tarih == null) {
			if (other.tarih != null)
				return false;
		} else if (!tarih.equals(other.tarih))
			return false;
		return true;
	}

}
