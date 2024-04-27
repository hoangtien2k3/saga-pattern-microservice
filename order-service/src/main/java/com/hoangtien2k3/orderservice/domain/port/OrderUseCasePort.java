package com.hoangtien2k3.orderservice.domain.port;

import com.hoangtien2k3.orderservice.domain.OrderRequest;

import java.util.UUID;

public interface OrderUseCasePort {

    void placeOrder(OrderRequest orderRequest);

    void updateOrderStatus(UUID orderId, boolean success);
}
