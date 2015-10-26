package com.kayi.qrvale.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Hareket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3696744822613655415L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@ManyToOne
	private Arac arac;

	@Column
	private Date alisTarihi;

	@ManyToOne
	private Vale alanVale;

	@Column
	private Date istekTarihi;

	@Column
	private String istekTerminal;

	@Column
	private Date verisTarihi;

	@ManyToOne
	private Vale verenVale;

	@Column
	private Date tamamlanmaTarihi;

	@ManyToOne
	private Isletme isletme;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Arac getArac() {
		return arac;
	}

	public void setArac(Arac arac) {
		this.arac = arac;
	}

	public Date getAlisTarihi() {
		return alisTarihi;
	}

	public void setAlisTarihi(Date alisTarihi) {
		this.alisTarihi = alisTarihi;
	}

	public Vale getAlanVale() {
		return alanVale;
	}

	public void setAlanVale(Vale alanVale) {
		this.alanVale = alanVale;
	}

	public Date getIstekTarihi() {
		return istekTarihi;
	}

	public void setIstekTarihi(Date istekTarihi) {
		this.istekTarihi = istekTarihi;
	}

	public String getIstekTerminal() {
		return istekTerminal;
	}

	public void setIstekTerminal(String istekTerminal) {
		this.istekTerminal = istekTerminal;
	}

	public Date getVerisTarihi() {
		return verisTarihi;
	}

	public void setVerisTarihi(Date verisTarihi) {
		this.verisTarihi = verisTarihi;
	}

	public Vale getVerenVale() {
		return verenVale;
	}

	public void setVerenVale(Vale verenVale) {
		this.verenVale = verenVale;
	}

	public Date getTamamlanmaTarihi() {
		return tamamlanmaTarihi;
	}

	public void setTamamlanmaTarihi(Date tamamlanmaTarihi) {
		this.tamamlanmaTarihi = tamamlanmaTarihi;
	}

	public Isletme getIsletme() {
		return isletme;
	}

	public void setIsletme(Isletme isletme) {
		this.isletme = isletme;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Hareket other = (Hareket) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
