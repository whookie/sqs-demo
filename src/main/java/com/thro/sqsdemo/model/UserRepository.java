package com.thro.sqsdemo.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<ExtendedAddressInformation, String> {
    public Optional<ExtendedAddressInformation> findByAddress(String address);
}
