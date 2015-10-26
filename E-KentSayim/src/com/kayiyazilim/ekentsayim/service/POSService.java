package com.kayiyazilim.ekentsayim.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;

import com.kayiyazilim.ekentsayim.dao.BolgeDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.POSDao;
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.POS;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.search.POSSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "posService")
@ViewScoped
public class POSService implements Serializable {

	private static final long serialVersionUID = 2014039389969914592L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private POSDao posDao;

	private POS pos = new POS();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<POS> posList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> markaList = new ArrayList<SelectItem>();

	private List<SelectItem> modelList = new ArrayList<SelectItem>();

	private POSSearch posSearch = new POSSearch();

	@PostConstruct
	public void init() {
		posList = posDao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e15,
				Alan.MARKA)) {
			markaList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e15,
				Alan.MODEL)) {
			modelList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}
	}

	public void ara() {
		posList = posDao.search(posSearch);
	}

	public void ekle() {
		pos.setGununTarihi(new Date());
		posDao.persist(pos);
		if (posList == null)
			posList = new ArrayList<POS>();
		posList.add(pos);
		FacesMessage mesaj1 = new FacesMessage("Kaydetme İşlemi Tamamlandı", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		pos = new POS();
	}

	public void guncelle(RowEditEvent event) {
		pos = ((POS) event.getObject());
		posDao.merge(pos);
		FacesMessage mesaj1 = new FacesMessage("Güncelleme İşlemi Tamamlandı",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		pos = new POS();
	}

	public void sil() {
		if (pos != null)
			posDao.remove(pos);
		posList.remove(pos);
		FacesMessage mesaj1 = new FacesMessage("Silme işlemi Tamamlandı");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		pos = new POS();

	}

	public List<SelectItem> getMarkaList() {
		return markaList;
	}

	public void setMarkaList(List<SelectItem> markaList) {
		this.markaList = markaList;
	}

	public List<SelectItem> getModelList() {
		return modelList;
	}

	public void setModelList(List<SelectItem> modelList) {
		this.modelList = modelList;
	}

	public POSSearch getPosSearch() {
		return posSearch;
	}

	public void setPosSearch(POSSearch posSearch) {
		this.posSearch = posSearch;
	}

	public POS getPos() {
		return pos;
	}

	public void setPos(POS pos) {
		this.pos = pos;
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

	public List<POS> getPosList() {
		return posList;
	}

	public void setPosList(List<POS> posList) {
		this.posList = posList;
	}

}
