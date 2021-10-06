/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dtos.PhoneDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mathias
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Phone.deleteAllRows", query = "DELETE from Phone")
})
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 45)
    @Column(name = "number", unique=true) // ensures that no dublicates can be made because number is unique.
    private int number;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @ManyToOne
    private Person person;

    public Phone() {
    }
    
    public Phone(int number) {
        this.number = number;
    }
    public Phone(int number, String description) {
        this.number = number;
        this.description = description;
    }
    
     public Phone(PhoneDTO phoneDTO) {
        this.number = phoneDTO.getNumber();
        this.description = phoneDTO.getDescription();
    }
    
     public static List<Phone> getPhoneList(List<PhoneDTO> _phoneList) {
        List<Phone> phones = new ArrayList();
        _phoneList.forEach(phoneDTO -> phones.add(new Phone(phoneDTO)));
        return phones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

  
    
}
