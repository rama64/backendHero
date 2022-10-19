package com.examplebackend.exampleBackend.controller;

import com.examplebackend.exampleBackend.entity.Person;
import com.examplebackend.exampleBackend.interfaces.PersonServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @Autowired PersonServiceInterface personService;
    
    @GetMapping("persons/get")
    public List<Person> getPerson(){
        return personService.getPerson();
    }
    
    @PostMapping("persons/create")
    public String createPerson(@RequestBody Person person){
        personService.savePerson(person);
        return "person created sucessfully";
    }
    
    @DeleteMapping("/persons/delete/{id}")
    public String deletePerson(@PathVariable Long id){
        personService.removePerson(id);
        return "person deleted sucessfully";
    }
    
    @PutMapping("persons/edit/{id}")
    public Person editPerson(@PathVariable Long id, 
            @RequestParam("name") String newName,
            @RequestParam("surname") String newSurame,
            @RequestParam("image") String newImage
            ){
        Person person = personService.findPerson(id);
        person.setName(newName);
        person.setSurname(newSurame);
        person.setImage(newImage);
        personService.savePerson(person);
        return person;
    }
}

