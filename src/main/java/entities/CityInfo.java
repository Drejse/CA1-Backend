/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dtos.CityInfoDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mathias
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CityInfo.deleteAllRows", query = "DELETE from CityInfo")})
public class CityInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Size(max = 4)
    @Column(name = "zipcode", length = 4, unique = true)
    private String zipCode;
    @Column(name = "city", length=35, unique = false)
    private String city;
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE, CascadeType.MERGE}, mappedBy = "cityInfo")
    private List<Address> addressList;

    public CityInfo() {
    }

    public CityInfo(String zipCode,String city) {
        this.zipCode = zipCode;
        this.city = city;
        this.addressList = new ArrayList<>();
    }
    

  public CityInfo(String zipCode) {
    this.zipCode = zipCode;
  }

  public CityInfo(CityInfoDTO dto){
    this.zipCode = dto.getZipCode();
    this.city = dto.getCity();
  }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlTransient
    public Collection<Address> getAddressList() {
        return addressList;
    }

   

   public void addAddress(Address address) {
        if (address != null){
            this.addressList.add(address);
            //Makes the relationship bi-directional
            address.setCityInfo(this);
        }
    }
    
    
    public void removeAddress(Address address) {
        if (address != null){
            this.addressList.remove(address);
          
            //Cascade will remove phone from DB.
            address.setCityInfo(null);
        }
    }

  
    
}
