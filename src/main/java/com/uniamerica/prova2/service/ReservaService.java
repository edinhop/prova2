package com.uniamerica.prova2.service;

import com.uniamerica.prova2.model.Reserva;
import com.uniamerica.prova2.model.Status;
import com.uniamerica.prova2.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;



    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva store(Reserva reserva){

        reserva.setStatus(Status.RESERVADO);
        return reservaRepository.save(reserva);
    }

    public List<Reserva> index(){
        return reservaRepository.findAll();
    }

    public List<Reserva> searchByDate(LocalDate data_entrada, LocalDate data_saida){
        return reservaRepository.searchByDate(data_entrada, data_saida);
    }

    public Optional<Reserva> show(long id){
        return  reservaRepository.findById(id);
    }

    public Reserva update(Reserva reserva) {

        Optional<Reserva> ById = reservaRepository.findById(reserva.getId());
        if(!ById.isPresent()){
            throw new RuntimeException("A reserva não foi encontrada");
        }
        Reserva reserva1 = ById.get();
        if(reserva1.getStatus().equals(Status.RESERVADO)){
            reserva1.setStatus(Status.EM_ANDAMENTO);
        }
        if(reserva1.getStatus().equals(Status.FINALIZADO)){
            reserva1.setStatus(Status.FINALIZADO);
        }
        return reservaRepository.save(reserva);
    }

    public void destroy(Long id) {
        Optional<Reserva> optionalReserva = this.show(id);

        if (optionalReserva.isPresent()){
            reservaRepository.deleteById(id);
        }
    }
}
