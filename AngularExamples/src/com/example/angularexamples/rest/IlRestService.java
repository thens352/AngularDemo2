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

import com.example.angularexamples.dao.IlDao;
import com.example.angularexamples.model.Il;

@Path("/il")
@Stateless
public class IlRestService {

	@EJB
	private IlDao ilDao;

	@POST
	@Consumes("application/json")
	public Response kaydet(Il il) {
		ilDao.persist(il);
		return Response.created(
				UriBuilder.fromResource(IlRestService.class)
						.path(String.valueOf(il.getId())).build()).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response sil(@PathParam("id") int id) {
		Il il = ilDao.find(id);
		if (il == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		ilDao.remove(il);
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response bul(@PathParam("id") int id) {
		Il il;
		try {
			il = ilDao.find(id);
		} catch (NoResultException nre) {
			il = null;
		}
		if (il == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(il).build();
	}

	@GET
	@Produces("application/json")
	public Response Listele() {
		List<Il> ilList = ilDao.findAll();
		return Response.ok(getAsJSON(ilList)).build();
	}

	public String getAsJSON(List<Il> ilList) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(ilList);
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
	public Response update(@PathParam("id") int id, Il il) {
		if (il == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (il.getId() != id) {
			return Response.status(Status.CONFLICT).entity(il).build();
		}
		if (ilDao.find(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			il = ilDao.merge(il);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT)
					.entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}
}
