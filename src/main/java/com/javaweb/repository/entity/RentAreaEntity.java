package com.javaweb.repository.entity;

public class RentAreaEntity {
	 private Integer id;
	 private String value;
	 private Integer buildingid;
	 public Integer getId() {
		 return id;
	 }
	 public void setId(Integer id) {
		 this.id = id;
	 }

	 public String getValue() {
		return value;
	}
	 public void setValue(String value) {
		 this.value = value;
	 }
	 public Integer getBuildingid() {
		 return buildingid;
	 }
	 public void setBuildingid(Integer buildingid) {
		 this.buildingid = buildingid;
	 }
	 
}
