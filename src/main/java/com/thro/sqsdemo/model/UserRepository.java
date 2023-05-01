package com.thro.sqsdemo.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<ExtendedDomainInformation, String> {
    
}
