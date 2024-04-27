package com.hoangtien2k3.customerservice.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoangtien2k3.customerservice.domain.entity.Customer;
import com.hoangtien2k3.customerservice.domain.exception.NotFoundException;
import com.hoangtien2k3.customerservice.domain.port.CustomerRepositoryPort;
import com.hoangtien2k3.customerservice.domain.port.CustomerUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerUseCase implements CustomerUseCasePort {

    private final ObjectMapper mapper;

    private final CustomerRepositoryPort customerRepository;

    @Override
    public Customer findById(UUID customerId) {
        return customerRepository.findCustomerById(customerId).orElseThrow(NotFoundException::new);
    }

    @Override
    public Customer create(CustomerRequest customerRequest) {
        var customer = mapper.convertValue(customerRequest, Customer.class);
        customer.setId(UUID.randomUUID());
        return customerRepository.saveCustomer(customer);
    }

    @Override
    public boolean reserveBalance(PlacedOrderEvent orderEvent) {
        var customer = findById(orderEvent.customerId());
        if (customer
                .getBalance()
                .subtract(orderEvent.price().multiply(BigDecimal.valueOf(orderEvent.quantity())))
                .compareTo(BigDecimal.ZERO)
                < 0) {
            return false;
        }
        customer.setBalance(
                customer
                        .getBalance()
                        .subtract(orderEvent.price().multiply(BigDecimal.valueOf(orderEvent.quantity()))));
        customerRepository.saveCustomer(customer);
        return true;
    }

    @Override
    public void compensateBalance(PlacedOrderEvent orderEvent) {
        var customer = findById(orderEvent.customerId());
        customer.setBalance(
                customer
                        .getBalance()
                        .add(orderEvent.price().multiply(BigDecimal.valueOf(orderEvent.quantity()))));
        customerRepository.saveCustomer(customer);
    }
}
