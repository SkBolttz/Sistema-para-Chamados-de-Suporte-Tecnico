package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.AtualizarDados;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.DeletarUsuario;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.UsuarioException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Service.TodosUsuariosService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private TodosUsuariosService todosUsuariosService;


    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody @Valid AtualizarDados dados) throws UsuarioException {
        
        return todosUsuariosService.atualizarDados(dados);
    }

    @PutMapping("/deletar")
    public ResponseEntity<String> deletar(@RequestBody @Valid DeletarUsuario usuario) throws UsuarioException {
        
        return todosUsuariosService.deletarUsuario(usuario);
    }
}
