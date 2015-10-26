package com.example.angularexamples.rest;

import java.io.IOException;
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
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.example.angularexamples.dao.IlceDao;
import com.example.angularexamples.model.Ilce;

@Path("/ilce")
@Stateless
public class IlceRestService {

	@EJB
	private IlceDao ilceDao;

	@POST
	@Consumes("application/json")
	public Response kaydet(Ilce ilce) {
		ilceDao.persist(ilce);
		return Response.created(
				UriBuilder.fromResource(IlceRestService.class)
						.path(String.valueOf(ilce.getId())).build()).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response sil(@PathParam("id") int id) {
		Ilce ilce = ilceDao.find(id);
		if (ilce == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		ilceDao.remove(ilce);
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response bul(@PathParam("id") int id) {
		Ilce ilce;
		try {
			ilce = ilceDao.find(id);
		} catch (NoResultException nre) {
			ilce = null;
		}
		if (ilce == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(ilce).build();
	}

	@GET
	@Produces("application/json")
	public Response Listele() {
		List<Ilce> ilceList = ilceDao.findAll();
		String a = getAsJSON(ilceList);
		return Response.ok(a).build();
	}

	public String getAsJSON(List<Ilce> ilceList) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(ilceList);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") int id, Ilce ilce) {
		if (ilce == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (ilce.getId() != id) {
			return Response.status(Status.CONFLICT).entity(ilce).build();
		}
		if (ilceDao.find(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			ilce = ilceDao.merge(ilce);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT)
					.entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}
}
