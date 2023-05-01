package com.thro.sqsdemo.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<ExtendedDomainInformation, String> {
    public Optional<ExtendedDomainInformation> findByDomain(String name);
}
