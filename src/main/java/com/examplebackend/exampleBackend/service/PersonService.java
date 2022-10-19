
package com.examplebackend.exampleBackend.service;

import com.examplebackend.exampleBackend.entity.Person;
import com.examplebackend.exampleBackend.interfaces.PersonServiceInterface;
import com.examplebackend.exampleBackend.repository.PersonRepositoryInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements PersonServiceInterface {
    @Autowired PersonRepositoryInterface personRepository;
    
    @Override
    public List<Person> getPerson() {
        return personRepository.findAll();
    } 

    @Override
    public void savePerson(Person person) {
      personRepository.save(person);
    }

    @Override
    public void removePerson(Long id) {
       personRepository.deleteById(id);
    }

    @Override
    public Person findPerson(Long id) {
       return personRepository.findById(id).orElse(null);
    }
    
}
