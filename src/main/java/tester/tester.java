/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author madr1
 */
public class tester {


    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityManager em = emf.createEntityManager();
    
    Person p1 = new Person("jønke", "Larsen","jønkeThebeast@email.dk");
    
    em.getTransaction().begin();
        em.persist(p1);
    em.getTransaction().commit();
    
    System.out.println("Lad os se om to vejs virker " + p1.getFirstName());
    }

    
}
