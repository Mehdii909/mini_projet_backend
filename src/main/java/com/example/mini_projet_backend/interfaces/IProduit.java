package com.example.mini_projet_backend.interfaces;

import com.example.mini_projet_backend.entities.Produit;

import java.util.List;
import java.util.Optional;

public interface IProduit {
    List<Produit> getAllProduits();
    Optional<Produit> getProduitById(Long id);
    Produit saveProduit(Produit produit);
    Produit updateProduit(Long id, Produit produit);
    void deleteProduit(Long id);
    List<Produit> getAllProduitsWithOrder(String id);
}
