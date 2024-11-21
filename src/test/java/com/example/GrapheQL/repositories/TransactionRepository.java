package com.example.GrapheQL.repositories;

import com.example.GrapheQL.entities.Compte;
import com.example.GrapheQL.entities.TypeTransaction;
import jakarta.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    List<Transaction> findByCompte(Compte compte);

    // Compter le nombre de transactions dans la base
    long count();

    // Calculer la somme des transactions d'un type spécifique (Dépôt ou Retrait)
    double sumByType(TypeTransaction type);
}
