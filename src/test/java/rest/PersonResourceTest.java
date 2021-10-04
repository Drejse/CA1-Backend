/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

/**
 *
 * @author Mathias
 */
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Phone;
import utils.EMF_Creator;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import dtos.PersonDTO;
import entities.Person;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    
//private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();    
    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    
    
    private static Person p1, p2;
    private static Hobby hobby1,hobby2;
    private static CityInfo ci1,ci2;
    private static Phone phone1,phone2;
    private static Address a1,a2;

    

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    
    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }


    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
            

            ci1 = new CityInfo("4600", "Køge");
            ci2 = new CityInfo("3450", "Allerød");

            a1 = new Address("sejeTestvej1", "sejestevej1");
            a2 = new Address("coolTestvej2", "coolestevej2");
            
            hobby1 = new Hobby("Fodbold", "http://www.test1.com", "cat1", "type1");
            hobby2 = new Hobby("Ballet", "http://www.test2.com", "cat2", "type2");
            
            List<Hobby> hl1 = new ArrayList<>();
            List<Hobby> hl2 = new ArrayList<>();
            hl1.add(hobby1);
            hl2.add(hobby1);
            hl2.add(hobby2);
            
            List<Phone> phl1 = new ArrayList<>();
            List<Phone> phl2 = new ArrayList<>();
            phone1 = new Phone("55555555", "mathiasTele");
            phone2 = new Phone("66666666", "sebastianTele");
            
            
            phl1.add(phone1);
            phl2.add(phone2);
            
            
            
            p1 = new Person("m@email.dk", "Mathias", "Drejer",phl1,a1,hl1);
            p2 = new Person("s@email.dk", "Sebastian", "Ebrecht",phl2,a2,hl2);
            

            

           

            a1.setCityInfo(ci1);
            ci2.addAddress(a2);

            p1.setAddress(a1);
            a2.addPerson(p2);
            

            p1.addPhone(phone1);
            p1.addPhone(phone2);
            

            p1.addHobbies(hobby1);
            p1.addHobbies(hobby2);
            p2.addHobbies(hobby1);
            
            try {
                // person 1
            em.getTransaction().begin();
                 //Address
                em.persist(ci1);
                a1.setCityInfo(ci1);
                em.persist(a1);
                //Phone
                em.persist(phone1);
                phone1.setPerson(p1);
                em.merge(phone1);
                //
                em.persist(p1);
                //Hobbies
                em.persist(hobby1);
                p1.addHobbies(hobby1);
                em.merge(p1);
                //
                em.getTransaction().commit();
                
                
                
                em.getTransaction().begin();
                //Address
                em.persist(ci2);
                a2.setCityInfo(ci2);
                em.persist(a2);
                //Phone
                em.persist(phone2);
                phone2.setPerson(p2);
                em.merge(phone2);
                //
                em.persist(p2);
                //Hobbies
                em.persist(hobby2);
                p2.addHobbies(hobby2);
                em.merge(p2);
                //
                em.getTransaction().commit();
            }
            finally{
            em.close();
                    }
    }
}
/*
    @Test
    public void testWrongURL() {
        given().when().get("/person/url/findes/ikke").then().statusCode(500);
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/person").then().statusCode(200);
    }
}
    
 /*   
    @Test
    public void testGetAllPersons() {
        given()
                .contentType("application/json")
                .get("/person").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("all", hasSize(4));
    }
    
    @Test
    public void testGetPersonById() {
        given()
                .contentType("application/json")
                .get("/person/"+p3.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("firstName", is(p3.getFirstName()));
    }
    
    
    @Test
    public void testAddPerson() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("firstName", "testPOST");
        requestParams.put("lastName", "test");
        requestParams.put("email", "test");

        given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/person")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("id", greaterThan(0));
    }
    
    
    @Test
    public void testUpdatePerson() {
        int id = p2.getId();
        
        PersonDTO pDTO = new PersonDTO("testPUT", "testlNamePUT", "Email");
        given()
                .contentType("application/json")
                //.body(requestParams.toString())
                .body(pDTO)
                .when()
                .put("/person/"+id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("id", is(id))
                .body("firstName", is("testPUT"));
    }
    
 
    @Test
    public void testDeletePerson() {
        int id = p2.getId();
        
        given()
                .contentType("application/json")
                .delete("/person/"+id).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("status", is("removed id:"+id));
    }
    
    
    /*
    //@Test
    public void testAddPersonFail() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("firstName", "testPOST");
        requestParams.put("lastName", "test");
        requestParams.put("email", "test");

        given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/person")
                .then()
                .assertThat()
                .statusCode(400);
    }
*/
    





