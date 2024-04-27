package com.hoangtien2k3.inventoryservice.api;

import com.hoangtien2k3.inventoryservice.domain.ProductRequest;
import com.hoangtien2k3.inventoryservice.domain.entity.Product;
import com.hoangtien2k3.inventoryservice.domain.port.ProductUseCasePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductUseCasePort productUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody @Valid ProductRequest productRequest) {
        log.info("Create new product {}", productRequest);
        return productUseCase.create(productRequest);
    }

    @GetMapping("/{productId}")
    public Product findById(@PathVariable UUID productId) {
        return productUseCase.findById(productId);
    }
}
