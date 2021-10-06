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
    private int number;
    private String description;
    
    public PhoneDTO() {}
    
    public PhoneDTO(Phone p) {
        this.number = p.getNumber();
        this.description = p.getDescription();
    }
    public PhoneDTO(int number) {
        this.number = number;
    }
    public PhoneDTO(int number, String description) {
        this.number = number;
        this.description = description;
    }

      public static List<PhoneDTO> getDtos(List<Phone> phones) {
        List<PhoneDTO> phoneDTO = new ArrayList();
        phones.forEach(phone -> phoneDTO.add(new PhoneDTO(phone)));
        return phoneDTO;
    }
      

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
