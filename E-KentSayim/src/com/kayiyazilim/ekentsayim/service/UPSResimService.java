package com.kayiyazilim.ekentsayim.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;

import com.kayiyazilim.ekentsayim.dao.BolgeDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.UPSResimDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.UPSResim;
import com.kayiyazilim.ekentsayim.model.search.UPSResimSearch;

@ManagedBean(name = "upsResimService")
@ViewScoped
public class UPSResimService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4213173503045304800L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private UPSResimDao dao;

	private List<UPSResim> upsResimList;

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private UPSResim upsResim = new UPSResim();
	
	private UPSResimSearch upsResimSearch=new UPSResimSearch();
	
	private String dosyaYolu;

	private String destination = FacesContext.getCurrentInstance()
			.getExternalContext().getRealPath("/")
			+ "WEB-INF/uploads/";

	private DefaultStreamedContent download;

	private FileUploadEvent upsResimUploadGuncelle;

	@PostConstruct
	public void init() {
		upsResimList = dao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();
	}
	
	public void ara(){
		upsResimList=dao.search(upsResimSearch);
	}

	public void kaydet() {
		if (upsResimUploadGuncelle != null) {
			dosyaYolu = destination
					+ upsResimUploadGuncelle.getFile().getFileName();
			upsResim.setUpsResimDosyasi(dosyaYolu);
			try {
				copyFile(upsResimUploadGuncelle.getFile().getFileName(),
						upsResimUploadGuncelle.getFile().getInputstream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			upsResimUploadGuncelle = null;
		}
		dao.persist(upsResim);
		if (upsResimList == null)
			upsResimList = new ArrayList<UPSResim>();
		upsResimList.add(upsResim);
		upsResim = new UPSResim();
	}

	public void upload(FileUploadEvent event) {
		this.upsResimUploadGuncelle = event;
	}

	public void guncelle(RowEditEvent event) {
		upsResim = ((UPSResim) event.getObject());
		if (upsResimUploadGuncelle != null) {
			File krokiDosya = new File(upsResim.getUpsResimDosyasi());
			krokiDosya.delete();
			dosyaYolu = destination
					+ upsResimUploadGuncelle.getFile().getFileName();
			upsResim.setUpsResimDosyasi(dosyaYolu);

			try {
				copyFile(upsResimUploadGuncelle.getFile().getFileName(),
						upsResimUploadGuncelle.getFile().getInputstream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			upsResimUploadGuncelle = null;
		}
		dao.merge(upsResim);
		upsResim = new UPSResim();
	}

	public void sil() {
		try {
			File upsResimDosya = new File(upsResim.getUpsResimDosyasi());
			upsResimDosya.delete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (upsResim != null)
			dao.remove(upsResim);
		upsResimList.remove(upsResim);
		upsResim = new UPSResim();
	}

	public void update(FileUploadEvent event) {
		this.upsResimUploadGuncelle = event;
	}
	
	public String getResimAdi(UPSResim upsResim){
		String[] slash=upsResim.getUpsResimDosyasi().split("/");
		return slash[slash.length-1];
	}

	public void indir(UPSResim upsResim) {
		File file = new File(upsResim.getUpsResimDosyasi());
		InputStream input = null;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		setDownload(new DefaultStreamedContent(input,
				externalContext.getMimeType(file.getName()), file.getName()));
	}

	public void copyFile(String fileName, InputStream in) {
		try {
			OutputStream out = new FileOutputStream(new File(destination
					+ fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			in.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<UPSResim> getUpsResimList() {
		return upsResimList;
	}

	public void setUpsResimList(List<UPSResim> upsResimList) {
		this.upsResimList = upsResimList;
	}

	public UPSResim getUpsResim() {
		return upsResim;
	}

	public void setUpsResim(UPSResim upsResim) {
		this.upsResim = upsResim;
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

	public String getDosyaYolu() {
		return dosyaYolu;
	}

	public void setDosyaYolu(String dosyaYolu) {
		this.dosyaYolu = dosyaYolu;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public DefaultStreamedContent getDownload() {
		return download;
	}

	public void setDownload(DefaultStreamedContent download) {
		this.download = download;
	}

	public FileUploadEvent getUpsResimUploadGuncelle() {
		return upsResimUploadGuncelle;
	}

	public void setUpsResimUploadGuncelle(FileUploadEvent upsResimUploadGuncelle) {
		this.upsResimUploadGuncelle = upsResimUploadGuncelle;
	}

	public UPSResimSearch getUpsResimSearch() {
		return upsResimSearch;
	}

	public void setUpsResimSearch(UPSResimSearch upsResimSearch) {
		this.upsResimSearch = upsResimSearch;
	}
}
