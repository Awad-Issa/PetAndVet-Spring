package com.example.petandvet.services;

import com.example.petandvet.models.Product;
import com.example.petandvet.repositories.BreedRepository;
import com.example.petandvet.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductService {

  private final ProductRepository productRepo;
  private final BreedRepository breedRepo;

  public void createProduct(Product product) {
    productRepo.save(product);
  }

  public void deleteProduct(Long id) {
    productRepo.deleteById(id);
  }

//  public List<Product> getProductsByBreed(Breed breed) {
//    return breed.getProducts();
//  }

    public List<Product> getAllProducts(){
        return (List<Product>) productRepo.findAll();
    }

  public Product findProduct(Long id){
    Optional<Product> optionalAuthor = productRepo.findById(id);
    if(optionalAuthor.isPresent()){
      return optionalAuthor.get();
    }else {
      return null;
    }
  }



}
