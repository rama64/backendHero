
package com.examplebackend.exampleBackend.interfaces;

import com.examplebackend.exampleBackend.entity.Person;
import java.util.List;


public interface PersonServiceInterface {
    public List<Person> getPerson();
    
    public void savePerson(Person person);
    
    public void removePerson(Long id);
    
    public Person findPerson(Long id);
}
