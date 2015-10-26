package com.kayiyazilim.ekentsayim.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.kayiyazilim.ekentsayim.dao.BilgiDao;
import com.kayiyazilim.ekentsayim.model.entity.Bilgi;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "bilgiService")
@ViewScoped
public class BilgiService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6940763684474116584L;

	@EJB
	private BilgiDao dao;

	private Bilgi bilgi = new Bilgi();

	private List<Bilgi> bilgiList = new ArrayList<Bilgi>();

	public Ekran[] getEkranlar() {
		return Ekran.values();
	}

	@PostConstruct
	public void init() {
		bilgiList = dao.findAll();
	}
	
	public void kaydet() {
		dao.persist(bilgi);
		if (bilgiList == null)
			bilgiList = new ArrayList<Bilgi>();
		bilgiList.add(bilgi);
		bilgi = new Bilgi();
	}

	public void guncelle(RowEditEvent event) {
		bilgi = ((Bilgi) event.getObject());
		dao.merge(bilgi);
		bilgi = new Bilgi();
	}

	public void sil() {
		if (bilgi != null)
			dao.remove(bilgi);
		bilgiList.remove(bilgi);
		bilgi = new Bilgi();
	}

	public BilgiDao getDao() {
		return dao;
	}

	public void setDao(BilgiDao dao) {
		this.dao = dao;
	}

	public Bilgi getBilgi() {
		return bilgi;
	}

	public void setBilgi(Bilgi bilgi) {
		this.bilgi = bilgi;
	}

	public List<Bilgi> getBilgiList() {
		return bilgiList;
	}

	public void setBilgiList(List<Bilgi> bilgiList) {
		this.bilgiList = bilgiList;
	}
}
