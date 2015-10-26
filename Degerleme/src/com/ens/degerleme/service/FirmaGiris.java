package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ens.degerleme.dao.FirmaDao;
import com.ens.degerleme.dao.IlDao;
import com.ens.degerleme.dao.IlceDao;
import com.ens.degerleme.dao.MahalleDao;
import com.ens.degerleme.dao.SokakDao;
import com.ens.degerleme.model.entity.Firma;
import com.ens.degerleme.model.entity.Il;
import com.ens.degerleme.model.entity.Ilce;
import com.ens.degerleme.model.entity.Mahalle;
import com.ens.degerleme.model.entity.Sokak;

@ManagedBean(name = "firmaGirisi")
@ViewScoped
public class FirmaGiris implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2208009956133807947L;

	@EJB
	private FirmaDao firmaDao;

	private Firma firma = new Firma();

	@EJB
	private IlDao ilDao;

	@EJB
	private IlceDao ilceDao;

	@EJB
	private MahalleDao mahalleDao;

	@EJB
	private SokakDao sokakDao;

	private Il secilenIl = new Il();

	private Ilce secilenIlce = new Ilce();

	private Mahalle secilenMahalle = new Mahalle();

	private List<Il> ilList = new ArrayList<Il>();

	private List<Ilce> ilceList = new ArrayList<Ilce>();

	private List<Mahalle> mahalleList = new ArrayList<Mahalle>();

	private List<Sokak> sokakList = new ArrayList<Sokak>();

	@PostConstruct
	public void init() {
		ilList=ilDao.hepsiniGetir();
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String firmaId = params.get("firmaId");
		if (firmaId != null && !!"".equals(firmaId)) {
			firma = firmaDao.firmaGetir(Integer.valueOf(firmaId));
			secilenMahalle = firma.getSokak().getMahalle();
			secilenIlce = secilenMahalle.getIlce();
			secilenIl = secilenIlce.getIl();
			mahalleSecildi();
			ilceSecildi();
			ilSecildi();
		}
	}
	
	public void ilSecildi() {
		if (secilenIl != null) {
			ilceList = ilceDao.ilceGetirByIlId(secilenIl.getId());
		} else {
			getFirma().setSokak(null);
			ilceList = new ArrayList<Ilce>();
			mahalleList = new ArrayList<Mahalle>();
			sokakList = new ArrayList<Sokak>();
			secilenIlce = null;
			secilenMahalle = null;
			getFirma().setSokak(null);
		}
	}

	public void ilceSecildi() {
		if (secilenIlce != null) {
			mahalleList = mahalleDao.mahalleGetirByIlceId(secilenIlce.getId());
		} else {
			getFirma().setSokak(null);
			mahalleList = new ArrayList<Mahalle>();
			sokakList = new ArrayList<Sokak>();
			secilenMahalle = null;
			getFirma().setSokak(null);
		}
	}

	public void mahalleSecildi() {
		if (secilenMahalle != null) {
			sokakList = sokakDao.sokakGetirByMahalleId(secilenMahalle.getId());
		} else {
			sokakList = new ArrayList<Sokak>();
			getFirma().setSokak(null);
		}
	}


	public void kaydet() {
		getFirma().setAdres(
				getFirma().getSokak().getMahalle().getIlce().getIl().getAdi()
						.toUpperCase()
						+ " / "
						+ getFirma().getSokak().getMahalle().getIlce().getAdi()
						+ " / "
						+ getFirma().getSokak().getMahalle().getAdi()
						+ " / "
						+ getFirma().getSokak().getAdi()
						+ " Kapý No:"
						+ getFirma().getKapiNo()
						+ " Daire No:"
						+ getFirma().getDaireNo());
		if (firma.getId() == null) {
			firmaDao.firmaKaydet(firma);
		} else {
			firma = firmaDao.firmaGuncelle(firma);
		}
	}

	public Firma getFirma() {
		return firma;
	}

	public void setFirma(Firma firma) {
		this.firma = firma;
	}

	public Il getSecilenIl() {
		return secilenIl;
	}

	public void setSecilenIl(Il secilenIl) {
		this.secilenIl = secilenIl;
	}

	public Ilce getSecilenIlce() {
		return secilenIlce;
	}

	public void setSecilenIlce(Ilce secilenIlce) {
		this.secilenIlce = secilenIlce;
	}

	public Mahalle getSecilenMahalle() {
		return secilenMahalle;
	}

	public void setSecilenMahalle(Mahalle secilenMahalle) {
		this.secilenMahalle = secilenMahalle;
	}

	public List<Il> getIlList() {
		return ilList;
	}

	public void setIlList(List<Il> ilList) {
		this.ilList = ilList;
	}

	public List<Ilce> getIlceList() {
		return ilceList;
	}

	public void setIlceList(List<Ilce> ilceList) {
		this.ilceList = ilceList;
	}

	public List<Mahalle> getMahalleList() {
		return mahalleList;
	}

	public void setMahalleList(List<Mahalle> mahalleList) {
		this.mahalleList = mahalleList;
	}

	public List<Sokak> getSokakList() {
		return sokakList;
	}

	public void setSokakList(List<Sokak> sokakList) {
		this.sokakList = sokakList;
	}

}
