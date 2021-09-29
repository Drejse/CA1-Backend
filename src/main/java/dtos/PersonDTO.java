/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;


import entities.Person;

/**
 *
 * @author madr1
 */
public class PersonDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    

    public PersonDTO() {
    }
    
    public PersonDTO(Person p) {
        this.email = p.getEmail();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.id = p.getId();
    }
    
     public PersonDTO(String fn,String ln, String email) {
        this.email = email;
        this.firstName = fn;
        this.lastName = ln;
       
        
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


    
    
}
