package com.example.mini_projet_backend.controllers;

import com.example.mini_projet_backend.entities.Produit;
import com.example.mini_projet_backend.interfaces.IProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/produits", produces = "application/json")
public class ProduitController {

    private final IProduit produitService;

    @Autowired
    public ProduitController(IProduit produitService) {
        this.produitService = produitService;
    }

    @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
//        List<Produit> produits = produitService.getAllProduits();

        List<Produit> produits = produitService.getAllProduitsWithOrder("id");

        return ResponseEntity.ok(produits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable("id") Long id) {
        Optional<Produit> produit = produitService.getProduitById(id);
        return produit.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produit> saveProduit(@RequestBody Produit produit) {
        Produit savedProduit = produitService.saveProduit(produit);
        return new ResponseEntity<>(savedProduit, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable("id") Long id, @RequestBody Produit produit) {
        Produit updatedProduit = produitService.updateProduit(id, produit);
        return new ResponseEntity<>(updatedProduit, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable("id") Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }

}
