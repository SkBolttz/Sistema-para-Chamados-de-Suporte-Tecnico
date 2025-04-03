package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação de Usuários", description = "Responsável por autenticar os usuários.")
public class AuthenticationController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/login/usuario")
    @Operation(summary = "Realizar autenticação para Login", description = "Responsável por realizar autenticação para login.")
    @Parameter(name = "usuario", description = "Usuário")
    @Parameter(name = "senha", description = "Senha")
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
    @Operation(summary = "Realiza autenticação para o Cadastro de Usuário(a)", description = "Realiza autenticação para o Cadastro de Usuário(a).")
    @Parameter(name = "Usuário", description = "Usuário")
    @Parameter(name = "E-mail", description = "Email")
    @Parameter(name = "Telefone", description = "Telefone")
    @Parameter(name = "Senha", description = "Senha")
    public ResponseEntity<String> registrarUsuario(@RequestBody @Valid Usuario usuario) throws UsuarioException {

        loginService.registrarUsuario(usuario);

        return ResponseEntity.status(201).body("Seja bem-vindo(a) " + usuario.getNome() + ", sua conta foi criada com sucesso.");
    }

    @Operation(summary = "Realiza autenticação para o Cadastro de Técnico(a)", description = "Realiza autenticação para o Cadastro de Técnico(a).")
    @Parameter(name = "Usuário", description = "Usuário")
    @Parameter(name = "E-mail", description = "Email")
    @Parameter(name = "Telefone", description = "Telefone")
    @Parameter(name = "Senha", description = "Senha")
    @Parameter(name = "Nível Técnico", description = "Nível Técnico")
    @PostMapping("/registrar/tecnico")
    public ResponseEntity<String> registrarTecnico(@RequestBody @Valid Tecnico tecnico)
            throws UsuarioException, TecnicoException {

        loginService.registrarTecnico(tecnico);

        return ResponseEntity.status(201).body("Seja bem-vindo(a) " + tecnico.getNome() + ", sua conta foi criada com sucesso.");
    }

    @Operation(summary = "Realiza autenticação para o Cadastro de Administrador(a)", description = "Realiza autenticação para o Cadastro de Administrador(a).")
    @Parameter(name = "Usuário", description = "Usuário")
    @Parameter(name = "E-mail", description = "Email")
    @Parameter(name = "Telefone", description = "Telefone")
    @Parameter(name = "Senha", description = "Senha")
    @PostMapping("/registrar/admin")
    public ResponseEntity<String> registrarAdmin(@RequestBody @Valid Administrador admin)
            throws UsuarioException, TecnicoException, AdministradorException {

        loginService.registrarAdmin(admin);

        return ResponseEntity.status(201).body("Seja bem-vindo(a) " + admin.getNome() + ", sua conta foi criada com sucesso.");
    }
}
