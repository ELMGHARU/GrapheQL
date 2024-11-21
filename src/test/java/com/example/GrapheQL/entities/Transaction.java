package com.example.GrapheQL.entities;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
public class Transaction {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private double montant;

private String date;

@Enumerated(EnumType.STRING)
private TypeTransaction type;

@ManyToOne
@JoinColumn(name = "compte_id")
private Compte compte;

    public Thread getCompte() {
        return null;
    }

    public void setCompte(Compte compte) {
    }
}