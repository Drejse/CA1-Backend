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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;



import utils.EMF_Creator;
/**
 *
 * @author mathias
 */

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class DataBaseFacadeTest {


    private static EntityManagerFactory emf;
    private static DatabaseFacade facade;
    private static PersonFacade personFacade;
    private static Person p1,p2,p3;
    private static Hobby hobby1,hobby2;
    private static CityInfo ci1,ci2;
    private static Phone phone1,phone2,phone3;
    private static Address a1,a2;
       public DataBaseFacadeTest(){
    
}
        
       @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = DatabaseFacade.getDatabaseFacade(emf);
       personFacade = PersonFacade.getPersonFacade(emf);
    }
    
    
    @BeforeEach
    public void setUp(){
        EntityManager em = emf.createEntityManager();
        
            hobby1 = new Hobby("Hobby1", "http://www.test1.com", "cat1", "type1");
            hobby2 = new Hobby("Hobby2", "http://www.test2.com", "cat2", "type2");

            ci1 = new CityInfo("testby1", "testby1");
            ci2 = new CityInfo("testby2", "testby1");

            p1 = new Person("m@email.dk", "Mathias", "Drejer");
            p2 = new Person("s@email.dk", "Sebastian", "Ebrecht");
            p3 = new Person("t@email.dk", "Tobias", "Linge");

            phone1 = new Phone("55555555", "mathiasTele");
            phone2 = new Phone("66666666", "sebastianTele");
            phone3 = new Phone("77777777", "TobbeTele");

            a1 = new Address("sejeTestvej1", "sejestevej1");
            a2 = new Address("coolTestvej2", "coolestevej2");

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
              
                //Persists Hobbies and CityInfo as we dont execute script in test.
                em.persist(hobby1);
                em.persist(hobby2);
                em.persist(ci1);
                em.persist(ci2);
                
                
                em.persist(p1);
                em.persist(p2);
                em.persist(p3);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }
 
    
     @Test
    public void testAddPerson() throws Exception {
        System.out.println("createPerson");
        Person person = new Person("firstname","lastname","email");
        person = facade.addPerson(person);
        assertNotNull(person.getId());
    }


    
     @Test
    public void testGetAllPersons() throws Exception {
        System.out.println("get all persons");
        int expResult = 3;
        List<Person> result = facade.getAllPersons();
        assertEquals(expResult, result.size());
    }
}
 