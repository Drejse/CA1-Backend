/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.CityInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebastianengelbrecht
 */
public class CityInfosDTO {

      public static List<CityInfoDTO> getDTO(List<CityInfo> cityInfos) {
        List<CityInfoDTO> cityInfoDTO = new ArrayList();
        cityInfos.forEach(cityInfo -> cityInfoDTO.add(new CityInfoDTO(cityInfo)));
        return cityInfoDTO;
    }

    List<CityInfoDTO> all = new ArrayList();

    public List<CityInfoDTO> getAllCityInfo() {

        return all;
    }

}