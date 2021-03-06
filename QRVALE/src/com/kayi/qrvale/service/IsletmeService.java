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

import com.kayi.qrvale.dao.IlDao;
import com.kayi.qrvale.dao.IlceDao;
import com.kayi.qrvale.dao.IsletmeDao;
import com.kayi.qrvale.dao.MahalleDao;
import com.kayi.qrvale.dao.SokakDao;
import com.kayi.qrvale.model.entity.Il;
import com.kayi.qrvale.model.entity.Ilce;
import com.kayi.qrvale.model.entity.Isletme;
import com.kayi.qrvale.model.entity.Mahalle;
import com.kayi.qrvale.model.entity.Sokak;

@ManagedBean(name="isletmeService")
@ViewScoped
public class IsletmeService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6107177726613338186L;

	@EJB
	private IsletmeDao isletmeDao;
	
	private Isletme isletme=new Isletme();
	
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
	
	public void isletmeKaydet()
	{
		if(isletme.getId()==null)
		{
			isletmeDao.IsletmeKaydet(isletme);
		}else
		{
			isletme=isletmeDao.IsletmeGuncelle(isletme);
		}
	}
	
	@PostConstruct
	public void init(){
		ilList=ilDao.hepsiniGetir();

		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String isletmeId = params.get("isletmeId");
		if (isletmeId != null && !"".equals(isletmeId)) {
			isletme = isletmeDao.IsletmeGetirById(Integer.valueOf(isletmeId));
			secilenMahalle=isletme.getSokak().getMahalle();
			secilenIlce=secilenMahalle.getIlce();
			secilenIl=secilenIlce.getIl();
			mahalleSecildi();
			ilceSecildi();
			ilSecildi();
		}
	}
	
	public void ilSecildi() {
		if (secilenIl != null) {
			ilceList = ilceDao.ilceGetirByIlId(secilenIl.getId());
		} else {
			getIsletme().setSokak(null);
			ilceList = new ArrayList<Ilce>();
			mahalleList = new ArrayList<Mahalle>();
			sokakList = new ArrayList<Sokak>();
			secilenIlce = null;
			secilenMahalle = null;
			getIsletme().setSokak(null);
		}
	}
	
	public void ilceSecildi() {
		if (secilenIlce != null) {
			mahalleList = mahalleDao.mahalleGetirByIlceId(secilenIlce.getId());
		} else {
			getIsletme().setSokak(null);
			mahalleList = new ArrayList<Mahalle>();
			sokakList = new ArrayList<Sokak>();
			secilenMahalle = null;
			getIsletme().setSokak(null);
		}
	}

	public void mahalleSecildi() {
		if (secilenMahalle != null) {
			sokakList = sokakDao.sokakGetirByMahalleId(secilenMahalle.getId());
		} else {
			sokakList = new ArrayList<Sokak>();
			getIsletme().setSokak(null);
		}
	}

	public Isletme getIsletme() {
		return isletme;
	}

	public void setIsletme(Isletme isletme) {
		this.isletme = isletme;
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
