/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import static facades.DatabaseFacade.getDatabaseFacade;
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
    /*
    public PersonDTO getPersonFromPhoneNumber(String number) throws Exception{
        return new PersonDTO(databaseFacade.getPersonByPhoneNumber(number));
        
    }
*/
    /*
    public PersonDTO getAllPersonsWithGivenHobby(String hobby){
        return new PersonDTO(databaseFacade.getAllPersonsWithGivenHobby(hobby));
    }
*/
    
    
}
