package com.juned.springecom.service;

import com.juned.springecom.Model.Product;
import com.juned.springecom.repository.Myrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    public Myrepo repo;


    public List<Product> getallProduct() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }



    public void deleteUpdate(int id) {

        repo.deleteById(id);
    }

    public Product addOrUpdateProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }



    public List<Product> searchProduct(String keyword) {

        return repo.searchProduct(keyword);
    }
}
