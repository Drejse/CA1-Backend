/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Address;
import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author madr1 & Sebastian
 */
public class PersonFacade{
    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
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
    
    /*
    @Override
    public PersonsDTO getAllPersonsWithGivenHobby(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbyCollection h WHERE h.name = :name",Person.class);
        List<Person> persons = query.getResultList();
        return new PersonsDTO(persons);
    }
        
    @Override
    public PersonsDTO getAllPersonsWithGivenCity(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.addressCollection a WHERE a.cityInfoid.city = :city",Person.class);
        List<Person> persons = query.getResultList();
        return new PersonsDTO(persons);    
    }*/
    
    /*@Override
    public PersonsDTO getNumberOfPersonsWithGivenHobby(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT COUNT(h.name) p FROM Person p JOIN p.hobbyCollection h",Person.class);
        System.out.println("Persons with given hobby is: " + query.getSingleResult());
        return PersonsDTO(x); 
        
    }
    //TODO
    // facades */
  
}
