/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;




/**
 *
 * @author madr1
 */
/*
public class PersonFacadeTester extends TestCase {
    
     private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private static Person p1,p2,p3,p4;
    
    public PersonFacadeTester(String testName) {
        super(testName);
    }
    
    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = PersonFacade.getPersonFacade(emf);
    }
    
    @Override
    protected void setUp() throws Exception {
        p1 = new Person("fName1", "lName1", "testEmailOne@email.dk"); 
        p2 = new Person("fName2", "lName2", "testEmailTwo@email.dk"); 
        p3 = new Person("fName3", "lName3", "testEmailThree@email.dk"); 
        p4 = new Person("fName4", "lName4", "testEmailFour@email.dk"); 
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
                em.persist(p1);
                em.persist(p2);
                em.persist(p3);
                em.persist(p4);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

   
    @Test
    public void testGetPerson() throws Exception {
        PersonDTO actPerson = facade.getPerson(p3.getId());
        assertEquals(actPerson.getId(),p3.getId());
    }

    @Test
    public void testGetAllPersons() throws Exception {
        PersonsDTO actPersons = facade.getAllPersons();
        assertEquals(actPersons.getAll().size(),4);
    }
    
    @Test
    public void testAddPerson() throws Exception {
        Person newP = new Person("Test","Add","EmailNrFive@email.dk");
        PersonDTO pDTO = facade.addPerson(newP.getFirstName(), newP.getLastName(), newP.getEmail());
        Assertions.assertNotNull(pDTO.getId());
    }
    
    @Test
    public void testEditPerson() throws Exception {
        PersonDTO actPDto = new PersonDTO(p3);
        PersonDTO newPDto = new PersonDTO("Updated firstName","Updated lastName","testEmailThree@email.dk");
        newPDto.setId(p3.getId());
        
        actPDto = facade.editPerson(newPDto);
        assertEquals(actPDto, newPDto);
    }
    
    @Test
    public void testDeletePerson() throws Exception {
       facade.deletePerson(p1.getId());
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
            Person p = em.find(Person.class, p1.getId());
       em.getTransaction().commit();
       em.close();
       Assertions.assertNull(p);
    }
}
*/

