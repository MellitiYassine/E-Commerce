package com.projet.commerce.facture;


import com.projet.commerce.client.Client;
import com.projet.commerce.client.ClientService;
import com.projet.commerce.produit.Produit;
import com.projet.commerce.produit.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8888")
public class FactureController {
    private final FactureService factureService;
    private  ProduitService produitService;

    @Autowired
    public FactureController(FactureService factureService){this.factureService = factureService;}

    @Autowired
    public void ProduitController(ProduitService produitService){this.produitService = produitService;}


    @GetMapping("/facture/all")
    public ResponseEntity<List<Facture>> getAllFacture(){
        List<Facture> clients = factureService.getAllFactures();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping("/facture/add")
    public ResponseEntity <Facture> addFacture(@RequestBody Facture client) {
        Facture new_facture = factureService.saveFacture(client) ;
        return new ResponseEntity<> (new_facture, HttpStatus.CREATED);
    }

    @PutMapping("/facture/update")
    public ResponseEntity<Facture> updateFacture(@RequestBody Facture client){
        Facture updatedFacture = factureService.saveFacture(client);
        return new ResponseEntity<>(updatedFacture, HttpStatus.OK);
    }

    //ajouter une facture pour un client enregistré
    @PostMapping("/client/{clientId}/add/facture")
    public ResponseEntity<Facture> AddFacture(@PathVariable("clientId") Long cin, @RequestBody Facture facture) {
        Facture new_facture = null ;

        try {
            Client clt = ClientService.findClientById(cin) ;
            facture.setClient(clt); ;
            new_facture = factureService.saveFacture(facture) ;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("there is a problem") ;
        }

        return new ResponseEntity<>(new_facture, HttpStatus.OK);

    }

    @PostMapping("/facture/{factureId}/add/produit")
    public ResponseEntity<Facture> AddProduit(@PathVariable("factureId") Long id, @RequestBody Produit produit) {
        Facture new_facture = null;
        try {
            Facture fct = FactureService.findFactureById(id) ;
            fct.setProduits(produit); ;
//          new_produit = produitService.saveProduit(produit) ;
            new_facture = FactureService.saveFacture(fct);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("there is a problem") ;
        }

        return new ResponseEntity<>(new_facture, HttpStatus.OK);

    }

    @PutMapping("/facture/{factureId}/produits/{produitId}")
    Facture ajouterProduitClient(@PathVariable Long factureId, @PathVariable Long produitId){
        //to impletment findById for both services
        Facture facture = factureService.findFactureById(factureId);
        Produit produit = produitService.findProduitById(produitId);
        facture.ajoutproduit(produit);
        return factureService.saveFacture(facture);
    }

}
