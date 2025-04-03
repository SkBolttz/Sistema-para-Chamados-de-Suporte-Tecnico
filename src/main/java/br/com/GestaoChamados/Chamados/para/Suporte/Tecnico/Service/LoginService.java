package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum.Role;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Administrador;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Tecnico;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.TodosUsuarios;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Usuario;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.EmailException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.NomeException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.TelefoneException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.AdministradorRepository;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.TecnicoRepository;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.TodosUsuariosRepository;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.UsuarioRepository;

@Service
public class LoginService {

    @Autowired
    private TodosUsuariosRepository todosUsuariosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    public void registrarUsuario(Usuario usuario) throws NomeException, EmailException {
        
        var nomeExiste = todosUsuariosRepository.findByNome(usuario.getNome());

        if(nomeExiste != null) {
            throw new NomeException("Nome de usuário já cadastrado.");
        }

        var emailExiste = todosUsuariosRepository.findByEmail(usuario.getEmail());

        if(emailExiste != null){
            throw new EmailException("Email já cadastrado.");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setAtivo(true);
        usuario.setRole(Role.USUARIO);

        TodosUsuarios todosUsuarios = new TodosUsuarios(usuario);

        todosUsuarios.setId(usuario.getId());
        todosUsuarios.setNome(usuario.getNome());
        todosUsuarios.setEmail(usuario.getEmail());
        todosUsuarios.setTelefone(usuario.getTelefone());
        todosUsuarios.setSenha(usuario.getSenha());
        todosUsuarios.setRole(Role.USUARIO);
        todosUsuarios.setAtivo(true);

        usuarioRepository.save(usuario);
        todosUsuariosRepository.save(todosUsuarios);
        
    }

    public void registrarTecnico(Tecnico tecnico) throws NomeException, EmailException, TelefoneException  {
        
        var nomeExiste = todosUsuariosRepository.findByNome(tecnico.getNome());

        if(nomeExiste != null) {
            throw new NomeException("Nome de usuário já cadastrado.");
        }

        var emailExiste = todosUsuariosRepository.findByEmail(tecnico.getEmail());

        if(emailExiste != null){
            throw new EmailException("Email já cadastrado.");
        }

        var telefoneExiste = todosUsuariosRepository.findByTelefone(tecnico.getTelefone());

        if(telefoneExiste != null){
            throw new TelefoneException("Telefone já cadastrado.");
        }

        tecnico.setSenha(passwordEncoder.encode(tecnico.getSenha()));
        tecnico.setAtivo(true);
        tecnico.setRole(Role.TECNICO);

        TodosUsuarios todosUsuarios = new TodosUsuarios(tecnico);

        todosUsuarios.setId(tecnico.getId());
        todosUsuarios.setNome(tecnico.getNome());
        todosUsuarios.setEmail(tecnico.getEmail());
        todosUsuarios.setTelefone(tecnico.getTelefone());
        todosUsuarios.setSenha(tecnico.getSenha());
        todosUsuarios.setRole(Role.TECNICO);
        todosUsuarios.setAtivo(true);

        todosUsuariosRepository.save(todosUsuarios);
        tecnicoRepository.save(tecnico);

    }

    public void registrarAdmin(Administrador admin) throws NomeException, EmailException, TelefoneException {
        
        var nomeExiste = todosUsuariosRepository.findByNome(admin.getNome());

        if(nomeExiste != null) {
            throw new NomeException("Nome de usuário já cadastrado.");
        }

        var emailExiste = todosUsuariosRepository.findByEmail(admin.getEmail());

        if(emailExiste != null){
            throw new EmailException("Email já cadastrado.");
        }

        var telefoneExiste = todosUsuariosRepository.findByTelefone(admin.getTelefone());

        if(telefoneExiste != null){
            throw new TelefoneException("Telefone já cadastrado.");
        }

        admin.setSenha(passwordEncoder.encode(admin.getSenha()));
        admin.setAtivo(true);
        admin.setRole(Role.ADMINISTRADOR);

        TodosUsuarios todosUsuarios = new TodosUsuarios(admin);
        todosUsuarios.setId(admin.getId());
        todosUsuarios.setNome(admin.getNome());
        todosUsuarios.setEmail(admin.getEmail());
        todosUsuarios.setTelefone(admin.getTelefone());
        todosUsuarios.setSenha(admin.getSenha());
        todosUsuarios.setRole(Role.ADMINISTRADOR);
        todosUsuarios.setAtivo(true);

        todosUsuariosRepository.save(todosUsuarios);
        administradorRepository.save(admin);

    }
}
