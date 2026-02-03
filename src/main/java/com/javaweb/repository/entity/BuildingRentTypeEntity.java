package com.javaweb.repository.entity;

public class BuildingRentTypeEntity {
	private Long id;
	private Long buildingid;
	private Long renttypeid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBuildingid() {
		return buildingid;
	}
	public void setBuildingid(Long buildingid) {
		this.buildingid = buildingid;
	}
	public Long getRenttypeid() {
		return renttypeid;
	}
	public void setRenttypeid(Long renttypeid) {
		this.renttypeid = renttypeid;
	}
	
}
