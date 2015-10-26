package com.kayi.qrvale.service;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.kayi.qrvale.dao.IsletmeDao;
import com.kayi.qrvale.dao.ValeDao;
import com.kayi.qrvale.model.entity.Isletme;
import com.kayi.qrvale.model.entity.Vale;

@ManagedBean(name = "valeService")
@ViewScoped
public class ValeService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8031915990553834608L;

	@EJB
	private ValeDao valeDao;

	@EJB
	private IsletmeDao isletmeDao;

	private Vale vale = new Vale();

	private Isletme isletme;

	public void valeKaydet() {
		vale.setIsletme(isletme);
		if (vale.getId() == null) {
			valeDao.valeKaydet(vale);
		} else {
			vale = valeDao.valeGuncelle(vale);
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
		String valeId = params.get("valeId");
		if (valeId != null && !"".equals(valeId)) {
			vale = valeDao.ValeGetirById(Integer.valueOf(valeId));
			isletme = vale.getIsletme();
		}
	}

	public Vale getVale() {
		return vale;
	}

	public void setVale(Vale vale) {
		this.vale = vale;
	}

	public Isletme getIsletme() {
		return isletme;
	}

	public void setIsletme(Isletme isletme) {
		this.isletme = isletme;
	}
}
