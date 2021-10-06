/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;


import entities.Person;
import java.util.ArrayList;
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
    private AddressDTO address;
   

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
        //this.phoneList = PhoneDTO.getDtos(person.getPhoneList());
         this.phoneList = person.getPhoneList() != null ? person.getNumberDTOList(person.getPhoneList()) : new ArrayList<>();
        this.hobbyList = person.getHobbyList() != null ? person.getHobbyDTOList(person.getHobbyList()) : new ArrayList<>();
        this.address = person.getAddress() == null ? new AddressDTO() : new AddressDTO(person.getAddress());
        
        
    }
    
    public PersonDTO(String email, String firstName, String lastName, List<PhoneDTO> phones) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneList = phones;
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
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
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
