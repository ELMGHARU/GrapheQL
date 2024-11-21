package com.example.GrapheQL.repositories;

import com.example.GrapheQL.entities.Compte;

import java.lang.ScopedValue;
import java.util.List;

import com.example.GrapheQL.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
    double sumSoldes();
}

