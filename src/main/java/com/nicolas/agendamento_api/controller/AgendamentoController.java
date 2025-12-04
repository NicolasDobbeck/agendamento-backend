package com.nicolas.agendamento_api.controller;

import com.nicolas.agendamento_api.dto.AgendamentoRequest;
import com.nicolas.agendamento_api.model.Agendamento;
import com.nicolas.agendamento_api.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid AgendamentoRequest dto) {
        try {
            Agendamento novoAgendamento = service.criar(dto);

            return ResponseEntity
                    .created(URI.create("/agendamentos/" + novoAgendamento.getId()))
                    .body(novoAgendamento);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}