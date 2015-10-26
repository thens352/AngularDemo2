package com.kayiyazilim.budaksanisakis.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.data.FilterEvent;

import com.kayiyazilim.budaksanisakis.dao.AracDao;
import com.kayiyazilim.budaksanisakis.dao.FaturaDao;
import com.kayiyazilim.budaksanisakis.dao.MalzemeDao;
import com.kayiyazilim.budaksanisakis.dao.ProjeDao;
import com.kayiyazilim.budaksanisakis.dao.SoforDao;
import com.kayiyazilim.budaksanisakis.dao.UrunDao;
import com.kayiyazilim.budaksanisakis.dao.searchobject.FaturaSearch;
import com.kayiyazilim.budaksanisakis.model.entity.Arac;
import com.kayiyazilim.budaksanisakis.model.entity.Fatura;
import com.kayiyazilim.budaksanisakis.model.entity.Malzeme;
import com.kayiyazilim.budaksanisakis.model.entity.Proje;
import com.kayiyazilim.budaksanisakis.model.entity.Sofor;

@ManagedBean(name = "muhasabeService")
@ViewScoped
public class MuhasabeService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2255697455538390750L;

	@EJB
	private FaturaDao faturaDao;

	@EJB
	private UrunDao urunDao;

	@EJB
	private ProjeDao projeDao;

	@EJB
	private SoforDao soforDao;

	@EJB
	private AracDao aracDao;

	@EJB
	private MalzemeDao malzemeDao;

	private List<Fatura> faturaList;

	private List<Proje> projeList;

	private List<Fatura> filtrelenenFaturaList;

	private FaturaSearch search;

	private List<Sofor> soforList;

	private List<Arac> aracList;

	private List<Malzeme> malzemeList;

	private Sofor sofor;

	private Arac arac;

	private Malzeme malzeme;

	@PostConstruct
	public void init() {
		projeList = projeDao.findAll();
		faturaList = faturaDao.findAll();
		soforList = soforDao.findAll();
		aracList = aracDao.findAll();
		malzemeList = malzemeDao.findAll();
	}

	public Double getToplamTutar() {
		Double toplamTutar = 0d;
		if (filtrelenenFaturaList == null || faturaList.size() == 0)
			for (Fatura temp : faturaList)
				toplamTutar += temp.getToplamTutar();
		else
			for (Fatura temp : filtrelenenFaturaList)
				toplamTutar += temp.getToplamTutar();
		System.out.println("toplam tutar");
		return toplamTutar;
	}

	public void ara() {
		faturaList = faturaDao.faturaAra(search);
	}

	public void araByArac() {
		if (arac == null)
			faturaList = faturaDao.findAll();
		else
			faturaList = faturaDao.faturaAraByAracID(arac.getId());
	}

	public void araBySofor() {
		if (sofor == null)
			faturaList = faturaDao.findAll();
		else
			faturaList = faturaDao.faturaAraBySoforID(sofor.getId());
	}

	public void araByMalzeme() {
		if (malzeme == null)
			faturaList = faturaDao.findAll();
		else
			faturaList = faturaDao.faturaAraByMalzemeID(malzeme.getId());
	}

	public void filterListener(FilterEvent filterEvent) {
		getToplamTutar();
	}

	public List<Fatura> getFaturaList() {
		return faturaList;
	}

	public void setFaturaList(List<Fatura> faturaList) {
		this.faturaList = faturaList;
	}

	public List<Fatura> getFiltrelenenFaturaList() {
		return filtrelenenFaturaList;
	}

	public void setFiltrelenenFaturaList(List<Fatura> filtrelenenFaturaList) {
		this.filtrelenenFaturaList = filtrelenenFaturaList;
	}

	public FaturaSearch getSearch() {
		return search;
	}

	public void setSearch(FaturaSearch search) {
		this.search = search;
	}

	public List<Proje> getProjeList() {
		return projeList;
	}

	public void setProjeList(List<Proje> projeList) {
		this.projeList = projeList;
	}

	public List<Sofor> getSoforList() {
		return soforList;
	}

	public void setSoforList(List<Sofor> soforList) {
		this.soforList = soforList;
	}

	public Sofor getSofor() {
		return sofor;
	}

	public void setSofor(Sofor sofor) {
		this.sofor = sofor;
	}

	public List<Arac> getAracList() {
		return aracList;
	}

	public void setAracList(List<Arac> aracList) {
		this.aracList = aracList;
	}

	public Arac getArac() {
		return arac;
	}

	public void setArac(Arac arac) {
		this.arac = arac;
	}

	public List<Malzeme> getMalzemeList() {
		return malzemeList;
	}

	public void setMalzemeList(List<Malzeme> malzemeList) {
		this.malzemeList = malzemeList;
	}

	public Malzeme getMalzeme() {
		return malzeme;
	}

	public void setMalzeme(Malzeme malzeme) {
		this.malzeme = malzeme;
	}
}
