package com.kayi.qrvale.service;

import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.kayi.qrvale.dao.HareketDao;
import com.kayi.qrvale.model.entity.Hareket;

@ManagedBean(name = "istekService")
@ViewScoped
public class IstekService {

	@EJB
	private HareketDao hareketDao;

	private Hareket hareket;

	@PostConstruct
	public void init() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		if (id != null && !"".equals(id))
			hareket = hareketDao.HareketGetirById(Integer.parseInt(id));
	}

	public void teslimIstek() {
		if (hareket != null && hareket.getIstekTarihi() == null) {
			hareket.setIstekTarihi(new Date());
			hareketDao.hareketGuncelle(hareket);
		}
	}

	public Hareket getHareket() {
		return hareket;
	}

	public void setHareket(Hareket hareket) {
		this.hareket = hareket;
	}
}
