package com.projet.commerce.produit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {
    private final ProduitRepository produitRepository;

    @Autowired
    public ProduitService(ProduitRepository produitRepository){this.produitRepository = produitRepository;}

    public List<Produit> getAllProduits(){
        return produitRepository.findAll();
    }

    public Produit saveProduit(Produit produit){
        return produitRepository.save(produit);
    }

    public Produit findProduitById(Long produitId) {
        return produitRepository.findProduitById(produitId);
    }
}
