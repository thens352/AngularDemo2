package com.kayiyazilim.ekentsayim.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;

/**
 * 
 */
@Stateless
@Path("/parametre")
public class ParametreEndpoint {
	@EJB
	private ParametreDao dao;

	@GET
	@Produces("application/json")
	public List<Parametre> listAll() {
		List<Parametre> results = dao.findAll();
		return results;
	}
}
