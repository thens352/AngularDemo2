package com.kayiyazilim.budaksanisakis.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;

import com.kayiyazilim.budaksanisakis.dao.KrokiDao;
import com.kayiyazilim.budaksanisakis.dao.ProjeDao;
import com.kayiyazilim.budaksanisakis.model.entity.Kroki;
import com.kayiyazilim.budaksanisakis.model.entity.Proje;

@ManagedBean(name = "projeService")
@ViewScoped
public class ProjeService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6314268491926963212L;

	@EJB
	private ProjeDao projeDao;

	@EJB
	private KrokiDao krokiDao;

	private Proje proje = new Proje();

	private List<Proje> projeList;

	private List<Kroki> krokiList;

	private Kroki kroki;

	private String dosyaYolu;

	private String destination = FacesContext.getCurrentInstance()
			.getExternalContext().getRealPath("/")
			+ "WEB-INF/uploads/";

	private DefaultStreamedContent download;

	private FileUploadEvent krokiUploadGuncelle;

	@PostConstruct
	public void init() {
		projeList = projeDao.findAll();
	}

	public void kaydet() {
		projeDao.persist(proje);
		projeList.add(proje);
		FacesMessage mesaj2 = new FacesMessage(
				"Proje Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		proje = new Proje();
	}

	public void guncelle(RowEditEvent event) {
		proje = (Proje) event.getObject();
		projeDao.merge(proje);
		FacesMessage mesaj2 = new FacesMessage(
				"Proje Güncelleme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		proje = new Proje();
	}

	public void sil() {
		projeDao.remove(proje);
		projeList.remove(proje);
		FacesMessage mesaj2 = new FacesMessage(
				"Proje Silme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		proje = new Proje();
	}

	public void krokiGuncelle(RowEditEvent event) {
		kroki = (Kroki) event.getObject();
		if (krokiUploadGuncelle != null) {
			dosyaYolu = destination
					+ krokiUploadGuncelle.getFile().getFileName();

			File krokiDosya = new File(kroki.getDosyaYolu());
			krokiDosya.delete();

			kroki.setAd(krokiUploadGuncelle.getFile().getFileName());
			kroki.setDosyaYolu(dosyaYolu);
			kroki.setYuklemeTarihi(new Date());
			try {
				copyFile(krokiUploadGuncelle.getFile().getFileName(),
						krokiUploadGuncelle.getFile().getInputstream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		krokiDao.merge(kroki);
		FacesMessage mesaj2 = new FacesMessage(
				"Kroki Güncelleme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		kroki = new Kroki();
	}

	public void krokiSil() {
		File krokiDosya = new File(kroki.getDosyaYolu());
		krokiDosya.delete();
		proje.getKrokiList().remove(kroki);
		krokiDao.remove(kroki);
		FacesMessage mesaj2 = new FacesMessage(
				"Kroki Silme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		kroki = new Kroki();
	}

	public void update(FileUploadEvent event) {
		this.krokiUploadGuncelle = event;
	}

	public void upload(FileUploadEvent event) {
		kroki = new Kroki();
		dosyaYolu = destination + event.getFile().getFileName();
		kroki.setAd(event.getFile().getFileName());
		kroki.setYuklemeTarihi(new Date());
		kroki.setDosyaYolu(dosyaYolu);
		kroki.setProje(proje);
		krokiList.add(kroki);
		krokiDao.persist(kroki);
		FacesMessage mesaj2 = new FacesMessage(
				"Kroki Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		kroki = new Kroki();

		// try {
		// InputStream input = event.getFile().getInputstream();
		// File folder = new File(destination);
		// String filename = FilenameUtils.getBaseName(event.getFile()
		// .getFileName());
		// String extension = FilenameUtils.getExtension(event.getFile()
		// .getFileName());
		// File file = File.createTempFile(filename + "-", "." + extension,
		// folder);
		// Files.copy(input, file.toPath());
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		try {
			copyFile(event.getFile().getFileName(), event.getFile()
					.getInputstream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void indir(Kroki kroki) {
		File file = new File(kroki.getDosyaYolu());
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

	public Proje getProje() {
		return proje;
	}

	public void setProje(Proje proje) {
		this.proje = proje;
	}

	public List<Proje> getProjeList() {
		return projeList;
	}

	public void setProjeList(List<Proje> projeList) {
		this.projeList = projeList;
	}

	public Kroki getKroki() {
		return kroki;
	}

	public void setKroki(Kroki kroki) {
		this.kroki = kroki;
	}

	public List<Kroki> getKrokiList() {
		return krokiList;
	}

	public void setKrokiList(List<Kroki> krokiList) {
		this.krokiList = krokiList;
	}

	public DefaultStreamedContent getDownload() {
		return download;
	}

	public void setDownload(DefaultStreamedContent download) {
		this.download = download;
	}

	public FileUploadEvent getKrokiUploadGuncelle() {
		return krokiUploadGuncelle;
	}

	public void setKrokiUploadGuncelle(FileUploadEvent krokiUploadGuncelle) {
		this.krokiUploadGuncelle = krokiUploadGuncelle;
	}
}
