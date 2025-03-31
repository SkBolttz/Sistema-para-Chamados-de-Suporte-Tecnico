package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.LoginDTO;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Administrador;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Tecnico;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.TodosUsuarios;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Usuario;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.AdministradorException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.TecnicoException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.UsuarioException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Security.TokenService;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Service.LoginService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    
    private LoginService loginService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    public AuthenticationController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login/usuario")
    public String logar(@RequestBody @Valid LoginDTO usuario) throws UsuarioException {

    try {
        var tokenAuthentication = new UsernamePasswordAuthenticationToken(usuario.nome(), usuario.senha());
        var authentication = manager.authenticate(tokenAuthentication);

        var user = (TodosUsuarios) authentication.getPrincipal();
        return tokenService.gerarToken(user, user.getRole().toString());
    } catch (BadCredentialsException e) {
        throw new UsuarioException("Usuário ou senha inválidos.");
    }
    }

    @PostMapping("/registrar/usuario")
    public String registrarUsuario(@RequestBody @Valid Usuario usuario) throws UsuarioException{

        return loginService.registrarUsuario(usuario);
    }
    
    @PostMapping("/registrar/tecnico")
    public ResponseEntity<String> registrarTecnico(@RequestBody @Valid Tecnico tecnico) throws UsuarioException, TecnicoException{
        
        return loginService.registrarTecnico(tecnico);
    }

    @PostMapping("/registrar/admin")
    public ResponseEntity<String> registrarAdmin(@RequestBody @Valid Administrador admin) throws UsuarioException, TecnicoException, AdministradorException{
        
        return loginService.registrarAdmin(admin);
    }
}
