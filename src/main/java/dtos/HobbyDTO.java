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
    Integer id;
    private String name;
    private String wikiLink;
    private String category;
    private String type;
    
    public HobbyDTO() {}
    
    public HobbyDTO(Hobby hobby) {
        this.id = hobby.getId();
        this.name = hobby.getName();
        this.wikiLink = hobby.getWikiLink();
        this.category = hobby.getCategory();
        this.type = hobby.getType();
    }
    /*
     public static List<HobbyDTO> getDtos(List<Hobby> hobbyList) {
        List<HobbyDTO> hobbyDTO = new ArrayList();
        hobbyList.forEach(hobby -> hobbyDTO.add(new HobbyDTO(hobby)));
        return hobbyDTO;
    }*/
    
    public HobbyDTO(String name, String wikiLink, String category, String type) {
        this.name = name;
        this.wikiLink = wikiLink;
        this.category = category;
        this.type = type;
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

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "HobbyDTO{" + "name=" + name + ", wikiLink=" + wikiLink + ", category=" + category + ", type=" + type + '}';
    }
    
   
    
    
    
}
