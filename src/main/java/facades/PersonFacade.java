/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.JsonObject;
import dtos.CityInfosDTO;
import dtos.HobbiesDTO;
import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Address;
import entities.CityInfo;
import entities.Person;
import static facades.DatabaseFacade.getDatabaseFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mathias
 */
public class PersonFacade {
    
    private static DatabaseFacade databaseFacade;
    private static PersonFacade personFacade;
    
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf){
        if(personFacade == null){
            personFacade = new PersonFacade();
            databaseFacade = getDatabaseFacade(_emf);
        }
        return personFacade;
    }
    
    public PersonDTO getPersonFromPhoneNumber(int number) throws Exception{
        return databaseFacade.getPersonFromPhoneNumber(number);

       
    }
    
    public PersonDTO createPerson(PersonDTO personDTO){
        return databaseFacade.createPerson(personDTO);

    }
    
    public PersonDTO getAllPersons() throws Exception {
        return (PersonDTO) databaseFacade.getAllPersons();
    }
    
    public CityInfosDTO getAllZipCodes() throws Exception {
        return databaseFacade.getAllZipCodes();
    }
   
     
     public HobbiesDTO getAllHobbies() throws Exception {
         return databaseFacade.getAllHobbies();
     }
    
    public PersonsDTO getAllPersonsWithGivenCity(String zipCode) throws Exception{
        return databaseFacade.getAllPersonsWithGivenCity(zipCode);
    }
 
   
    public PersonsDTO getAllPersonsWithGivenHobby(String hobby) throws Exception{
        return databaseFacade.getAllPersonsWithGivenHobby(hobby);
    }
    
    public JsonObject getNumberOfPersonsWithGivenHobby(String hobby) throws Exception{
        JsonObject json = new JsonObject();
        int amount = databaseFacade.getNumberOfPersonsWithGivenHobby(hobby);
        json.addProperty("amount", amount);
        return json;
    }
    
      public PersonDTO addPerson(PersonDTO personDTO) throws Exception{
        //convert to Person and address
        Person person = new Person(personDTO);
        Address address = new Address(personDTO.getAddress());
        System.out.println("adddress" +address);

        
        try {
            
            address = databaseFacade.getAddress(address);
        } catch (Exception e) {
            
        }

        //Link address and person
        //Husk alle hobbies og phones
        person.setAddress(address);
        //Persist and return person
        return new PersonDTO(databaseFacade.createPerson(person));        
    }
      
      ////////////////////////////////////////////////////////////////////////
      public PersonDTO tilfojPerson(PersonDTO personDTO) throws Exception{
        //convert to Person and address
        Person person = new Person(personDTO);
        Address address = new Address(personDTO.getAddress());
        System.out.println("adddress" +address);

        
        try {
            //check if address exist
            address = databaseFacade.getAddress(address);
        } catch (Exception e) {
            //if not exits create address
            
        }

        //Link address and person
        //Husk alle hobbies og phones
        person.setAddress(address);
        //Persist and return person
        return new PersonDTO(databaseFacade.addPerson(person));        
    }
    
    
    
    
   

    
    
}
