package com.example.freshFarm.repository;

import com.example.freshFarm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByEmailAndPostcode(String email, String postcode);
    @Override
    Optional<Customer> findById(String customer_id);
    Optional<Customer> findByAddressLine1AndPostcode(String address_line1, String postcode);

    // We can define custom queries here if needed in the future
}
