package com.thro.sqsdemo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressInformationRepo extends JpaRepository<AddressEntry, Long> {
    Optional<AddressEntry> findByAddressAndKey(String address, String key);

    List<AddressEntry> findAllByAddress(String address);

    @Transactional
    void deleteByAddressAndKey(String address, String key);
}
