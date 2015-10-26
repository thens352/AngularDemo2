package com.kayi.qrvale.service;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.kayi.qrvale.dao.AracDao;
import com.kayi.qrvale.dao.IsletmeDao;
import com.kayi.qrvale.model.entity.Arac;
import com.kayi.qrvale.model.entity.Isletme;
import com.kayi.qrvale.model.type.Marka;
import com.kayi.qrvale.model.type.Renk;

@ManagedBean(name = "aracService")
@ViewScoped
public class AracService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2499058762605599533L;

	@EJB
	private AracDao aracDao;

	@EJB
	private IsletmeDao isletmeDao;

	private Arac arac = new Arac();

	private Isletme isletme;

	public Marka[] getMarkalar() {
		return Marka.values();
	}

	public Renk[] getRenkler() {
		return Renk.values();
	}

	public void aracKaydet() {
		arac.setIsletme(isletme);
		if (arac.getId() == null) {
			aracDao.AracKaydet(arac);
		} else {
			arac = aracDao.AracGuncelle(arac);
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
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String aracId = params.get("aracId");
		if (aracId != null && !"".equals(aracId)) {
			arac = aracDao.AracGetirById(Integer.valueOf(aracId));
			isletme = arac.getIsletme();
		}
	}

	public Arac getArac() {
		return arac;
	}

	public void setArac(Arac arac) {
		this.arac = arac;
	}

	public Isletme getIsletme() {
		return isletme;
	}

	public void setIsletme(Isletme isletme) {
		this.isletme = isletme;
	}
}
