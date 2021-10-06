/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import utils.EMF_Creator;
/**
 *
 * @author madr1
 */
public class Populator {

    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
    
        //Hobby hobby1 = em.find(Hobby.class,hobby1.getId);
      
        
 
        CityInfo ci1 = em.find(CityInfo.class, "0800");

      
       
        Person p1 = new Person("Ebbesand","Fyrste","FyrstenEbbesand@email.dk");
    
       Phone phone1 = new Phone(99898989,"ebbeTlf");
        
       Address a1 = new Address("Ebbevej 5", "Ebbeperium");
       
       a1.setCityInfo(ci1);

      
       p1.setAddress(a1);

        
       p1.addPhone(phone1);

        
        /*
       p1.addHobbies(hobby1);
       p2.addHobbies(hobby2);
       p3.addHobbies(hobby1);
       */
    em.getTransaction().begin();
       // em.persist(p1);

    em.getTransaction().commit();
    em.close();
    
    
  
    
    }

    
    public static void main(String[] args) {
       populate();
    }

    
}
