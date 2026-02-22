package com.javaweb.model;

public class BuildingDTO {
	private String name;
	private Integer numberofbasement;
	private String address;
	private String managername;
	private String managerphonenumber;
	private Integer floorarea;
	private String rentarea;
	private Integer rentprice;
	private String servicefee;
	private String brokeragefee;
	
	
	public String getServicefee() {
		return servicefee;
	}
	public void setServicefee(String servicefee) {
	}
	public String getBrokeragefee() {
		return brokeragefee;
	}
	public void setBrokeragefee(String brokeragefee) {
		this.brokeragefee = brokeragefee;
	}
	public Integer getFloorarea() {
		return floorarea;
	}
	public void setFloorarea(Integer floorarea) {
		this.floorarea = floorarea;
	}
	public Integer getRentprice() {
		return rentprice;
	}
	public void setRentprice(Integer rentprice) {
		this.rentprice = rentprice;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public String getManagerphonenumber() {
		return managerphonenumber;
	}
	public void setManagerphonenumber(String managerphonenumber) {
		this.managerphonenumber = managerphonenumber;
	}
	public String getRentarea() {
		return rentarea;
	}
	public void setRentarea(String rentarea) {
		this.rentarea = rentarea;
	}
	public String getName() {
		return name;
	} 
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumberofbasement() {
		return numberofbasement;
	}
	public void setNumberofbasement(Integer numberofbasement) {
		this.numberofbasement = numberofbasement;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
