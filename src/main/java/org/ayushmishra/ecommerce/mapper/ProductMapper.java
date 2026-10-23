package org.ayushmishra.ecommerce.mapper;

import org.ayushmishra.ecommerce.dto.ProductRequest;
import org.ayushmishra.ecommerce.dto.ProductResponse;
import org.ayushmishra.ecommerce.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductEntity mapToEntity(ProductRequest productRequest){
        ProductEntity productEntity = new ProductEntity();

        productEntity.setName(productRequest.getName());
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setStock(productRequest.getStock());

        return productEntity;
    }

    public ProductResponse mapToResponse(ProductEntity productEntity){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(productEntity.getId());
        productResponse.setName(productEntity.getName());
        productResponse.setDescription(productEntity.getDescription());
        productResponse.setPrice(productEntity.getPrice());
        productResponse.setStock(productEntity.getStock());

        return productResponse;
    }


}
