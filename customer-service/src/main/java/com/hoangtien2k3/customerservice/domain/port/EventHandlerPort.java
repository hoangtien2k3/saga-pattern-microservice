package com.hoangtien2k3.customerservice.domain.port;

import org.springframework.messaging.Message;

import java.util.function.Consumer;

public interface EventHandlerPort {

    Consumer<Message<String>> handleReserveCustomerBalanceRequest();

    Consumer<Message<String>> handleCompensateCustomerBalanceRequest();
}
