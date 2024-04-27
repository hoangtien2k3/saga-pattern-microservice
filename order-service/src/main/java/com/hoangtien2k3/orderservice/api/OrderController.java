package com.hoangtien2k3.orderservice.api;

import com.hoangtien2k3.orderservice.domain.OrderRequest;
import com.hoangtien2k3.orderservice.domain.port.OrderUseCasePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderUseCasePort orderUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody @Valid OrderRequest orderRequest) {
        log.info("Received new order request {}", orderRequest);
        orderUseCase.placeOrder(orderRequest);
    }
}
