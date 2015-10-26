package com.kayi.qrvale.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.kayi.qrvale.dao.AracDao;
import com.kayi.qrvale.dao.HareketDao;
import com.kayi.qrvale.dao.IsletmeDao;
import com.kayi.qrvale.dao.ValeDao;
import com.kayi.qrvale.dao.search.AracSearch;
import com.kayi.qrvale.dao.search.HareketSearch;
import com.kayi.qrvale.dao.search.ValeSearch;
import com.kayi.qrvale.model.entity.Arac;
import com.kayi.qrvale.model.entity.Hareket;
import com.kayi.qrvale.model.entity.Isletme;
import com.kayi.qrvale.model.entity.Vale;
import com.kayi.qrvale.model.type.Marka;
import com.kayi.qrvale.model.type.Renk;

@ManagedBean(name = "raporlamaService")
@ViewScoped
public class RaporlamaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -344929825656673022L;

	@EJB
	private HareketDao hareketDao;

	@EJB
	private IsletmeDao isletmeDao;

	@EJB
	private ValeDao valeDao;

	@EJB
	private AracDao aracDao;

	private Isletme isletme;

	private Vale alanVale = new Vale();

	private Vale verenVale = new Vale();

	private Arac arac = new Arac();

	private HareketSearch hareketSearch = new HareketSearch();

	private AracSearch aracSearch = new AracSearch();

	private ValeSearch valeSearch = new ValeSearch();

	private List<Vale> valeList = new ArrayList<Vale>();

	private List<Arac> aracList = new ArrayList<Arac>();

	private List<Hareket> hareketList = new ArrayList<Hareket>();

	public Marka[] getMarkalar() {
		return Marka.values();
	}

	public Renk[] getRenkler() {
		return Renk.values();
	}

	@PostConstruct
	public void init() {
		try {
			isletme = isletmeDao.IsletmeGetirById((Integer) FacesContext
					.getCurrentInstance().getExternalContext().getSessionMap()
					.get("isletmeID"));
			aracList = aracDao.AracGetirByIsletmeId(isletme.getId());
			valeList = valeDao.valeGetirByIsletmeId(isletme.getId());
		} catch (Exception e) {
			LoginService.sessionDoldu();
		}
	}

	public void genelRaporla() {
		hareketList = hareketDao.HareketGetir(hareketSearch);
	}

	public void aracHareketRaporla() {
		hareketList = new ArrayList<Hareket>();
		aracList = aracDao.aracGetir(aracSearch);
		if (!aracList.isEmpty()) {
			for (Arac tempArac : aracList) {
				List<Hareket> tempList = new ArrayList<Hareket>();
				hareketSearch.setArac(tempArac);
				tempList = hareketDao.HareketGetir(hareketSearch);
				hareketList.addAll(tempList);
			}
		}
		aracSearch = new AracSearch();
		hareketSearch = new HareketSearch();
	}

	public void valeHareketRaporla() {
		hareketList = new ArrayList<Hareket>();
		valeList = valeDao.valeGetir(valeSearch);
		if (!valeList.isEmpty()) {
			for (Vale tempVale : valeList) {
				List<Hareket> tempList = new ArrayList<Hareket>();
				hareketSearch.setAlanVale(tempVale);
				tempList = hareketDao.HareketGetir(hareketSearch);

				for (Hareket tempHareket : tempList) {
					if (!tempHareket.getAlanVale().equals(
							tempHareket.getVerenVale())) {
						hareketList.add(tempHareket);
					}
				}

				hareketSearch.setAlanVale(null);

				hareketSearch.setVerenVale(tempVale);
				tempList = hareketDao.HareketGetir(hareketSearch);
				hareketList.addAll(tempList);
				hareketSearch.setVerenVale(null);

			}
		}
		valeSearch = new ValeSearch();
		hareketSearch = new HareketSearch();
	}

	public Isletme getIsletme() {
		return isletme;
	}

	public void setIsletme(Isletme isletme) {
		this.isletme = isletme;
	}

	public Vale getAlanVale() {
		return alanVale;
	}

	public void setAlanVale(Vale alanVale) {
		this.alanVale = alanVale;
	}

	public Vale getVerenVale() {
		return verenVale;
	}

	public void setVerenVale(Vale verenVale) {
		this.verenVale = verenVale;
	}

	public Arac getArac() {
		return arac;
	}

	public void setArac(Arac arac) {
		this.arac = arac;
	}

	public HareketSearch getHareketSearch() {
		return hareketSearch;
	}

	public void setHareketSearch(HareketSearch hareketSearch) {
		this.hareketSearch = hareketSearch;
	}

	public AracSearch getAracSearch() {
		return aracSearch;
	}

	public void setAracSearch(AracSearch aracSearch) {
		this.aracSearch = aracSearch;
	}

	public ValeSearch getValeSearch() {
		return valeSearch;
	}

	public void setValeSearch(ValeSearch valeSearch) {
		this.valeSearch = valeSearch;
	}

	public List<Vale> getValeList() {
		return valeList;
	}

	public void setValeList(List<Vale> valeList) {
		this.valeList = valeList;
	}

	public List<Arac> getAracList() {
		return aracList;
	}

	public void setAracList(List<Arac> aracList) {
		this.aracList = aracList;
	}

	public List<Hareket> getHareketList() {
		return hareketList;
	}

	public void setHareketList(List<Hareket> hareketList) {
		this.hareketList = hareketList;
	}
}
