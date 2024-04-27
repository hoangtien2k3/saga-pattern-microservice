package com.hoangtien2k3.inventoryservice.domain.port;

import com.hoangtien2k3.inventoryservice.domain.PlacedOrderEvent;
import com.hoangtien2k3.inventoryservice.domain.ProductRequest;
import com.hoangtien2k3.inventoryservice.domain.entity.Product;

import java.util.UUID;

public interface ProductUseCasePort {

    Product findById(UUID productId);

    Product create(ProductRequest productRequest);

    boolean reserveProduct(PlacedOrderEvent orderEvent);
}
