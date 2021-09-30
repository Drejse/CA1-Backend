/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Hobby;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 45319
 */
public class HobbyDTO {
    private Integer id;
    private String name;
    private String description;
    
    public HobbyDTO() {}
    
    public HobbyDTO(Hobby h) {
        this.id = h.getId();
        this.name = h.getName();
    }
    
    public HobbyDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    
   

    public static List<HobbyDTO> getDTO(List<Hobby> hobbies) {
        List<HobbyDTO> hobbyDTO = new ArrayList();
        hobbies.forEach(hobby -> hobbyDTO.add(new HobbyDTO(hobby)));
        return hobbyDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
