package com.nicolas.agendamento_api.controller;

import com.nicolas.agendamento_api.dto.UsuarioResponseDTO;
import com.nicolas.agendamento_api.model.Especialidade;
import com.nicolas.agendamento_api.model.UserRole;
import com.nicolas.agendamento_api.model.Usuario;
import com.nicolas.agendamento_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/medicos")
    public ResponseEntity<List<UsuarioResponseDTO>> listarMedicos(
            @RequestParam(required = false) Especialidade especialidade
    ) {
        List<Usuario> lista;

        if (especialidade != null) {
            lista = repository.findByRoleAndEspecialidade(UserRole.MEDICO, especialidade);
        } else {
            // Se não veio, busca todos
            lista = repository.findByRole(UserRole.MEDICO);
        }

        var dtos = lista.stream()
                .map(u -> new UsuarioResponseDTO(u.getId(), u.getNome()))
                .toList();

        return ResponseEntity.ok(dtos);
    }
}