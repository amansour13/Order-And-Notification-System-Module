/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleCodeExample.Main;

import ConsoleCodeExample.model.Person;
import ConsoleCodeExample.service.PersonServiceImpl;

/**
 *
 * @author 20111
 */
public class Main {
    public static void main(String[] args) {

        PersonServiceImpl personServiceImpl = new PersonServiceImpl();
        
        Person p1 = new Person();
        p1.setName("Ahmed");
        p1.setAge(20);
        p1.setId(1);
        
        personServiceImpl.addPerson(p1);
        
        Person p2 = new Person();
        p2.setName("Mohamed");
        p2.setAge(30);
        p2.setId(2);
        
        personServiceImpl.addPerson(p2);
        
        System.out.println("get Person by id: 1");
        System.out.println(personServiceImpl.getPerson(1));
        
        System.out.println("get All Persons");
        Person [] persons = personServiceImpl.getAllPersons();
        for(Person person : persons){
            System.out.println(person);
        }
    }
}
