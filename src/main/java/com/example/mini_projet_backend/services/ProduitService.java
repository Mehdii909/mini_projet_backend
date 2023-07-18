package com.example.mini_projet_backend.services;


import com.example.mini_projet_backend.dao.ProduitRepository;
import com.example.mini_projet_backend.entities.Produit;
import com.example.mini_projet_backend.interfaces.IProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService implements IProduit {

    private final ProduitRepository produitRepository;

    @Autowired
    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }

    @Override
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public Produit updateProduit(Long id, Produit produit) {
        Produit produitExistant = produitRepository.findById(id).orElse(null);

        if (produitExistant != null) {
            produitExistant.setNom(produit.getNom());
            produitExistant.setPrixUnitaire(produit.getPrixUnitaire());
            produitExistant.setQuantite(produit.getQuantite());

            return produitRepository.save(produitExistant);
        } else {
            throw new IllegalArgumentException("Le produit avec l'ID " + id + " n'existe pas.");
        }
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }
}
