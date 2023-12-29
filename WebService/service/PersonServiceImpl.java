/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsoleCodeExample.service;

import ConsoleCodeExample.model.Person;
import static ConsoleCodeExample.util.Common.persons;
import java.util.Set;


public class PersonServiceImpl implements PersonService {

    @Override
    public Boolean addPerson(Person p) {
        try {
            if(persons.get(p.getId()) != null){
                return false;
            }
            persons.put(p.getId(), p);
        } catch (Exception e) {
            System.out.println("Exception in addPerson as" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Boolean deletePerson(int id) {
        try {
            if(persons.get(id) == null){
                return false;
            }
            persons.remove(id);
        } catch (Exception e) {
            System.out.println("Exception in addPerson as" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Person getPerson(int id) {
        try {
            return persons.get(id);
        } catch (Exception e) {
            System.out.println("Exception in getPerson as" + e.getMessage());
        }
        return null;
    }

    @Override
    public Person[] getAllPersons() {
        try {
            Set<Integer> ids = persons.keySet();
		Person[] p = new Person[ids.size()];
		int i=0;
		for(Integer id : ids){
			p[i] = persons.get(id);
			i++;
		}
		return p;
        } catch (Exception e) {
            System.out.println("Exception in getAllPersons as" + e.getMessage());
        }
        return null;
    }
    
    public Person getDummyPerson(int id) {
        try {
            Person p = new Person();
		p.setAge(99);
		p.setName("Dummy");
		p.setId(id);
		return p;
        } catch (Exception e) {
            System.out.println("Exception in getDummyPerson as" + e.getMessage());
        }
	return null;	
	}
}
