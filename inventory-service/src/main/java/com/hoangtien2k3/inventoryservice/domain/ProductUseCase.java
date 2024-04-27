package com.hoangtien2k3.inventoryservice.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoangtien2k3.inventoryservice.domain.entity.Product;
import com.hoangtien2k3.inventoryservice.domain.exception.NotFoundException;
import com.hoangtien2k3.inventoryservice.domain.port.ProductRepositoryPort;
import com.hoangtien2k3.inventoryservice.domain.port.ProductUseCasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductUseCase implements ProductUseCasePort {

    private final ObjectMapper mapper;

    private final ProductRepositoryPort productRepository;

    @Override
    public Product findById(UUID productId) {
        return productRepository.findProductById(productId).orElseThrow(NotFoundException::new);
    }

    @Override
    public Product create(ProductRequest productRequest) {
        var product = mapper.convertValue(productRequest, Product.class);
        product.setId(UUID.randomUUID());
        return productRepository.saveProduct(product);
    }

    @Override
    public boolean reserveProduct(PlacedOrderEvent orderEvent) {
        var product = findById(orderEvent.productId());
        if (product.getStocks() - orderEvent.quantity() < 0) {
            return false;
        }
        product.setStocks(product.getStocks() - orderEvent.quantity());
        productRepository.saveProduct(product);
        return true;
    }
}
