/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Phone;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author 45319
 */
public class PhoneDTO {
    private int id;
    private String number;
    private String description;
    
    public PhoneDTO() {}
    
    public PhoneDTO(Phone p) {
        this.id = p.getId();
        this.number = p.getNumber();
        this.description = p.getDescription();
    }
    
    public PhoneDTO(String number, String description) {
        this.number = number;
        this.description = description;
    }

      public static List<PhoneDTO> getDtos(List<Phone> phones) {
        List<PhoneDTO> phonesDTO = new ArrayList();
        phones.forEach(phone -> phonesDTO.add(new PhoneDTO(phone)));
        return phonesDTO;
    }
      
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
