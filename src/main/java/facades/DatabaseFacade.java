package facades;

import dtos.CityInfosDTO;
import dtos.HobbyDTO;
import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
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

    private HobbyDTO createHobby(String name, String wikiLink, String category, String type) {
    EntityManager em = emf.createEntityManager();
    Hobby hobby = new Hobby();
    try {
    em.getTransaction().begin();
            hobby.setName(name);
            hobby.setWikiLink(wikiLink);
            hobby.setCategory(category);
            hobby.setType(type);
            em.persist(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new HobbyDTO(hobby);
  }
    
  public PersonDTO createPerson(PersonDTO personDTO) {
   
      Person person = null;
      List<HobbyDTO> hobbies = personDTO.getHobbyList();
      List<HobbyDTO> h2 = new ArrayList<>();
      personDTO.setHobbyList(h2);
      EntityManager em = emf.createEntityManager();
      try {
        person = new Person(personDTO);

        em.getTransaction().begin();
        if(person.getAddress() != null && person.getAddress().getCityInfo() != null){
            Address a = person.getAddress();
            CityInfo ci = a.getCityInfo();
            em.persist(ci);
            a.setCityInfo(ci);
            em.persist(a);
        }
        if (person.getPhoneList() != null) {
          for (Phone p : person.getPhoneList()) {
            
              em.persist(p);
              p.setPerson(person);
              em.merge(p);
            
          }
        }

        em.persist(person);

        if(person.getHobbyList() != null){
          for(HobbyDTO h: hobbies){
            HobbyDTO hobby = createHobby(h.getName(), h.getWikiLink(), h.getCategory(), h.getType());
            em.find(Hobby.class, hobby.getId());
            Hobby hobbyEntity = new Hobby(hobby);
            person.addHobbies(hobbyEntity);
            em.merge(person);
          }
        }

        em.merge(person);

        em.getTransaction().commit();

      } finally {
        em.close();
      }
      return new PersonDTO(person);
  }
  

  
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
    
        public PersonDTO getPersonFromPhoneNumber(int number)throws Exception {
        EntityManager em = emf.createEntityManager();
        Person person = null;
      
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.phoneList h WHERE h.number = :number",Person.class);

        query.setParameter("number", number);
        person = query.getSingleResult();
        
        return new PersonDTO(person);
    }
    
         
    
        public PersonsDTO getAllPersonsWithGivenHobby(String hobby)throws Exception{
        EntityManager em = emf.createEntityManager();
        List<Person> personList;
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbyList h WHERE h.name = :name",Person.class);
        query.setParameter("name", hobby);
        personList = query.getResultList();
        return new PersonsDTO(personList);
        
    }
        
    
        public PersonsDTO getAllPersonsWithGivenCity(String zipCode)throws Exception{
        EntityManager em = emf.createEntityManager();
        List<Person> personList;
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.address a join a.cityInfo c where c.zipCode = :zip",Person.class);
        query.setParameter("zip", zipCode);
        personList = query.getResultList();
        return new PersonsDTO(personList);
        } 
   
  
        public int getNumberOfPersonsWithGivenHobby(String hobby)throws Exception{
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT COUNT(distinct p) from Person p INNER JOIN p.hobbyList h where h.name = :hobbyName");
        query.setParameter("hobbyName", hobby);
        Long result = (Long) query.getSingleResult();
        return result.intValue();
    }
        
        
        
        public CityInfosDTO getAllZipCodes() throws Exception{
        EntityManager em = emf.createEntityManager();
        List<CityInfo> cityInfos;
        TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo c", CityInfo.class);
        cityInfos = query.getResultList();
        return new CityInfosDTO(cityInfos);

    }


        
}

   