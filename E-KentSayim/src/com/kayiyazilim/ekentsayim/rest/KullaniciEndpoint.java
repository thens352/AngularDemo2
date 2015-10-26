package com.kayiyazilim.ekentsayim.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.type.Statu;

/**
 * 
 */
@Stateless
@Path("/kullanici")
public class KullaniciEndpoint {
	@EJB
	private KullaniciDao dao;

	@GET
	@Path("/login/{kullaniciAdi}/{sifre}")
	@Produces("application/json")
	public String login(@PathParam("kullaniciAdi") String kullaniciAdi,
			@PathParam("sifre") String sifre) {
		JSONObject result = new JSONObject();
		Kullanici kullanici = dao.login(kullaniciAdi);
		if (kullanici == null) {
			try {
				result.put("result", false);
				result.put("error", "kullanici adi");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			if (!kullanici.getSifre().equals(sifre)) {
				try {
					result.put("result", false);
					result.put("error", "sifre");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (kullanici.getStatu() == Statu.AKTIF) {
				try {
					result.put("result", false);
					result.put("error", "statu");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					kullanici.setStatu(Statu.AKTIF);
					dao.merge(kullanici);
					result.put("result", true);
					result.put("id", kullanici.getId());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result.toString();
	}

	@GET
	@Path("/logout/{id}")
	@Produces("application/json")
	public String logout(@PathParam("id") int id) {
		JSONObject result = new JSONObject();
		Kullanici kullanici = dao.find(id);
		if (kullanici == null) {
			try {
				result.put("result", false);
				result.put("error", "id gecersiz");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				result.put("result", true);
				kullanici.setStatu(Statu.PASIF);
				dao.merge(kullanici);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Kullanici create(Kullanici entity) {
		dao.persist(entity);
		return entity;
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") int id) {
		Kullanici entity = dao.find(id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		dao.remove(entity);
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") int id) {
		Kullanici entity;
		try {
			entity = dao.find(id);
		} catch (NoResultException nre) {
			entity = null;
		}
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(entity).build();
	}

	@GET
	@Produces("application/json")
	public List<Kullanici> listAll() {
		List<Kullanici> results = dao.findAll();
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") int id, Kullanici entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id != entity.getId()) {
			return Response.status(Status.CONFLICT).entity(entity).build();
		}
		if (dao.find(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			entity = dao.merge(entity);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT)
					.entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}
}
