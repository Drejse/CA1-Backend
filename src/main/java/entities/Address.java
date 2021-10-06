/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dtos.AddressDTO;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mathias
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.deleteAllRows", query = "DELETE from Address")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Size(max = 45)
    @Column(name = "street")
    private String street;
    
    @Size(max = 45)
    @Column(name = "additionalInfo")
    private String additionalInfo;
   
  
    @OneToMany(mappedBy = "address", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Person> persons;

    @ManyToOne
    private CityInfo cityInfo;
    
    public Address() {
    }
    
     public Address(AddressDTO dto) {
        this.street = dto.getStreet();
        this.additionalInfo = dto.getAdditionalInfo();
        this.cityInfo = new CityInfo(dto.getCityInfo());
    }

    public Address(String street, String additionalInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityInfo = null;
    }
    
   public Address(String street,String additionalInfo, CityInfo cityInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityInfo = cityInfo;
    }

 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

     public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
        if(!cityInfo.getAddressList().contains(this)){
            //TODO: Make facade that checks if address is in DB and fetches it before just inserting a new one.
            cityInfo.addAddress(this);
        }
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }


   public void addPerson(Person person) {
        if (person != null){
            this.persons.add(person);
            //Makes the relationship bi-directional
            person.setAddress(this);
        }
    }
    
    public void removePerson(Person person) {
        if (person != null){
            this.persons.remove(person);
          
            //Cascade will remove phone from DB.
            person.setAddress(null);
        }
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", street=" + street + ", additionalInfo=" + additionalInfo + ", cityInfo=" + cityInfo + ", persons=" + persons + '}';
    }

        
    
}
