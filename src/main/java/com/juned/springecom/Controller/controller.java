package com.juned.springecom.Controller;


import com.juned.springecom.Model.Product;
import com.juned.springecom.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class controller {

    @Autowired
    public Service service;
    @GetMapping("products")
    public ResponseEntity<List<Product>> getProduct()
    {
        return new ResponseEntity<>(service.getallProduct(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = service.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/product")
    public ResponseEntity<?> addProduct (@RequestPart Product product,@RequestPart MultipartFile imageFile)
    {
        Product savedProduct= null;
        try {
            savedProduct = service.addOrUpdateProduct(product,imageFile);
            return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
        } catch (IOException e) {
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product product,@RequestPart MultipartFile imageFile)
    {
        Product updateproduct=null;
        try {
            updateproduct = service.addOrUpdateProduct(product, imageFile);
            return new ResponseEntity<>("updated",HttpStatus.OK);
        }catch (IOException e)
        {

            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct (@PathVariable int id)
    {

        Product product=service.getProductById(id);

        if (product!=null)
        {
            service.deleteUpdate(id);
            return new ResponseEntity<>("deleted",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("not deleted",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchbykeyword(@RequestParam String keyword)
    {
        List<Product> products=service.searchProduct(keyword);
        System.out.println("searching by: "+keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
