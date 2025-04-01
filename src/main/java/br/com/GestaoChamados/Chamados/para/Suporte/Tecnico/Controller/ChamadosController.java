package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.AberturaChamadoDTO;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Tecnico;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.UsuarioException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Service.ChamadoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/chamados")
public class ChamadosController {
    
    @Autowired
    private ChamadoService chamadoService;

    @PostMapping("/registrar")
    public ResponseEntity<String> abrirChamado(@RequestBody @Valid AberturaChamadoDTO chamado, Tecnico tecnico) {

        try {
            return chamadoService.abrirChamado(chamado, tecnico);
        } catch (UsuarioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
