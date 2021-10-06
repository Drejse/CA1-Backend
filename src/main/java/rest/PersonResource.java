/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import entities.Person;
import facades.DatabaseFacade;
import facades.PersonFacade;
import java.util.List;
//import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.graalvm.compiler.word.Word.Operation;
import utils.EMF_Creator;


/**
 *
 * @author madr1
 */


@Path("person")

public class PersonResource {

    private final EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
       
    private final DatabaseFacade facade =  DatabaseFacade.getDatabaseFacade(emf);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
          
    @GET
    @Produces({MediaType.APPLICATION_JSON}) 
    public Response getServerIsUp() {
        return Response.ok(gson.toJson("WELCOME!"), MediaType.APPLICATION_JSON).build();
    }
    

  
        
    @Path("/allpersons")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() throws Exception{
        List<Person> persons = facade.getAllPersons();
        return Response.ok(gson.toJson(persons), MediaType.APPLICATION_JSON).build();
    }
    
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonById(@PathParam("id") int id) throws Exception {
        PersonDTO pdto = facade.getPerson(id);
        return Response.ok(gson.toJson(pdto), MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
        public PersonDTO createNewPerson(PersonDTO p) {
        return facade.createPerson(p);

  } 
   
          @Path("/number/{number}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonFromPhoneNumber(@PathParam("number")int number) throws Exception{
        return Response.ok(gson.toJson(facade.getPersonFromPhoneNumber(number)),MediaType.APPLICATION_JSON).build();
    }
   
      //Author Sebastian, Mathias.
    @Path("/byhobby/{hobby}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersonsByGivenHobby(@PathParam("hobby") String hobby) throws Exception{
        return Response.ok(gson.toJson(facade.getAllPersonsWithGivenHobby(hobby)), MediaType.APPLICATION_JSON).build();
    }

    //Author Sebastian & Tobias
    @Path("/zip/{zipcode}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersonsByGivenCity(@PathParam("zipcode") String zipCode) throws Exception{
        return Response.ok(gson.toJson(facade.getAllPersonsWithGivenCity(zipCode)), MediaType.APPLICATION_JSON).build();
    }
 
    @Path("/zip/allzip")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllZipCodes() throws Exception{
        return Response.ok(gson.toJson(facade.getAllZipCodes()), MediaType.APPLICATION_JSON).build();
    }
   /**/


    /**
     *
     * @param hobby
     * @return 
     * @throws java.lang.Exception 
     */


    @Path("/byhobby/number/{personwithhobby}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfPersonsWithGivenHobby(@PathParam("personwithhobby") String hobby) throws Exception{
        return Response.ok(gson.toJson(facade.getNumberOfPersonsWithGivenHobby(hobby)), MediaType.APPLICATION_JSON).build();
    }




}
    /*
       @Path("{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson (@PathParam("id")int id, String person) throws Exception{
        PersonDTO pDTO = gson.fromJson(person, PersonDTO.class);
        pDTO.setId(id);
        pDTO = facade.editPerson(pDTO);
        return Response.ok(gson.toJson(pDTO), MediaType.APPLICATION_JSON).build();
    }
   /*
    @Path("{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePerson (@PathParam("id")int id) throws Exception{
        PersonDTO pDto = facade.deletePerson(id);
        return Response.ok("{\"status\" : \"removed id:"+pDto.getId()+"\"}", MediaType.APPLICATION_JSON).build();
    }
*/
}



