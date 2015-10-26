package com.demo.angulardemo2.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.demo.angulardemo2.model.Mahalle;

/**
 * 
 */
@Stateless
@Path("/mahalles")
public class MahalleEndpoint {
	@PersistenceContext(unitName = "AngularDemo2")
	private EntityManager em;

	@POST
	@Consumes("application/json")
	public Response create(Mahalle entity) {
		em.persist(entity);
		return Response.created(
				UriBuilder.fromResource(MahalleEndpoint.class)
						.path(String.valueOf(entity.getId())).build()).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		Mahalle entity = em.find(Mahalle.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.remove(entity);
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {
		TypedQuery<Mahalle> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT m FROM Mahalle m LEFT JOIN FETCH m.ilce WHERE m.id = :entityId ORDER BY m.id",
						Mahalle.class);
		findByIdQuery.setParameter("entityId", id);
		Mahalle entity;
		try {
			entity = findByIdQuery.getSingleResult();
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
	public List<Mahalle> listAll(@QueryParam("start") Integer startPosition,
			@QueryParam("max") Integer maxResult) {
		TypedQuery<Mahalle> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT m FROM Mahalle m LEFT JOIN FETCH m.ilce ORDER BY m.id",
						Mahalle.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		final List<Mahalle> results = findAllQuery.getResultList();
		return results;
	}

	@GET
	@Path("/byIlceId")
	@Produces("application/json")
	public List<Mahalle> listAllByIlceId(@QueryParam("IlceId") Long IlceId) {
		@SuppressWarnings("unchecked")
		List<Mahalle> results = em
				.createQuery("from Mahalle m where m.ilce.id=:IlceId")
				.setParameter("IlceId", IlceId).getResultList();
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Long id, Mahalle entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(entity.getId())) {
			return Response.status(Status.CONFLICT).entity(entity).build();
		}
		if (em.find(Mahalle.class, id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			entity = em.merge(entity);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT)
					.entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}
}
