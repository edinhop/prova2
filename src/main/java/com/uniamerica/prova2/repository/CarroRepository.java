package com.uniamerica.prova2.repository;

import com.uniamerica.prova2.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    @Query("select q from Carro q left join Reserva r on q.id=r.carro.id where r.dataRetirada >= :dataRetirada " +
            "and r.dataDevolucao <= :dataDevolucao and r.status like 'FINALIZADO'")
    List<Carro> searchByDate(
            @Param("dataRetirada") LocalDate dataRetirada,
            @Param("dataDevolucao") LocalDate dataDevolucao);
}