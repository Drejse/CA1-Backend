package dtos;

import entities.CityInfo;


 //@author sebastianengelbrecht


public class CityInfoDTO{
    
    private String city;
    private String zipCode;

    public CityInfoDTO() {
    }
    
    public CityInfoDTO(CityInfo c){
        this.city = c.getCity();
        this.zipCode = c.getZipCode();
    }

    public CityInfoDTO(String city, String zipCode) {
        this.city = city;
        this.zipCode = zipCode;
    }
    
 

    public String getCity() {
        return city;
    }

    public void setCity(String City) {
        this.city = City;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    
}