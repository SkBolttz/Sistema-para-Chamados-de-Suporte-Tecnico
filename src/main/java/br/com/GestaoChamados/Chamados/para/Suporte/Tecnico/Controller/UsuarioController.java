package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.AtualizarDados;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.DeletarUsuario;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.ReativarUsuario;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.UsuarioException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Service.TodosUsuariosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Controle de Usuário", description = "Responsável pelo controle de usuário.")
public class UsuarioController {
    
    @Autowired
    private TodosUsuariosService todosUsuariosService;


    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar dados do Usuário", description = "Responsável pela atualização dos dados do usuário.")
    @Parameter(name = "Id", description = "Id do usuário.")
    @Parameter(name = "Nome", description = "Nome do usuário.")
    @Parameter(name = "Email", description = "Email do usuário.")
    @Parameter(name = "Telefone", description = "Telefone do usuário.")
    @Parameter(name = "Senha", description = "Senha do usuário.")   
    public ResponseEntity<String> atualizar(@RequestBody @Valid AtualizarDados dados) {
        
        try{
            todosUsuariosService.atualizarDados(dados);
            return ResponseEntity.status(200).body("Dados atualizados com sucesso!");
        }catch(UsuarioException e){
            return ResponseEntity.badRequest().body("Usuário não encontrado! Favor verifique os dados.");
        }
    }

    @PutMapping("/deletar")
    @Operation(summary = "Deletar Usuário", description = "Responsável pela exclusão do usuário.")
    @Parameter(name = "Nome", description = "Nome do usuário.")
    public ResponseEntity<String> deletar(@RequestBody @Valid DeletarUsuario usuario)  {
        
        try{
            todosUsuariosService.deletarUsuario(usuario);
            return ResponseEntity.status(200).body("Usuário deletado com sucesso!");
        }catch(UsuarioException e){
            return ResponseEntity.badRequest().body("Usuário nao encontrado! Favor verifique os dados.");
        }
    }

    @PutMapping("/reativar")
    @Operation(summary = "Reativar Usuário", description = "Responsável pela reativação do usuário.")
    @Parameter(name = "Nome", description = "Nome do usuário.")
    public ResponseEntity<String> reativarUsuario(@RequestBody @Valid ReativarUsuario usuario) {
        
        try{
            todosUsuariosService.reativarUsuario(usuario);
            return ResponseEntity.status(200).body("Usuário reativado com sucesso!");
        }catch(UsuarioException e){
            return ResponseEntity.badRequest().body("Usuário nao encontrado! Favor verifique os dados.");
        }
    }
}
