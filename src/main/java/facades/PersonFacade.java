/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.JsonObject;
import dtos.CityInfosDTO;
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
    
    public PersonDTO getPersonFromPhoneNumber(String number) throws Exception{
        return databaseFacade.getPersonFromPhoneNumber(number);
    }
    
    public List<Person> getAllPersons() throws Exception {
        return databaseFacade.getAllPersons();
    }
    
    public CityInfosDTO getAllZipCodes() throws Exception {
        return databaseFacade.getAllZipCodes();
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

    
    
}
