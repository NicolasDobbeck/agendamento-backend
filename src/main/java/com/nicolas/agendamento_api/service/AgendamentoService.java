package com.nicolas.agendamento_api.service;

import com.nicolas.agendamento_api.dto.AgendamentoRequest;
import com.nicolas.agendamento_api.model.Agendamento;
import com.nicolas.agendamento_api.model.Usuario;
import com.nicolas.agendamento_api.repository.AgendamentoRepository;
import com.nicolas.agendamento_api.repository.UsuarioRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final UsuarioRepository usuarioRepository;

    public AgendamentoService(AgendamentoRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Agendamento> listarTodos() {
        String emailLogado = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario usuario = (Usuario) usuarioRepository.findByLogin(emailLogado);

        if (usuario.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PACIENTE"))) {
            return repository.findByNomePaciente(emailLogado);
        }
        else if (usuario.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MEDICO"))) {
            return repository.findByNomeProfissional(usuario.getNome());
        }
        else {
            return repository.findAll();
        }
    }

    public Agendamento criar(AgendamentoRequest dto) {
        if (repository.existsByNomeProfissionalAndDataHora(dto.nomeProfissional(), dto.dataHora())) {
            throw new IllegalArgumentException("Horário indisponível para este profissional!");
        }

        String emailUsuarioLogado = SecurityContextHolder.getContext().getAuthentication().getName();

        Agendamento agendamento = new Agendamento();
        agendamento.setNomePaciente(emailUsuarioLogado);
        agendamento.setNomeProfissional(dto.nomeProfissional());
        agendamento.setDataHora(dto.dataHora());

        return repository.save(agendamento);
    }
}