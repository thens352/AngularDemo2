package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import com.ens.degerleme.dao.MahalleDao;
import com.ens.degerleme.dao.searchobject.MahalleSearch;
import com.ens.degerleme.model.entity.Mahalle;



@ManagedBean(name = "mahalleListService")
@ViewScoped
public class MahalleListService implements Serializable {

	private static final long serialVersionUID = -6093393727814589664L;
	@EJB
	private MahalleDao mahalleDao;

	MahalleSearch mahalleSearch = new MahalleSearch();

	List<Mahalle> mahalleList = new ArrayList<Mahalle>();

	@PostConstruct
	public void init() {
		mahalleList = mahalleDao.mahalleGetirByOrnek(mahalleSearch);
	}
	
	public void mahalleSil(Mahalle mahalle_s) {
		mahalleDao.mahalleSil(mahalle_s);
		mahalleListele();
		mahalle_s = new Mahalle();
	}

	public void mahalleListele() {
		
		mahalleList=mahalleDao.hepsiniGetir();
	}

	public String goToEdit(Mahalle mahalle) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		flash.put("mahalle", mahalle);

		return "mahalleKaydet?faces-redirect=true";
	}

	public MahalleDao getIlceDao() {
		return mahalleDao;
	}

	public MahalleDao getMahalleDao() {
		return mahalleDao;
	}

	public void setMahalleDao(MahalleDao mahalleDao) {
		this.mahalleDao = mahalleDao;
	}

	public MahalleSearch getMahalleSearch() {
		return mahalleSearch;
	}

	public void setMahalleSearch(MahalleSearch mahalleSearch) {
		this.mahalleSearch = mahalleSearch;
	}

	public List<Mahalle> getMahalleList() {
		return mahalleList;
	}

	public void setMahalleList(List<Mahalle> mahalleList) {
		this.mahalleList = mahalleList;
	}

}