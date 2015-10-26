package com.kayiyazilim.ekentsayim.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.kayiyazilim.ekentsayim.dao.BolgeDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.NcRouterDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.NcRouter;
import com.kayiyazilim.ekentsayim.model.search.NcRouterSearch;
import com.kayiyazilim.ekentsayim.model.type.Durum;

@ManagedBean(name = "ncRouterService")
@ViewScoped
public class NcRouterService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5291907220340255921L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private NcRouterDao dao;

	private NcRouter ncRouter = new NcRouter();

	private List<Kullanici> kullaniciList;

	private NcRouterSearch ncRouterSearch = new NcRouterSearch();

	private List<Bolge> bolgeList;

	private List<NcRouter> ncRouterList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	@PostConstruct
	public void init() {
		ncRouterList = dao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();
	}

	public void ara() {
		ncRouterList = dao.search(ncRouterSearch);
	}

	public void kaydet() {
		ncRouter.setGununTarihi(new Date());
		dao.persist(ncRouter);
		if (ncRouterList == null)
			ncRouterList = new ArrayList<NcRouter>();
		ncRouterList.add(ncRouter);
		ncRouter = new NcRouter();
	}

	public void guncelle(RowEditEvent event) {
		ncRouter = ((NcRouter) event.getObject());
		dao.merge(ncRouter);
		ncRouter = new NcRouter();
	}

	public void sil() {
		if (ncRouter != null)
			dao.remove(ncRouter);
		ncRouterList.remove(ncRouter);
		ncRouter = new NcRouter();
	}

	public NcRouter getNcRouter() {
		return ncRouter;
	}

	public void setNcRouter(NcRouter ncRouter) {
		this.ncRouter = ncRouter;
	}

	public NcRouterSearch getNcRouterSearch() {
		return ncRouterSearch;
	}

	public void setNcRouterSearch(NcRouterSearch ncRouterSearch) {
		this.ncRouterSearch = ncRouterSearch;
	}

	public List<NcRouter> getNcRouterList() {
		return ncRouterList;
	}

	public void setNcRouterList(List<NcRouter> ncRouterList) {
		this.ncRouterList = ncRouterList;
	}

	public List<Kullanici> getKullaniciList() {
		return kullaniciList;
	}

	public void setKullaniciList(List<Kullanici> kullaniciList) {
		this.kullaniciList = kullaniciList;
	}

	public List<Bolge> getBolgeList() {
		return bolgeList;
	}

	public void setBolgeList(List<Bolge> bolgeList) {
		this.bolgeList = bolgeList;
	}

}
