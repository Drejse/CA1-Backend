/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import dtos.AddressDTO;
import dtos.PersonDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;



import utils.EMF_Creator;
/**
 *
 * @author mathias
 */

//Uncomment the line below, to temporarily disable this test

@Disabled

//@Disabled

public class DataBaseFacadeTest {

public DataBaseFacadeTest(){}

     private static EntityManagerFactory emf;
    private static DatabaseFacade facade;
    private static PersonFacade personFacade;
     /*
    private Person p1 = new Person("Mathias", "Drejer", "m@email.dk");
    private Person p2 = new Person("Sebastian", "Ebrect", "s@email.dk");
    private Phone phone1 = new Phone("55555555", "mathiasTele");;
    private Phone phone2 = new Phone("66666666", "sebastianTele");
    private Address a1 = new Address("Coolvej 15", "");
    private CityInfo ci1 = new CityInfo("2000", "Frederiksberg");
    private Hobby hobby1 = new Hobby("Fodbold", "http://www.test1.com", "cat1", "type1");
    private Hobby hobby2 = new Hobby("Baseball", "http://www.test2.com", "cat2", "type2");
    */
    
    private static Person p1, p2, p3;
    private static Hobby hobby1,hobby2;
    private static CityInfo ci1,ci2;
    private static Phone phone1,phone2,phone3;
    private static Address a1,a2;
       
        
       @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = DatabaseFacade.getDatabaseFacade(emf);
       personFacade = PersonFacade.getPersonFacade(emf);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    
    @BeforeEach
    public void setUp(){
        EntityManager em = emf.createEntityManager();
  
            hobby1 = new Hobby("Fodbold", "http://www.test1.com", "cat1", "type1");
            hobby2 = new Hobby("Baseball", "http://www.test2.com", "cat2", "type2");

            ci1 = new CityInfo("Monaco", "random1");
            ci2 = new CityInfo("Frankfurt", "random2");

            p1 = new Person("test@email.dk", "MathiasTEST", "DrejerTEST");
            p2 = new Person("test2@email.dk", "SebastianTEST", "EbrechtTEST");
            p3 = new Person("test3@email.dk", "TobiasTEST", "LingeTEST");

            phone1 = new Phone(20202020, "mathiasTele");
            phone2 = new Phone(30303030, "sebastianTele");
            phone3 = new Phone(40404040, "TobbeTele");

            a1 = new Address("TesterVejen40", "sejestevej1");
            a2 = new Address("OmegaTesterVej", "coolestevej2");

            a1.setCityInfo(ci1);
            ci2.addAddress(a2);

            p1.setAddress(a1);
            a2.addPerson(p2);
            p3.setAddress(a1);

            p1.addPhone(phone1);
            p1.addPhone(phone2);
            p2.addPhone(phone3);

            p1.addHobbies(hobby1);
            p1.addHobbies(hobby2);
            p2.addHobbies(hobby1);
            
            try {
            em.getTransaction().begin();
                em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
                em.createNamedQuery("Phone.deleteAllRows").executeUpdate();
                em.createNamedQuery("Person.deleteAllRows").executeUpdate();
                em.createNamedQuery("Address.deleteAllRows").executeUpdate();
                em.createNamedQuery("CityInfo.deleteAllRows").executeUpdate();
                
                
                em.persist(hobby1);
                em.persist(hobby2);
                em.persist(ci1);
                em.persist(ci2);
                
                
                em.persist(p1);
                em.persist(p2);
                em.persist(p3);
            em.getTransaction().commit();
            }
            finally{
            em.close();
                    }
    }
  /*
         @Test
    void testAddPhone() {
        p1.addPhone(phone1);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        assertEquals(55555555, p1.getPhoneList().get(0).getNumber());
    }
    
   
  @Test
    public void testAddPerson() throws Exception {
        System.out.println("createPerson");
        Person newPerson = new Person("email","firstname","lastname");
        newPerson = facade.createPerson(newPerson);
        assertNotNull(newPerson.getId());
    }
    
    @Test
    void testRemovePhone() {
        phone1.setPerson(null);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(phone1);
            em.getTransaction().commit();
            p1 = em.find(Person.class, p1.getId());
        } finally {
            em.close();
        }
        assertEquals(0, p1.getPhoneList().size());
    }
    
    
    @Test
    public void testEditPerson() throws Exception {
        PersonDTO actPDto = new PersonDTO(p1);
        PersonDTO newPDto = new PersonDTO("Updated fn","Updated ln","updateEmail");
        newPDto.setId(p1.getId());
        
        actPDto = facade.editPerson(newPDto);
        assertEquals(actPDto, newPDto);
    }
    
    
    
     @Test
        void testAddPerson() throws Exception {
        System.out.println("createPerson");
        PersonDTO person = new Person("firstname","lastname","email");
        person = facade.createPerson(person);
        assertNotNull(person.getId());
    }
    

    @After
    public void tearDown() throws Exception {
    }


     
     @Test
    void testGetAllPersons() throws Exception {
        System.out.println("get all persons");
        int expResult = 3;
        List<Person> result = facade.getAllPersons();
        assertEquals(expResult, result.size());
    }

    //Author Tobias

    @Test
    public void testgetPersonByPhoneNumber() throws Exception {
        System.out.println("getPersonByPhoneNumber");
        String phoneNumber = p1.getPhoneList().get(0).getNumber();
        Person result = facade.getPersonFromPhoneNumber(phoneNumber);
        assertEquals(p1.getId(), result.getId());
    }

    // Author Tobias & Sebastian
    @Test
    public void testGetAllPersonsWithGivenHobby() throws Exception {
        System.out.println("get all persons with a given hobby");
        List<Person> hobbyTest = facade.getAllPersonsWithGivenHobby(hobby1.getName());
        int exp = 2;
        assertEquals(exp,hobbyTest.size());
    }

    @Test
    public void testGetAllPersonsWithGivenCity(String zipCode) throws Exception {
        System.out.println("Get all persons with a given City");
        List<Person> result = facade.getAllPersonsWithGivenHobby(ci1.getCity());
        int exp = 2;
        assertEquals(exp,result.size());
    }

    @Test
    public void testGetNumberOfPersonsWithGivenHobby () throws Exception {
        System.out.println("Number of persons with a given hobby");
        int count = facade.getNumberOfPersonsWithGivenHobby(hobby1.getName());
        assertEquals(2, count);
    }

    @Test
    public void testGetAllZipCodes() throws Exception {
        System.out.println("get all zipcodes");
        int expResult = 2;
        List<CityInfo> result = facade.getAllZipCodes();
        assertEquals(expResult, result.size());
    }
 
*/

}
 