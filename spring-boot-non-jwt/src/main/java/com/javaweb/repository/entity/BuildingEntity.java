 package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "building")
public class BuildingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column (name = "name")
	private String name;
	
	@Column (name = "numberofbasement")
	private Integer numberofbasement;
	
	@Column (name = "ward")
	private String ward;
	
	@Column (name = "street")
	private String street;
	
	
	@Column (name = "floorarea")
	private Integer floorarea;
	
	@Column (name = "direction")
	private String direction;
	
	@Column (name = "level")
	private String level;
	
	@Column (name = "rentprice")
	private Integer rentprice;
	
	@Column (name = "managername")
	private String managername;
	
	@Column (name = "managerphonenumber")
	private String managerphonenumber;
	
	@Column (name = "servicefee")
	private String servicefee;
	
	@Column (name = "brokeragefee")
	private String brokeragefee;
	
	@ManyToOne
	@JoinColumn (name = "districtid")
	private DistrictEntity district;
	
	@OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
	private List<RentAreaEntity>  rentareas = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "buildingrenttype", 
				joinColumns = @JoinColumn(name = "buildingid", nullable = false),
				inverseJoinColumns = @JoinColumn(name = "renttypeid", nullable = false))
	private List<RentTypeEntity> renttypes = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "assignmentbuilding", 
				joinColumns = @JoinColumn(name = "buildingid", nullable = false),
				inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
	private List<UserEntity> users = new ArrayList<>();


	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public List<RentTypeEntity> getRenttypes() {
		return renttypes;
	}

	public void setRenttypes(List<RentTypeEntity> renttypes) {
		this.renttypes = renttypes;
	}

	public List<RentAreaEntity> getRentareas() {
		return rentareas;
	}

	public void setRentareas(List<RentAreaEntity> rentareas) {
		this.rentareas = rentareas;
	}

	public DistrictEntity getDistrict() {
		return district;
	}

	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}

	public Integer getFloorarea() {
		return floorarea;
	}
	
	public void setFloorarea(Integer floorarea) {
		this.floorarea = floorarea;
	}
	public String getDirection() {
		return direction;
	}

	public String getServicefee() {
		return servicefee;
	}

	public void setServicefee(String servicefee) {
		this.servicefee = servicefee;
	}

	public String getBrokeragefee() {
		return brokeragefee;
	}

	public void setBrokeragefee(String string) {
		this.brokeragefee = string;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
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
	public String getName() {
		return name;
	}
	public Integer getNumberofbasement() {
		return numberofbasement;
	}
	public void setNumberofbasement(Integer numberofbasement) {
		this.numberofbasement = numberofbasement;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
}
