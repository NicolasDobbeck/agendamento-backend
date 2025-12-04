package com.nicolas.agendamento_api.repository;

import com.nicolas.agendamento_api.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    boolean existsByNomeProfissionalAndDataHora(String nomeProfissional, LocalDateTime dataHora);

    List<Agendamento> findByNomePaciente(String nomePaciente);

    List<Agendamento> findByNomeProfissional(String nomeProfissional);
}