/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dtos.PersonDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author madr1
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
})
   
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 45)
    private String firstName;
    @Size(max = 45)
    private String lastName;
    @Size(min = 1, max = 45)
    private String email;
    
    @ManyToMany(mappedBy = "personList",  cascade = CascadeType.PERSIST)
    private List<Hobby> hobbyList;
    
    @OneToMany(mappedBy = "person",  cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Phone> phoneList;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;

    public Person() {
    }

   

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hobbyList = new ArrayList<>();
        this.phoneList = new ArrayList<>();
        
    }
    
     public Person(String email, String firstName, String lastName,
      List<Phone> phoneList, Address address, List<Hobby> hobbyList) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneList = phoneList;
    this.address = address;
    this.hobbyList = hobbyList;
  }

 
    
     public Person updateFromDto(PersonDTO pDto){
        this.firstName = pDto.getFirstName();
        this.lastName = pDto.getLastName();
        
        return this;
    }

    public Integer getId() {
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

    public List<Hobby> getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(List<Hobby> hobbyList) {
        this.hobbyList = hobbyList;
    }

    public Address getAddress() {
        return address;
    }

      public void setAddress(Address address) {
        this.address = address;
        if(!address.getPersons().contains(this)){
            //TODO: Make facade that checks if address is in DB and fetches it before just inserting a new one.
            address.addPerson(this);
        }
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    
     public void addPhone(Phone phone) {
        if (phone != null){
            this.phoneList.add(phone);
            //Makes the relationship bi-directional
            phone.setPerson(this);
        }
    }
    
    public void removePhone(Phone phone) {
        if (phone != null){
            this.phoneList.remove(phone);
          
            //Cascade will remove phone from DB.
            phone.setPerson(null);
        }
    }
    
    
    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }


    public void addHobbies(Hobby hobby) {
        if (hobby != null){
            this.hobbyList.add(hobby);
            //Makes the relationship bi-directional
            hobby.getPersonList().add(this);
        }
    }
    
    public void removeHobby(Hobby hobby) {
        if (hobby != null){
            this.hobbyList.remove(hobby);
            hobby.getPersonList().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", hobbyCollection=" + hobbyList + ", address=" + address + ", phoneCollection=" + phoneList + '}';
    }

   
    
    
}
