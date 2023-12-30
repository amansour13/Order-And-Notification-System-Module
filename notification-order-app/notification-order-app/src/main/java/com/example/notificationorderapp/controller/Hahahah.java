package com.example.notificationorderapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notificationorderapp.model.User;

@RestController
@RequestMapping("/")
public class Hahahah{

    @GetMapping("/baby") public ResponseEntity<String> hahahaha() {
        return new ResponseEntity<>("MOM", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> postMethodName(@RequestBody User user) {
        if (user.getUsername().equals("mohammad") && user.getPassword().equals("mohammad"))
            return new ResponseEntity<>("success", HttpStatus.OK);
        else
            return new ResponseEntity<>("failed", HttpStatus.OK);
    }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    

    //     System.out.println("in delete with id:"+id);
    //     boolean res = personService.deletePerson(id);
    //     Response response = new Response();
    //     if (!res) {
    //         response.setStatus(false);
    //         response.setMessage("com.example.demo.Person Doesn't Exists");
    //         return response;
    //     }

    //     response.setStatus(true);
    //     response.setMessage("com.example.demo.Person deleted successfully");
    //     return response;
    // }
    // @GetMapping("/get/{id}")
	// public Person getPerson(@PathVariable("id") int id) {
    //         System.out.println("in get with id:"+id);
	// 	return personService.getPerson(id);
	// }
        
    //     @GetMapping("/getDummy/{id}")
	// public Person getDummyPerson(@PathVariable("id") int id) {
    //         System.out.println("in get dummy id:"+id);
	// 	return personService.getDummyPerson(id);
	// }
        
    //     @GetMapping("/get")
	// public Person[] getAll() {
    //         System.out.println("in getAll");
	// 	return personService.getAllPersons();
	// }

}