package com.projet.commerce.produit;


import com.projet.commerce.facture.Facture;
import com.projet.commerce.facture.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8888")
@RequestMapping("/produit")
public class ProduitController {
    private final ProduitService produitService;

    @Autowired
    public ProduitController(ProduitService produitService){this.produitService = produitService;}

    @GetMapping("/all")
    public ResponseEntity<List<Produit>> getAllProduit(){
        List<Produit> produits = produitService.getAllProduits();
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity <Produit> addproduit(@RequestBody Produit produit)
    {
        Produit new_produit = produitService.saveProduit(produit) ;
        return new ResponseEntity<> (new_produit , HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Produit> updateProduit(@RequestBody Produit produit){
        Produit updatedProduit = produitService.saveProduit(produit);
        return new ResponseEntity<>(updatedProduit, HttpStatus.OK);
    }


}
