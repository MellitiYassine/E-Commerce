package com.projet.commerce.produit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projet.commerce.facture.Facture;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "produit")
public class Produit {
    @Id
    @SequenceGenerator(
            name = "produit_sequence",
            sequenceName = "produit_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "produit_sequence"
    )
    private Long id;
    private String nomProduit;

    public Set<Facture> getFactures() {
        return factures;
    }

    public void setFactures(Set<Facture> factures) {
        this.factures = factures;
    }

    private Long quantite;
    private Double price;
/*
    @OneToMany(mappedBy = "produit")
    private Set<Achat> achats;
*/
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "produits")
    @JsonIgnore
    private Set<Facture> factures = new HashSet<>();


    public Produit(Long id, String NomProduit, Long quantite, Double price) {
        this.id = id;
        this.nomProduit = NomProduit;
        this.quantite = quantite;
        this.price = price;
    }

    public Produit() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String name) {
        this.nomProduit = name;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantity) {
        this.quantite = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", name='" + nomProduit + '\'' +
                ", quantity=" + quantite +
                ", price=" + price +
                '}';
    }
}
