package com.nicolas.agendamento_api.repository;

import com.nicolas.agendamento_api.model.Especialidade;
import com.nicolas.agendamento_api.model.UserRole;
import com.nicolas.agendamento_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);

    List<Usuario> findByRole(UserRole role);

    List<Usuario> findByRoleAndEspecialidade(UserRole role, Especialidade especialidade);
}