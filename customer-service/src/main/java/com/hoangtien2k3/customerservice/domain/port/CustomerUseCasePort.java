package com.hoangtien2k3.customerservice.domain.port;

import com.hoangtien2k3.customerservice.domain.CustomerRequest;
import com.hoangtien2k3.customerservice.domain.PlacedOrderEvent;
import com.hoangtien2k3.customerservice.domain.entity.Customer;

import java.util.UUID;

public interface CustomerUseCasePort {

  Customer findById(UUID customerId);

  Customer create(CustomerRequest customerRequest);

  boolean reserveBalance(PlacedOrderEvent orderEvent);

  void compensateBalance(PlacedOrderEvent orderEvent);
}
