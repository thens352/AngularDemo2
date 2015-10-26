package com.ens.degerleme.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Banka implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4955917534397671018L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String name;
	private String email;
	private String ilgiliKisi;

	@OneToMany(mappedBy = "banka")
	private List<BankaSubesi> bankaSubesiList = new ArrayList<BankaSubesi>();

	@OneToMany(mappedBy = "banka")
	private List<IsEmri> isemriList = new ArrayList<IsEmri>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIlgiliKisi() {
		return ilgiliKisi;
	}

	public void setIlgiliKisi(String ilgiliKisi) {
		this.ilgiliKisi = ilgiliKisi;
	}

	public List<BankaSubesi> getBankaSubesiList() {
		return bankaSubesiList;
	}

	public void setBankaSubesiList(List<BankaSubesi> bankaSubesiList) {
		this.bankaSubesiList = bankaSubesiList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((ilgiliKisi == null) ? 0 : ilgiliKisi.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Banka other = (Banka) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ilgiliKisi == null) {
			if (other.ilgiliKisi != null)
				return false;
		} else if (!ilgiliKisi.equals(other.ilgiliKisi))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public List<IsEmri> getIsemriList() {
		return isemriList;
	}

	public void setIsemriList(List<IsEmri> isemriList) {
		this.isemriList = isemriList;
	}

}
