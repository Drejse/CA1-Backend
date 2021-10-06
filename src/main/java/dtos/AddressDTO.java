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
    private CityInfoDTO cityInfo;
    
    public AddressDTO() {}
    
   
     
    public AddressDTO(Address a) {
        this.id = a.getId() == null ? null : a.getId();
        this.street = a.getStreet() == null ? null : a.getStreet();
        this.additionalInfo = a.getAdditionalInfo();
        this.cityInfo = a.getCityInfo() == null ? null : new CityInfoDTO(a.getCityInfo());
    }
    
     public AddressDTO(String street, String additionalInfo) {
    this.street = street;
    this.additionalInfo = additionalInfo;
  }
     
       public AddressDTO(String street, String additionalInfo, CityInfoDTO cityInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityInfo = cityInfo;
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

    public CityInfoDTO getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfoDTO cityInfo) {
        this.cityInfo = cityInfo;
    }

 

 
    
    
}
