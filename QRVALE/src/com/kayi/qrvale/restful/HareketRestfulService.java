package com.kayi.qrvale.restful;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import com.kayi.qrvale.dao.AracDao;
import com.kayi.qrvale.dao.HareketDao;
import com.kayi.qrvale.dao.IsletmeDao;
import com.kayi.qrvale.dao.ValeDao;
import com.kayi.qrvale.model.entity.Arac;
import com.kayi.qrvale.model.entity.Hareket;
import com.kayi.qrvale.model.entity.Vale;
import com.kayi.qrvale.model.type.Marka;
import com.kayi.qrvale.model.type.Renk;

@Path("/hareketservice")
@Stateless
public class HareketRestfulService {

	@EJB
	private AracDao aracDao;

	@EJB
	private ValeDao valeDao;

	@EJB
	private HareketDao hareketDao;

	@EJB
	private IsletmeDao isletmeDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("status")
	public Response getStatus() {
		return Response.ok("{\"status\":\"Hareket Service is running...\"}")
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("yeniHareket")
	public Response yeniHareket(@QueryParam("plaka") String plaka,
			@QueryParam("marka") String marka,
			@QueryParam("model") String model, @QueryParam("renk") String renk,
			@QueryParam("isletmeID") Integer isletmeID,
			@QueryParam("alanValeID") Integer alanValeID) {

		String response = null;
		int aracID;
		int hareketID;

		try {
			if (aracDao.AracGetirByPlaka(plaka) != null)
				aracID = aracDao.AracGetirByPlaka(plaka).getId();
			else
				aracID = aracKaydet(plaka, marka, model, renk, alanValeID,
						isletmeID);

			hareketID = hareketKaydet(aracID, alanValeID, isletmeID);
			response = hareketConverter(hareketDao.HareketGetirById(hareketID));
		} catch (Exception e) {
			response = constructJSON(false, e.getMessage());
		}
		return Response.ok(response).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("koordinatEkle")
	public Response koordinatEkle(@QueryParam("koordinat") String koordinat,
			@QueryParam("hareketID") Integer hareketID) {
		Hareket hareket = hareketDao.HareketGetirById(hareketID);
		Arac arac = hareket.getArac();
		Vale vale = hareket.getAlanVale();

		arac.setKoordinat(koordinat);
		vale.setDoluMu(false);

		valeDao.valeGuncelle(vale);
		aracDao.AracGuncelle(arac);

		return Response.ok(constructJSON(true)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("bildirimler")
	public Response bildirimler() {
		try {
			List<Hareket> hareketList = hareketDao.hepsiniGetir();
			return Response.ok(hareketConverter(hareketList)).build();
		} catch (Exception e) {
			return Response.ok(constructJSON(false, e.getMessage())).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("hareket")
	public Response hareket(@QueryParam("id") Integer id) {
		try {
			return Response.ok(
					hareketConverter(hareketDao.HareketGetirById(id))).build();
		} catch (Exception e) {
			return Response.ok(constructJSON(false, e.getMessage())).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("teslimAlindi")
	public Response teslimAlindi(@QueryParam("hareketID") Integer hareketID,
			@QueryParam("verenValeID") Integer verenValeID) {
		String response = null;
		try {
			Hareket hareket = hareketDao.HareketGetirById(hareketID);
			Vale verenVale = valeDao.ValeGetirById(verenValeID);
			if (hareket.getVerisTarihi() == null && !verenVale.isDoluMu()) {
				hareket.setVerenVale(verenVale);
				hareket.setVerisTarihi(new Date());
				verenVale.setDoluMu(true);
				hareketDao.hareketGuncelle(hareket);
				valeDao.valeGuncelle(verenVale);
				response = constructJSON(true);
			} else
				response = constructJSON(false,
						"Hareket teslim alýnmýþ ya da vale dolu");

		} catch (Exception e) {
			response = constructJSON(false, e.getMessage());
		}

		return Response.ok(response).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("istek")
	public Response istek(@QueryParam("id") Integer id) {
		String response = null;
		try {
			Hareket hareket = hareketDao.HareketGetirById(id);
			if (hareket.getArac().getKoordinat() != null) {
				hareket.setIstekTarihi(new Date());
				hareketDao.hareketGuncelle(hareket);
				response = constructJSON(true);
			} else
				response = constructJSON(false, "Araç koordinatý bulunamadý");

		} catch (Exception e) {
			response = "{\"status\":\"401\","
					+ "\"message\":\"Process failed! \""
					+ "\"developerMessage\":\"" + e.getMessage() + "\"" + "}";
		}

		return Response.ok(response).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("tamamlandi")
	public Response tamamlandi(@QueryParam("id") Integer id) {
		String response = null;
		try {
			Hareket hareket = hareketDao.HareketGetirById(id);
			Vale verenVale = hareket.getVerenVale();
			Arac arac = hareket.getArac();
			if (hareket.getVerisTarihi() != null) {
				hareket.setTamamlanmaTarihi(new Date());
				verenVale.setDoluMu(false);
				arac.setKoordinat(null);
				aracDao.AracGuncelle(arac);
				valeDao.valeGuncelle(verenVale);
				hareketDao.hareketGuncelle(hareket);
				response = constructJSON(true);
			} else
				response = constructJSON(false,
						"Harekette herhangi bir vale görevli deðil");

		} catch (Exception e) {
			response = constructJSON(false, e.getMessage());
		}

		return Response.ok(response).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("suanki")
	public Response suanki(@QueryParam("id") String id) {
		String response = null;
		Vale vale = null;
		try {
			vale = valeDao.ValeGetirById(Integer.valueOf(id));
			if (vale != null) {
				Hareket suankiHareket = hareketDao.suankiHareket(vale.getId());
				if (suankiHareket != null) {
					response = hareketConverter(suankiHareket);
				} else {
					response = constructJSON(false,
							"Herhangi bir hareket teslim alýnmamýs");
				}
			}
		} catch (Exception e) {
			response = constructJSON(false, "Vale bulunamadi");
		}
		return Response.ok(response).build();
	}

	private int aracKaydet(String plaka, String marka, String model,
			String renk, Integer alanValeID, Integer isletmeID) {
		Arac arac = new Arac();
		arac.setPlaka(plaka);
		arac.setMarka(Marka.valueOf(marka));
		arac.setModel(model);
		arac.setRenk(Renk.valueOf(renk));
		arac.setIsletme(isletmeDao.IsletmeGetirById(isletmeID));

		aracDao.AracKaydet(arac);

		return arac.getId();
	}

	private int hareketKaydet(Integer aracID, Integer alanValeID,
			Integer isletmeID) {
		Hareket hareket = new Hareket();
		Vale vale = valeDao.ValeGetirById(alanValeID);

		hareket.setArac(aracDao.AracGetirById(aracID));
		hareket.setAlanVale(vale);
		hareket.setAlisTarihi(new Date());
		hareket.setIsletme(isletmeDao.IsletmeGetirById(isletmeID));

		vale.setDoluMu(true);

		valeDao.valeGuncelle(vale);
		hareketDao.hareketKaydet(hareket);

		return hareket.getId();
	}

	public String hareketConverter(Hareket hareket) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("result", true);
			obj.put("hareketID", hareket.getId().toString());
			obj.put("plaka", hareket.getArac().getPlaka());
			obj.put("marka", hareket.getArac().getMarka().getDisplayName());
			obj.put("model", hareket.getArac().getModel());
			obj.put("renk", hareket.getArac().getRenk().getDisplayName());
			obj.put("koordinat", hareket.getArac().getKoordinat());

			obj.put("alanValeID", hareket.getAlanVale().getId().toString());
			obj.put("alisTarihi", hareket.getAlisTarihi().toString());

			if (hareket.getIstekTarihi() != null)
				obj.put("istekTarihi", hareket.getIstekTarihi().toString());

			if (hareket.getVerenVale() != null)
				obj.put("verenValeID", hareket.getVerenVale().getId()
						.toString());
			if (hareket.getVerisTarihi() != null)
				obj.put("verisTarihi", hareket.getVerisTarihi().toString());

			if (hareket.getTamamlanmaTarihi() != null)
				obj.put("tamamlanmaTarihi", hareket.getTamamlanmaTarihi()
						.toString());
			obj.put("isletmeID", hareket.getIsletme().getId().toString());

		} catch (JSONException e) {
			return e.getMessage();
		}

		return obj.toString();
	}

	public String hareketConverter(List<Hareket> hareketList) {
		JSONArray array = new JSONArray();
		for (Hareket temp : hareketList) {
			if (temp.getIstekTarihi() != null && temp.getVerisTarihi() == null)
				try {
					array.put(array.length(), new JSONObject(
							hareketConverter(temp)));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		JSONObject result = new JSONObject();

		try {
			result.put("bildirimler", array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result.toString();
	}

	public String constructJSON(boolean result) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("result", new Boolean(result));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString();
	}

	public String constructJSON(boolean result, String error) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("error", error);
			obj.put("result", new Boolean(result));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString();
	}

	public String constructJSON(boolean result, Integer id, String adSoyad) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("id", id);
			obj.put("adSoyad", adSoyad);
			obj.put("result", new Boolean(result));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString();
	}
}
