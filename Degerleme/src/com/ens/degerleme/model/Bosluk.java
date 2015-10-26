package com.ens.degerleme.model;

import java.io.Serializable;

public class Bosluk  implements Serializable{

	private static final long serialVersionUID = -1588999722814000024L;

	private int id;
	private String param;
	private String metin;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMetin() {
		return metin;
	}
	public void setMetin(String metin) {
		this.metin = metin;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	
	

}
