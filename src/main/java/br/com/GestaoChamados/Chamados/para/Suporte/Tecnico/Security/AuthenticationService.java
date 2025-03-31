package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.TodosUsuarios;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.TodosUsuariosRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private TodosUsuariosRepository todosUsuariosRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
        TodosUsuarios usuario = todosUsuariosRepository.findByNome(username);

        if(usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        
        return usuario;
    }
}
