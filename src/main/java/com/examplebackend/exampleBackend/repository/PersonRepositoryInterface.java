
package com.examplebackend.exampleBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositoryInterface extends JpaRepository<Person, Long>{
    
}
