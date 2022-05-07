package com.projet.commerce.facture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projet.commerce.client.Client;
import com.projet.commerce.produit.Produit;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class Facture {
    @Id
    @SequenceGenerator(
            name = "facture_sequence",
            sequenceName = "facture_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "facture_sequence"
    )
    private Long id;
    private Date dateFacture;
    private Double prix;

    /*
    @OneToMany(mappedBy = "facture")
    private Set<Achat> achats;
    */

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cin", nullable = false)
    private Client client;

    @ManyToMany(/*fetch = FetchType.LAZY/*, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }*/)
    @JoinTable(name = "achat",
            joinColumns = { @JoinColumn(name = "factureId")},
            inverseJoinColumns = { @JoinColumn(name = "produitId")}
    )
//    private Set<Produit> produits = new HashSet<>();
    private List<Produit> produits =new ArrayList<>() ;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date date) {
        this.dateFacture = date;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double price) {
        this.prix = price;
    }

    public Facture() {
    }

    public Facture(Long id, Date dateFacture, Double prix) {
        this.id = id;
        this.dateFacture = dateFacture;
        this.prix = prix;
    }

    public Facture(Date dateFacture, Double prix, Client client, List<Produit> produits) {
        this.dateFacture = dateFacture;
        this.prix = prix;
        this.client = client;
        this.produits = produits;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setProduits(Produit produit) {
        this.produits.add(produit);
    }

    public void ajoutproduit(Produit produit) {
        produits.add(produit);
    }
}
