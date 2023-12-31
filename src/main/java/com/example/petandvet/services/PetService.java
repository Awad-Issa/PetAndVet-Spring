package com.example.petandvet.services;

import com.example.petandvet.models.Pet;
import com.example.petandvet.repositories.PetRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetService {

  private final PetRepository petRepo;

  public void createPet(Pet pet) {
    petRepo.save(pet);
  }

  public Pet getPetById(Long id) {
    return petRepo.findById(id).orElse(null);
  }

  public List<Pet> getPetsByLocation(String location) {
    return petRepo.findByLocation(location);
  }

//  public List<Pet> getPetsByBreed(String breed){
//    return petRepo.findByBreed(breed);
//  }


  public List<Pet> getAllPets(){
    return (List<Pet>) petRepo.findAll();
  }


  public void updatePet(Pet pet) {
    petRepo.save(pet);
  }

  public void deletePet(Long id) {
    petRepo.deleteById(id);
  }
}
