package com.webapp.foodgate.repository;

import com.webapp.foodgate.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {
    Address findByHouseNumber(String houseNumber);

    boolean existsByHouseNumber(String houseNumber);
}
