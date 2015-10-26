package com.kayi.qrvale.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.kayi.qrvale.dao.AracDao;
import com.kayi.qrvale.dao.HareketDao;
import com.kayi.qrvale.dao.IsletmeDao;
import com.kayi.qrvale.dao.ValeDao;
import com.kayi.qrvale.model.entity.Arac;
import com.kayi.qrvale.model.entity.Hareket;
import com.kayi.qrvale.model.entity.Isletme;
import com.kayi.qrvale.model.entity.Vale;

@ManagedBean(name = "hareketService")
@ViewScoped
public class HareketService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4458189078014263357L;

	@EJB
	private HareketDao hareketDao;

	@EJB
	private AracDao aracDao;

	@EJB
	private ValeDao valeDao;

	@EJB
	private IsletmeDao isletmeDao;

	private Hareket hareket = new Hareket();

	private Arac arac = new Arac();

	private Isletme isletme;

	private List<Arac> aracList = new ArrayList<Arac>();

	private List<Vale> valeList = new ArrayList<Vale>();

	public void hareketKaydet() {
		hareket.setIsletme(isletme);
		if (hareket.getId() == null) {
			hareketDao.hareketKaydet(hareket);
		} else {
			hareket = hareketDao.hareketGuncelle(hareket);
		}
	}

	@PostConstruct
	public void init() {
		try {
			isletme = isletmeDao.IsletmeGetirById((Integer) FacesContext
					.getCurrentInstance().getExternalContext().getSessionMap()
					.get("isletmeID"));
		} catch (Exception e) {
			LoginService.sessionDoldu();
		}
		aracList = aracDao.AracGetirByIsletmeId(isletme.getId());
		valeList = valeDao.valeGetirByIsletmeId(isletme.getId());

		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String hareketId = params.get("hareketId");
		if (hareketId != null && !"".equals(hareketId)) {
			hareket = hareketDao.HareketGetirById(Integer.valueOf(hareketId));
			arac = hareket.getArac();
		}
	}

	public Hareket getHareket() {
		return hareket;
	}

	public void setHareket(Hareket hareket) {
		this.hareket = hareket;
	}

	public Arac getArac() {
		return arac;
	}

	public void setArac(Arac arac) {
		this.arac = arac;
	}

	public List<Arac> getAracList() {
		return aracList;
	}

	public void setAracList(List<Arac> aracList) {
		this.aracList = aracList;
	}

	public List<Vale> getValeList() {
		return valeList;
	}

	public void setValeList(List<Vale> valeList) {
		this.valeList = valeList;
	}

	public Isletme getIsletme() {
		return isletme;
	}

	public void setIsletme(Isletme isletme) {
		this.isletme = isletme;
	}
}
