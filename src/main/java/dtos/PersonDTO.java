/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;


import entities.Person;
import java.util.List;

/**
 *
 * @author madr1
 */
public class PersonDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDTO adress;

     private List<PhoneDTO> phoneList;
    private List<HobbyDTO> hobbyList;
    public PersonDTO() {
    }
    
    public PersonDTO(Person person) {
         if (person.getId() != null) {
            this.id = person.getId();
        }
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phoneList = PhoneDTO.getDtos(person.getPhoneList());
        this.hobbyList = HobbyDTO.getDtos(person.getHobbyList());
        
        
    }
    
     public PersonDTO(String fn,String ln, String email) {
        this.email = email;
        this.firstName = fn;
        this.lastName = ln;
       
        
    }

    public List<PhoneDTO> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<PhoneDTO> phoneList) {
        this.phoneList = phoneList;
    }

    public List<HobbyDTO> getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(List<HobbyDTO> hobbyList) {
        this.hobbyList = hobbyList;
    }
     
     
     

    public AddressDTO getAddress() {
        return adress;
    }

    public void setAdress(AddressDTO adress) {
        this.adress = adress;
    }

     
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    
    
}
