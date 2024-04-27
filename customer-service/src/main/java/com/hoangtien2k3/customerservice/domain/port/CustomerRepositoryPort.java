package com.hoangtien2k3.customerservice.domain.port;

import com.hoangtien2k3.customerservice.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepositoryPort {

    Optional<Customer> findCustomerById(UUID customerId);

    Customer saveCustomer(Customer customer);
}
