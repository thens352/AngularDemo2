package com.kayi.qrvale.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Il implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6031524564474664400L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(unique = true)
	private String adi;

	@OneToMany(mappedBy = "il", fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Ilce> ilceListesi = new ArrayList<Ilce>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public List<Ilce> getIlceListesi() {
		return ilceListesi;
	}

	public void setIlceListesi(List<Ilce> ilceListesi) {
		this.ilceListesi = ilceListesi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adi == null) ? 0 : adi.hashCode());
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
		Il other = (Il) obj;
		if (adi == null) {
			if (other.adi != null)
				return false;
		} else if (!adi.equals(other.adi))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}

}
