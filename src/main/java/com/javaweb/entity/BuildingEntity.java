package com.javaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity{
    @Column (name = "name")
    private String name;
    @Column (name = "district")
    private String district;
    @Column (name = "numberofbasement")
    private Long numberofbasement;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Column (name = "ward")
    private String ward;

    @Column (name = "street")
    private String street;

    @Column (name = "type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column (name = "floorarea")
    private Long floorarea;

    @Column (name = "direction")
    private String direction;

    @Column (name = "level")
    private String level;

    @Column (name = "rentprice")
    private Long rentprice;

    @Column (name = "managername")
    private String managername;

    @Column (name = "managerphonenumber")
    private String managerphonenumber;

    @Column (name = "servicefee")
    private String servicefee;

    @Column (name = "brokeragefee")
    private String brokeragefee;

    @Column(name = "rentpricedescription")
    private String rentpricedescription;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY,cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<RentAreaEntity>  rentareas = new ArrayList<>();

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

    public List<RentAreaEntity> getRentareas() {
        return rentareas;
    }

    public void setRentareas(List<RentAreaEntity> rentareas) {
        this.rentareas = rentareas;
    }

    public Long getFloorarea() {
        return floorarea;
    }

    public void setFloorarea(Long floorarea) {
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
    public Long getRentprice() {
        return rentprice;
    }
    public void setRentprice(Long rentprice) {
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
    public Long getNumberofbasement() {
        return numberofbasement;
    }
    public void setNumberofbasement(Long numberofbasement) {
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
