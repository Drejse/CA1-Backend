package dtos;

import entities.CityInfo;
import java.util.ArrayList;
import java.util.List;

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
    
    public static List<CityInfoDTO> getDTO(List<CityInfo> cityInfos) {
        List<CityInfoDTO> cityInfoDTO = new ArrayList();
        cityInfos.forEach(cityInfo -> cityInfoDTO.add(new CityInfoDTO(cityInfo)));
        return cityInfoDTO;
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