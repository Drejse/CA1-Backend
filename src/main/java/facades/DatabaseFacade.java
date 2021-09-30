package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author 45319
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
    
    public PersonDTO addPerson(String firstName, String lastName, String email) { 
        Person p = new Person(firstName,lastName,email);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(p);
    }

   
    public PersonDTO deletePerson(int id) throws Exception {
        EntityManager em = emf.createEntityManager();
        Person person;
        try {
            em.getTransaction().begin();
                person = em.find(Person.class, id);
                em.remove(person);
                em.getTransaction().commit();
            return new PersonDTO(person);
        } finally {
            em.close();
        }
        
    }

    
    public PersonDTO getPerson(int id) throws Exception {
        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, id);
      
            return new PersonDTO(p);
        
     
    }

    
    public PersonsDTO getAllPersons() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = query.getResultList();
        return new PersonsDTO(persons);
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
    
        public List<Person> getPersonFromPhoneNumber(String number) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.address h WHERE p.number = :number",Person.class);
        List<Person> person = query.setParameter("number", number).getResultList();
        return person;
    }
    
    
    
        public List<Person> getAllPersonsWithGivenHobby(String hobby){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbyList h WHERE h.name = :name",Person.class);
        query.setParameter("name", hobby);
        return query.getResultList();
        
    }
        
    
        public List<Person> getAllPersonsWithGivenCity(String zipCode){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.address a WHERE a.cityInfoid.city = :city",Person.class);
        query.setParameter("city", zipCode);
        return query.getResultList();
        } 
   
  
        public int getNumberOfPersonsWithGivenHobby(String hobby){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT COUNT(distinct p) from Person p INNER JOIN p.hobbyList h where h.name = :hobbyName");
        query.setParameter("hobbyName", hobby);
        Long result = (Long) query.getSingleResult();
        return result.intValue();

    }
        
}

   