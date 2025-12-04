package com.nicolas.agendamento_api.dto;
import com.nicolas.agendamento_api.model.Especialidade;
import com.nicolas.agendamento_api.model.UserRole;

public record RegisterDTO(
        String login,
        String senha,
        String nome,
        UserRole role,
        Especialidade especialidade
) {}