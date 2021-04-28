package com.uniamerica.prova2.repository;

import com.uniamerica.prova2.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository <Reserva, Long> {

    @Query("select r from Reserva r where r.dataRetirada >= :dataRetirada and r.dataDevolucao <= :dataDevolucao")
    List<Reserva> searchByDate(
            @Param("dataRetirada") LocalDate dataRetirada,
            @Param("dataDevolucao") LocalDate dataDevolucao);
}
