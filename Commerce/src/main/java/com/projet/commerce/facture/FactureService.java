package com.projet.commerce.facture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureService {

    private static  FactureRepository factureRepository;

    @Autowired
    public FactureService(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    public static Facture saveFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    public static Facture findFactureById(Long factureId) {
        return factureRepository.findFactureById(factureId);
    }

}
