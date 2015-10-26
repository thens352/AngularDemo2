package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ens.degerleme.dao.BankaDao;
import com.ens.degerleme.model.entity.Banka;

@ManagedBean(name = "bankaGirisi")
@ViewScoped
public class BankaGiris implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2952459167848623034L;

	@EJB
	private BankaDao bankaDao;

	private Banka banka = new Banka();

	@PostConstruct
	public void init() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String bankaId = params.get("bankaId");
		if (bankaId != null && !!"".equals(bankaId)) {
			banka = bankaDao.bankaGetir(Integer.valueOf(bankaId));
		}
	}

	public void kaydet() {
		if (banka.getId() == null) {
			bankaDao.bankaKaydet(banka);
		} else {
			banka = bankaDao.bankaGuncelle(banka);
		}
	}

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
	}

}
