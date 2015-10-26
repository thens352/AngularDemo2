package com.kayi.qrvale.restful;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import com.kayi.qrvale.dao.ValeDao;
import com.kayi.qrvale.model.entity.Vale;

@Path("/valeservice")
@Stateless
public class ValeRestfulService {

	@EJB
	private ValeDao valeDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("status")
	public Response getStatus() {
		return Response.ok("{\"status\":\"Vale Service is running...\"}")
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login")
	public Response login(@QueryParam("kullaniciAdi") String kullaniciAdi,
			@QueryParam("sifre") String sifre) {
		String response = null;
		Vale vale = null;
		try {
			vale = valeDao.ValeGetirByKullaniciAdi(kullaniciAdi);
			if (vale.getSifre().equals(sifre)) {
				vale.setGorevliMi(true);
				valeDao.valeGuncelle(vale);
				response = constructJSON(true, vale.getId(), vale.getAd() + " "
						+ vale.getSoyad());
			} else {
				response = constructJSON(false, "Sifre yanlis");
			}
		} catch (Exception e) {
			response = constructJSON(false,
					"Kullanici adi yanlis ya da kayitli degil");
		}

		return Response.ok(response).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("logout")
	public Response logout(@QueryParam("id") Integer id) {
		String response = null;
		Vale vale = valeDao.ValeGetirById(id);
		if (vale == null)
			response = constructJSON(false);
		else {
			vale.setGorevliMi(false);
			valeDao.valeGuncelle(vale);
			response = constructJSON(true);
		}

		return Response.ok(response).build();
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
