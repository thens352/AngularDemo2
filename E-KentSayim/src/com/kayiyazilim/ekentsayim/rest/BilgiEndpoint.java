package com.kayiyazilim.ekentsayim.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.kayiyazilim.ekentsayim.dao.BilgiDao;
import com.kayiyazilim.ekentsayim.model.entity.Bilgi;

@Stateless
@Path("/bilgi")
public class BilgiEndpoint {

	@EJB
	private BilgiDao dao;

	@GET
	@Produces("application/json")
	public List<Bilgi> listAll() {
		List<Bilgi> results = dao.findAll();
		for (Bilgi bilgi : results) {
			bilgi.setEkranAdi(bilgi.getEkran().getDisplayName());
		}
		return results;
	}
}
