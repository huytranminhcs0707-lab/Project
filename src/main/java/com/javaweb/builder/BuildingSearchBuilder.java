package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {

    private String name;
    private Integer floorarea;
    private Integer districtid;
    private String ward;
    private String street;
    private Integer numberofbasement;
    private String direction;
    private Integer level;
    private Integer areafrom;
    private Integer areato;
    private Integer rentpriceto;
    private Integer rentpricefrom;
    private String managername;
    private String managerphonenumber;
    private Integer staffid;
    private List<String> typecode;

    private BuildingSearchBuilder(Builder builder) {
        this.name = builder.name;
        this.floorarea = builder.floorarea;
        this.districtid = builder.districtid;
        this.ward = builder.ward;
        this.street = builder.street;
        this.numberofbasement = builder.numberofbasement;
        this.direction = builder.direction;
        this.level = builder.level;
        this.areafrom = builder.areafrom;
        this.areato = builder.areato;
        this.rentpricefrom = builder.rentpricefrom;
        this.rentpriceto = builder.rentpriceto;
        this.managername = builder.managername;
        this.managerphonenumber = builder.managerphonenumber;
        this.staffid = builder.staffid;
        this.typecode = builder.typecode;
    }

    // ================= GETTER =================

    public String getName() { return name; }
    public Integer getFloorarea() { return floorarea; }
    public Integer getDistrictid() { return districtid; }
    public String getWard() { return ward; }
    public String getStreet() { return street; }
    public Integer getNumberofbasement() { return numberofbasement; }
    public String getDirection() { return direction; }
    public Integer getLevel() { return level; }
    public Integer getAreafrom() { return areafrom; }
    public Integer getAreato() { return areato; }
    public Integer getRentpriceto() { return rentpriceto; }
    public Integer getRentpricefrom() { return rentpricefrom; }
    public String getManagername() { return managername; }
    public String getManagerphonenumber() { return managerphonenumber; }
    public Integer getStaffid() { return staffid; }
    public List<String> getTypecode() { return typecode; }

    // ================= BUILDER =================

    public static class Builder {

        private String name;
        private Integer floorarea;
        private Integer districtid;
        private String ward;
        private String street;
        private Integer numberofbasement;
        private String direction;
        private Integer level;
        private Integer areafrom;
        private Integer areato;
        private Integer rentpriceto;
        private Integer rentpricefrom;
        private String managername;
        private String managerphonenumber;
        private Integer staffid;
        private List<String> typecode = new ArrayList<>();

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setFloorarea(Integer floorarea) {
            this.floorarea = floorarea;
            return this;
        }

        public Builder setDistrictid(Integer districtid) {
            this.districtid = districtid;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setNumberofbasement(Integer numberofbasement) {
            this.numberofbasement = numberofbasement;
            return this;
        }

        public Builder setDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public Builder setLevel(Integer level) {
            this.level = level;
            return this;
        }

        public Builder setAreafrom(Integer areafrom) {
            this.areafrom = areafrom;
            return this;
        }

        public Builder setAreato(Integer areato) {
            this.areato = areato;
            return this;
        }

        public Builder setRentpriceto(Integer rentpriceto) {
            this.rentpriceto = rentpriceto;
            return this;
        }

        public Builder setRentpricefrom(Integer rentpricefrom) {
            this.rentpricefrom = rentpricefrom;
            return this;
        }

        public Builder setManagername(String managername) {
            this.managername = managername;
            return this;
        }

        public Builder setManagerphonenumber(String managerphonenumber) {
            this.managerphonenumber = managerphonenumber;
            return this;
        }

        public Builder setStaffid(Integer staffid) {
            this.staffid = staffid;
            return this;
        }

        public Builder setTypecode(List<String> typecode) {
            this.typecode = typecode;
            return this;
        }

        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }
    }
}
