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
        /*
        Hobby hobby1 = em.find(Hobby.class,"Skuespil");
       Hobby hobby2 = em.find(Hobby.class,"Bowling");
        
    */
        CityInfo ci1 = em.find(CityInfo.class, "2730");
        CityInfo ci2 = em.find(CityInfo.class, "2900");
      
       
        Person p1 = new Person("Mathias","Drejer","MathiasDenBedste@email.dk");
        Person p2 = new Person("Sebastian","Engelbrecht","RandomEmail@email.dk");
        Person p3 = new Person("Tobias","Linge","SejeTobias@email.dk");
    
        Phone phone1 = new Phone("22346520","MathiasTlf");
        Phone phone2 = new Phone("90285299","SebbeTelef");
        Phone phone3 = new Phone("11121212","TobbeTelefon");
        
        Address a1 = new Address("Sejevej 1", "random");
        Address a2 = new Address("denBedsteVej 2", "random");
       
        a1.setCityInfo(ci1);
        ci2.addAddress(a2);
      
        p1.setAddress(a1);
        a2.addPerson(p2);
        p3.setAddress(a1);
        
        p1.addPhone(phone1);
        p1.addPhone(phone2);
        p2.addPhone(phone3);
        
        /*
       p1.addHobbies(hobby1);
       p2.addHobbies(hobby2);
       p3.addHobbies(hobby1);
       */
    em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
    em.getTransaction().commit();
    em.close();
    
        //p1.getHobbyList().forEach(x -> System.out.println(x));
       // hobby1.getPersonList().forEach(x -> System.out.println("persons from hobbies "+x));
        
        System.out.println("-------------------------------------");
        
        a1.getPersons().forEach(x -> System.out.println("persons from address A1 "+a1.getId()+"   ---   "+x));
        System.out.println(p2.getAddress());
       
        System.out.println("ZIP-ADDRESS CHECK");
        System.out.println(p1.getAddress().getCityInfo().getZipCode());
        ci1.getAddressList().forEach(x->x.getPersons().forEach(y->System.out.println(y.getFirstName())));
    }

    
    public static void main(String[] args) {
       populate();
    }

    
}
