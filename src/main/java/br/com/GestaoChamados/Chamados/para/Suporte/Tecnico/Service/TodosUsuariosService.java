package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.AtualizarDados;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.DeletarUsuario;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.ReativarUsuario;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum.Role;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Administrador;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Tecnico;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.TodosUsuarios;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Usuario;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.UsuarioException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.AdministradorRepository;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.TecnicoRepository;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.TodosUsuariosRepository;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.UsuarioRepository;
import jakarta.validation.Valid;

@Service
public class TodosUsuariosService {

    @Autowired
    private TodosUsuariosRepository todosUsuariosRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AdministradorRepository administradorRepository;
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private PasswordEncoder PasswordEncoder;

    public void atualizarDados(@Valid AtualizarDados dados) throws UsuarioException {
        TodosUsuarios user = todosUsuariosRepository.findById(dados.id())
                .orElseThrow(() -> new UsuarioException("Usuário não encontrado."));

        if (dados.nome() != null) {
            user.setNome(dados.nome());
        }
        if (dados.email() != null) {
            user.setEmail(dados.email());
        }
        if (dados.telefone() != null) {
            user.setTelefone(dados.telefone());
        }
        if (dados.senha() != null && !dados.senha().isBlank()) {
            user.setSenha(PasswordEncoder.encode(dados.senha()));
        }

        if (user.getRole() == Role.ADMINISTRADOR) {
            Administrador admin = new Administrador(user);
            administradorRepository.save(admin);
        }

        if (user.getRole() == Role.TECNICO) {
            Tecnico tech = new Tecnico(user);
            tecnicoRepository.save(tech);
        }

        if (user.getRole() == Role.USUARIO) {
            Usuario usuario = new Usuario(user);
            usuarioRepository.save(usuario);
        }

        todosUsuariosRepository.save(user);
    }

    @Transactional
    public void deletarUsuario(DeletarUsuario usuario) throws UsuarioException {

        TodosUsuarios user = todosUsuariosRepository.findByNome(usuario.nome());

        if (user == null) {
            throw new UsuarioException("Usuário nao encontrado.");
        }

        user.setAtivo(false);
        todosUsuariosRepository.save(user);

        if (user.getRole() == Role.ADMINISTRADOR) {
            Administrador admin = new Administrador(user);
            administradorRepository.save(admin);
        }

        if (user.getRole() == Role.TECNICO) {
            Tecnico tech = new Tecnico(user);
            tecnicoRepository.save(tech);
        }

        if (user.getRole() == Role.USUARIO) {
            Usuario usuario2 = new Usuario(user);
            usuarioRepository.save(usuario2);
        }
    }
    
    @Transactional
    public void reativarUsuario(ReativarUsuario usuario) throws UsuarioException {
    
        TodosUsuarios user = todosUsuariosRepository.findByNome(usuario.nome());

        if(user == null){
            throw new UsuarioException("Usuário nao encontrado.");
        }
        
        user.setAtivo(true);
        todosUsuariosRepository.save(user);
    
        if (user.getRole() == Role.ADMINISTRADOR) {
            Administrador admin = new Administrador(user);
            administradorRepository.save(admin);
        }

        if (user.getRole() == Role.TECNICO) {
            Tecnico tech = new Tecnico(user);
            tecnicoRepository.save(tech);
        }

        if (user.getRole() == Role.USUARIO) {
            Usuario usuario2 = new Usuario(user);
            usuarioRepository.save(usuario2);
        }
    }
}
