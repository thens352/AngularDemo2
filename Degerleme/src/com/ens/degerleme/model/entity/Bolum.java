package com.ens.degerleme.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Bolum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1588999722814000024L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private String name;

	private int sira;

	@ManyToOne
	private Form form;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "bolum", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("siraNo")
	private List<Soru> soruListesi = new ArrayList<Soru>();

	@Transient
	private List<Cevap> cevapListesi = new ArrayList<Cevap>();

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getSira() {
		return sira;
	}

	public Form getForm() {
		return form;
	}

	public List<Soru> getSoruListesi() {
		return soruListesi;
	}

	public List<Cevap> getCevapListesi() {
		return cevapListesi;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSira(int sira) {
		this.sira = sira;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public void setSoruListesi(List<Soru> soruListesi) {
		this.soruListesi = soruListesi;
	}

	public void setCevapListesi(List<Cevap> cevapListesi) {
		this.cevapListesi = cevapListesi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cevapListesi == null) ? 0 : cevapListesi.hashCode());
		result = prime * result + ((form == null) ? 0 : form.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + sira;
		result = prime * result
				+ ((soruListesi == null) ? 0 : soruListesi.hashCode());
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
		Bolum other = (Bolum) obj;
		if (cevapListesi == null) {
			if (other.cevapListesi != null)
				return false;
		} else if (!cevapListesi.equals(other.cevapListesi))
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sira != other.sira)
			return false;
		if (soruListesi == null) {
			if (other.soruListesi != null)
				return false;
		} else if (!soruListesi.equals(other.soruListesi))
			return false;
		return true;
	}

}