package com.example.GiftHub.service;

import com.example.GiftHub.domain.product.Product;
import com.example.GiftHub.domain.product.ProductDTO;
import com.example.GiftHub.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() throws Exception{
        List<Product> products = this.productRepository.findAll();

        if (products.isEmpty()){
            throw new Exception("Não há produtos cadastrados");
        }

        return products;
    }

    public List<Product> searchProducts(String term) throws Exception {
        List<Product> products = this.productRepository.findByNameContainingIgnoreCase(term);

        if (products.isEmpty()) {
            throw new Exception("Nenhum produto encontrado para o termo de pesquisa: " + term);
        }

        return products;
    }

    public Product getProductById(Long id) throws Exception{
        return this.productRepository.findById(id)
                .orElseThrow(() -> new Exception("Produto não encontrado"));
    }

    public Product createProduct(ProductDTO product) throws Exception {
        if (product == null){
            throw new Exception("Erro ao cadastrar o produto, faltando informações necessarias.");
        }
        Product newProduct = new Product(product);
        this.productRepository.save(newProduct);
        return newProduct;
    }
    public void deleteProduct(Long id) throws Exception {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()){
            productRepository.deleteById(id);
        } else {
            throw new Exception("Produto não encontrado para a exclusão");
        }

    }

}
