package facades;

import dtos.HobbyDTO;
import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mathias
 */
public class DatabaseFacade {
    


    private static DatabaseFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private DatabaseFacade() {}
    
    public static DatabaseFacade getDatabaseFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DatabaseFacade();
        }
        return instance;
    }

    
    
    /*
    public PersonDTO addPerson(PersonDTO personDTO){
        Person person = null;
        List<HobbyDTO> hobbyList = personDTO.getHobbyList();
    }*/
    
    
    
    
    
    
    
     
   
    public void deletePerson(int id) throws Exception {
        EntityManager em = emf.createEntityManager();
        Person person;
        try {
            em.getTransaction().begin();
                person = em.find(Person.class, id);
                if(person == null){
                    throw new Exception("could not delete, no id found");
                }
                em.remove(person);
                em.getTransaction().commit();
            
        } finally {
            em.close();
        }
        
    }

    
    public PersonDTO getPerson(int id) throws Exception {
        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, id);
      
            return new PersonDTO(p);
        
     
    }

    
    public List<Person> getAllPersons() throws Exception{
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = query.getResultList();
        return persons;
    }

    
    public PersonDTO editPerson(PersonDTO p) throws Exception {
        
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
                Person tempPerson = em.find(Person.class, p.getId());
                tempPerson = tempPerson.updateFromDto(p);
            em.getTransaction().commit();
            return new PersonDTO(tempPerson);
        } finally {
            em.close();
        }
    }
    
        public Person getPersonFromPhoneNumber(String number)throws Exception {
        EntityManager em = emf.createEntityManager();
        Person person = null;
        
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.address h WHERE p.number = :number",Person.class);
        query.setParameter("number", number);
        person = query.getSingleResult();
        
        return person;
    }
    
         
    
        public List<Person> getAllPersonsWithGivenHobby(String hobby)throws Exception{
        EntityManager em = emf.createEntityManager();
        List<Person> personList;
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbyList h WHERE h.name = :name",Person.class);
        query.setParameter("name", hobby);
        personList = query.getResultList();
        return personList;
        
    }
        
    
        public List<Person> getAllPersonsWithGivenCity(String zipCode)throws Exception{
        EntityManager em = emf.createEntityManager();
        List<Person> personList;
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.address a join a.cityInfo c where c.zipCode = :zip",Person.class);
        query.setParameter("zip", zipCode);
        personList = query.getResultList();
        return personList;
        } 
   
  
        public int getNumberOfPersonsWithGivenHobby(String hobby)throws Exception{
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT COUNT(distinct p) from Person p INNER JOIN p.hobbyList h where h.name = :hobbyName");
        query.setParameter("hobbyName", hobby);
        Long result = (Long) query.getSingleResult();
        return result.intValue();
    }
        
        
        
        public List<CityInfo> getAllZipCodes() throws Exception{
        EntityManager em = emf.createEntityManager();
        List<CityInfo> cityInfos;
        TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo c", CityInfo.class);
        cityInfos = query.getResultList();
        return cityInfos;

    }
        
        /*
         public long getPersonCount() {
          EntityManager em = emf.createEntityManager();
          try {
            return (long) em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
          } finally {
            em.close();
          }
        }
*/

    

   
        
}

   