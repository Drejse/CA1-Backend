/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Address;
import entities.CityInfo;

/**
 *
 * @author 45319
 */
public class AddressDTO {
    private Integer id;
    private String street;
    private String additionalInfo;
    private CityInfo cityInfoId;
    
    public AddressDTO() {}
    
    public AddressDTO(Address a) {
        this.id = a.getId();
        this.street = a.getStreet();
        this.cityInfoId = a.getCityInfo();
        this.additionalInfo = a.getAdditionalInfo();
    }
    
    public AddressDTO(String street, String additionalInfo, CityInfo cityInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityInfoId = cityInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public CityInfo getCityInfo() {
        return cityInfoId;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfoId = cityInfo;
    }
    
    
}
