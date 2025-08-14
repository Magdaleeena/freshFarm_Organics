package com.example.freshFarm.repository;

import com.example.freshFarm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByEmailAndPostcode(String email, String postcode);
    @Override
    Optional<Customer> findById(String customer_id);
    Optional<Customer> findByAddressLine1AndPostcode(String address_line1, String postcode);
    Optional<Customer> findUserByEmail(String email);

    Customer findByEmail(String customerEmail);

    // added for testing

    List<Customer> findByCustomerId(String customerId);

    // Adding this method for testing
    List<Customer> findAll();

}

