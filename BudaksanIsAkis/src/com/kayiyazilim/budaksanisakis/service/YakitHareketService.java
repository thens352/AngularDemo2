package com.kayiyazilim.budaksanisakis.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.kayiyazilim.budaksanisakis.dao.AracDao;
import com.kayiyazilim.budaksanisakis.dao.ProjeDao;
import com.kayiyazilim.budaksanisakis.dao.SoforDao;
import com.kayiyazilim.budaksanisakis.dao.YakitHareketDao;
import com.kayiyazilim.budaksanisakis.model.entity.Arac;
import com.kayiyazilim.budaksanisakis.model.entity.Proje;
import com.kayiyazilim.budaksanisakis.model.entity.Sofor;
import com.kayiyazilim.budaksanisakis.model.entity.YakitHareket;
import com.kayiyazilim.budaksanisakis.model.type.HareketTipi;

@ManagedBean(name = "yakitHareketService")
@ViewScoped
public class YakitHareketService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8211037008026530691L;

	@EJB
	private YakitHareketDao yakitHareketDao;

	@EJB
	private SoforDao soforDao;

	@EJB
	private ProjeDao projeDao;

	@EJB
	private AracDao aracDao;

	private List<Arac> aracList;

	private List<Sofor> soforList;

	private List<Proje> projeList;

	private List<YakitHareket> yakitHareketList;

	private List<YakitHareket> filtrelenenYakitHareketList;

	private YakitHareket yakitHareket = new YakitHareket();

	public HareketTipi[] getHareketTipleri() {
		return HareketTipi.values();
	}

	@PostConstruct
	public void init() {
		aracList = aracDao.findAll();
		soforList = soforDao.findAll();
		projeList = projeDao.findAll();
		yakitHareketList = yakitHareketDao.findAll();
	}

	public void ekle() {
		yakitHareketDao.persist(yakitHareket);
		if (yakitHareketList == null)
			yakitHareketList = new ArrayList<YakitHareket>();
		yakitHareketList.add(yakitHareket);
		FacesMessage mesaj2 = new FacesMessage(
				"Yakýt Hareketi Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		yakitHareket = new YakitHareket();
	}

	public void guncelle(RowEditEvent event) {
		yakitHareket = ((YakitHareket) event.getObject());
		yakitHareketDao.merge(yakitHareket);
		FacesMessage mesaj2 = new FacesMessage(
				"Yakýt Hareketi Güncelleme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		yakitHareket = new YakitHareket();
	}

	public void sil() {
		if (yakitHareket != null)
			yakitHareketDao.remove(yakitHareket);
		yakitHareketList.remove(yakitHareket);
		FacesMessage mesaj2 = new FacesMessage(
				"Yakýt Hareketi Silme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		yakitHareket = new YakitHareket();
	}

	public List<Arac> getAracList() {
		return aracList;
	}

	public void setAracList(List<Arac> aracList) {
		this.aracList = aracList;
	}

	public List<Sofor> getSoforList() {
		return soforList;
	}

	public void setSoforList(List<Sofor> soforList) {
		this.soforList = soforList;
	}

	public List<Proje> getProjeList() {
		return projeList;
	}

	public void setProjeList(List<Proje> projeList) {
		this.projeList = projeList;
	}

	public List<YakitHareket> getYakitHareketList() {
		return yakitHareketList;
	}

	public void setYakitHareketList(List<YakitHareket> yakitHareketList) {
		this.yakitHareketList = yakitHareketList;
	}

	public YakitHareket getYakitHareket() {
		return yakitHareket;
	}

	public void setYakitHareket(YakitHareket yakitHareket) {
		this.yakitHareket = yakitHareket;
	}

	public List<YakitHareket> getFiltrelenenYakitHareketList() {
		return filtrelenenYakitHareketList;
	}

	public void setFiltrelenenYakitHareketList(
			List<YakitHareket> filtrelenenYakitHareketList) {
		this.filtrelenenYakitHareketList = filtrelenenYakitHareketList;
	}
}
