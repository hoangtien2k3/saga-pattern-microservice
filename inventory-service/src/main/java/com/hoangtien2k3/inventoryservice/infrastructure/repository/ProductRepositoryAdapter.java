package com.hoangtien2k3.inventoryservice.infrastructure.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoangtien2k3.inventoryservice.domain.entity.Product;
import com.hoangtien2k3.inventoryservice.domain.port.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ObjectMapper mapper;

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Optional<Product> findProductById(UUID productId) {
        var entity = productJpaRepository.findById(productId);
        return entity.map(productEntity -> mapper.convertValue(productEntity, Product.class));
    }

    @Override
    public Product saveProduct(Product product) {
        var entity = mapper.convertValue(product, ProductEntity.class);
        productJpaRepository.save(entity);
        return product;
    }
}
