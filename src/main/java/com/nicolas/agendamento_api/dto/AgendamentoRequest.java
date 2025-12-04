package com.nicolas.agendamento_api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoRequest(

        @NotBlank(message = "Nome do profissional é obrigatório")
        String nomeProfissional,

        @NotNull(message = "Data e hora são obrigatórias")
        @Future(message = "O agendamento deve ser para uma data futura")
        LocalDateTime dataHora
) {}