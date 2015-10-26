package com.ens.degerleme.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Rapor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1588999722814000024L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date raporTarihi = new Date();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "rapor")
	private List<Cevap> cevapList = new ArrayList<Cevap>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "rapor")
	private List<Eksiklik> eksiklikList = new ArrayList<Eksiklik>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "rapor")
	private List<Onlem> onlemList = new ArrayList<Onlem>();

	@OneToOne
	private IsEmri isEmri;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Cevap> getCevapList() {
		return cevapList;
	}

	public void setCevapList(List<Cevap> cevapList) {
		this.cevapList = cevapList;
	}

	public Date getRaporTarihi() {
		return raporTarihi;
	}

	public void setRaporTarihi(Date raporTarihi) {
		this.raporTarihi = raporTarihi;
	}

	public List<Eksiklik> getEksiklikList() {
		return eksiklikList;
	}

	public void setEksiklikList(List<Eksiklik> eksiklikList) {
		this.eksiklikList = eksiklikList;
	}

	public List<Onlem> getOnlemList() {
		return onlemList;
	}

	public void setOnlemList(List<Onlem> onlemList) {
		this.onlemList = onlemList;
	}

	public IsEmri getIsEmri() {
		return isEmri;
	}

	public void setIsEmri(IsEmri isEmri) {
		this.isEmri = isEmri;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cevapList == null) ? 0 : cevapList.hashCode());
		result = prime * result
				+ ((eksiklikList == null) ? 0 : eksiklikList.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isEmri == null) ? 0 : isEmri.hashCode());
		result = prime * result
				+ ((onlemList == null) ? 0 : onlemList.hashCode());
		result = prime * result
				+ ((raporTarihi == null) ? 0 : raporTarihi.hashCode());
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
		Rapor other = (Rapor) obj;
		if (cevapList == null) {
			if (other.cevapList != null)
				return false;
		} else if (!cevapList.equals(other.cevapList))
			return false;
		if (eksiklikList == null) {
			if (other.eksiklikList != null)
				return false;
		} else if (!eksiklikList.equals(other.eksiklikList))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isEmri == null) {
			if (other.isEmri != null)
				return false;
		} else if (!isEmri.equals(other.isEmri))
			return false;
		if (onlemList == null) {
			if (other.onlemList != null)
				return false;
		} else if (!onlemList.equals(other.onlemList))
			return false;
		if (raporTarihi == null) {
			if (other.raporTarihi != null)
				return false;
		} else if (!raporTarihi.equals(other.raporTarihi))
			return false;
		return true;
	}
}
