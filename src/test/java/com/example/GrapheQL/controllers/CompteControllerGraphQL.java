package com.example.GrapheQL.controllers;

import com.example.GrapheQL.entities.*;
import com.example.GrapheQL.repositories.CompteRepository;
import com.example.GrapheQL.repositories.TransactionRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class CompteControllerGraphQL {

    private final CompteRepository compteRepository;
    private final TransactionRepository transactionRepository;

    public CompteControllerGraphQL(CompteRepository compteRepository, TransactionRepository transactionRepository) {
        this.compteRepository = compteRepository;
        this.transactionRepository = transactionRepository;
    }

    @QueryMapping
    public List<Compte> allComptes() {
        return compteRepository.findAll();
    }

    @QueryMapping
    public Compte compteById(@Argument Long id) {
        return compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Compte %s not found", id)));
    }

    @MutationMapping
    public Compte saveCompte(@Argument Compte compte) {
        return compteRepository.save(compte);
    }

    @QueryMapping
    public Map<String, Object> totalSolde() {
        long count = compteRepository.count();
        double sum = compteRepository.sumSoldes();
        double average = count > 0 ? sum / count : 0;
        return Map.of("count", count, "sum", sum, "average", average);
    }

    // Correction de l'importation et utilisation de votre propre entité Transaction
    @QueryMapping
    public List<jakarta.transaction.Transaction> compteTransactions(@Argument Long id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Compte %s not found", id)));
        return transactionRepository.findByCompte(compte);
    }

    // Utilisation de l'entité Transaction directement dans la mutation
    @MutationMapping
    public Transaction addTransaction(@Argument Transaction transaction) {
        Compte compte = compteRepository.findById(transaction.getCompte().getId())
                .orElseThrow(() -> new RuntimeException("Compte not found"));

        // Assurez-vous que l'objet Transaction contient tous les champs nécessaires
        transaction.setCompte(compte);

        return null ;
    }

    @QueryMapping
    public Map<String, Object> transactionStats() {
        long count = transactionRepository.count();
        double sumDepots = transactionRepository.sumByType(TypeTransaction.DEPOT);
        double sumRetraits = transactionRepository.sumByType(TypeTransaction.RETRAIT);

        return Map.of(
                "count", count,
                "sumDepots", sumDepots,
                "sumRetraits", sumRetraits
        );
    }
}
