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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;import java.util.Date;

import com.kayiyazilim.ekentsayim.dao.BolgeDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.TurnikeDao;
import com.kayiyazilim.ekentsayim.model.entity.Turnike;

/**
 * 
 */
@Stateless
@Path("/turnike")
public class TurnikeEndpoint {
	@EJB
	private TurnikeDao dao;
	@EJB
	private KullaniciDao kullaniciDao;
	
	@EJB
	private BolgeDao bolgeDao;
	
	@GET
	@Path("searchBarkod/{barkod}")
	@Produces("application/json")
	public Response searchBarkod(@PathParam("barkod") Integer barkod) {
		Turnike entity = dao.searchRest(barkod, null);
		if (entity != null)
			return Response.ok(entity).build();
		return Response.noContent().build();
	}
	
	@GET
	@Path("searchSeriNo/{cihazSeriNo}")
	@Produces("application/json")
	public Response searchSeriNo(@PathParam("cihazSeriNo") String cihazSeriNo) {
		Turnike entity = dao.searchRest(0, cihazSeriNo);
		if (entity != null)
			return Response.ok(entity).build();
		return Response.noContent().build();
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Turnike create(Turnike entity) {
		entity.setGununTarihi(new Date()); dao.persist(entity);entity.setKullanici(kullaniciDao.find(entity.getKullaniciID()));entity.setBolge(bolgeDao.find(entity.getBolgeID()));
		return entity;
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") int id) {
		Turnike entity = dao.find(id);
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
		Turnike entity;
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
	public List<Turnike> listAll(@QueryParam("start") Integer startPosition,
			@QueryParam("max") Integer maxResult) {
		List<Turnike> results = dao.findAll();
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") int id, Turnike entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id != entity.getBarkod()) {
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
