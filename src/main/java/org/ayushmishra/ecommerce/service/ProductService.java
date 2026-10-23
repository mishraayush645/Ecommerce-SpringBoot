package org.ayushmishra.ecommerce.service;

import jakarta.transaction.Transactional;
import org.ayushmishra.ecommerce.dto.ProductRequest;
import org.ayushmishra.ecommerce.dto.ProductResponse;
import org.ayushmishra.ecommerce.entity.ProductEntity;
import org.ayushmishra.ecommerce.exception.ProductNotFoundException;
import org.ayushmishra.ecommerce.mapper.ProductMapper;
import org.ayushmishra.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;


    private ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductResponse createProduct(ProductRequest productRequest){

        ProductEntity entity = productMapper.mapToEntity(productRequest);

        ProductEntity savedProduct = productRepository.save(entity);

        return productMapper.mapToResponse(savedProduct);
    }

    public List<ProductResponse> getAllProducts(){
//        List<ProductResponse> productResponseList = new ArrayList<>();
//        for(ProductEntity productEntity : productRepository.findAll()){
//            productResponseList.add(productMapper.mapToResponse(productEntity));
//        }



//            List<ProductResponse> responses = new ArrayList<>();
//
//            productRepository.findAll().forEach(product -> {
//                responses.add(productMapper.mapToResponse(product));
//            });
//
//            return responses;


            return productRepository.findAll()
                    .stream()
                    .map(productMapper::mapToResponse)
                    .toList();
    }

    public ProductResponse getProductById(Long id){

        //Optional<ProductEntity> product = productRepository.findById(id);


            ProductEntity product = productRepository.findById(id)
                    .orElseThrow(() ->
                            new ProductNotFoundException(
                                    "Product not found with id: " + id
                            )
                    );


           return productMapper.mapToResponse(product);

    }

    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest productRequest){

        //Optional<ProductEntity> existingProduct = productRepository.findById(id);
        //ProductEntity existingProductEntity = existingProduct.get();

        ProductEntity existingProductEntity = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException((
                "Product not found with id: " + id
                )));

        existingProductEntity.setName(productRequest.getName());
        existingProductEntity.setDescription(productRequest.getDescription());
        existingProductEntity.setPrice(productRequest.getPrice());
        existingProductEntity.setStock(productRequest.getStock());



        ProductEntity savedProduct = productRepository.save(existingProductEntity);

        return productMapper.mapToResponse(savedProduct);

    }

    public Boolean deleteProductById(Long id){

        Optional<ProductEntity> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }

        return false;
        
    }

}
