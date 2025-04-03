package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.AberturaChamadoDTO;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.AtenderChamado;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.FinalizarChamado;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.ChamadoException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.TecnicoException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.UsuarioException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Service.ChamadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/chamados")
@Tag(name = "Controle de Chamados", description = "Responsável pelo controle de chamados para Suporte Técnico.")
public class ChamadosController {

    @Autowired
    private ChamadoService chamadoService;

    @PostMapping("/registrar")
    @Operation(summary = "Abertura de chamados", description = "Responsável pela abertura de chamados.")
    @Parameter(name = "Título", description = "Título do chamado.")
    @Parameter(name = "Descrição", description = "Descrição do chamado.")
    @Parameter(name = "Nome", description = "Nome do usuário responsável pela abertura do chamado.")
    public ResponseEntity<String> abrirChamado(@RequestBody @Valid AberturaChamadoDTO chamado) {

        try {
            chamadoService.abrirChamado(chamado);
            return ResponseEntity.status(201).body(chamado.nomeUsuario() + ", seu chamado foi aberto com sucesso!");
        } catch (UsuarioException e) {
            return ResponseEntity.badRequest()
                    .body("Usuário(a) " + chamado.nomeUsuario() + " nao encontrado! Verifique se o nome esta correto.");
        }
    }

    @PutMapping("/atender")
    @Operation(summary = "Atendimento de chamados", description = "Responsável pelo atendimento de chamados.")
    @Parameter(name = "Id", description = "Id do chamado.")
    @Parameter(name = "Nome Técnico", description = "Nome do Técnico responsável pelo atendimento do chamado.")
    public ResponseEntity<String> atenderChamado(@RequestBody @Valid AtenderChamado chamado) {

        try {
            chamadoService.atenderChamado(chamado);
            return ResponseEntity.status(201)
                    .body(chamado.nomeTecnico() + ", você atendeu o chamado: " + chamado.id() + " com sucesso!");
        } catch (ChamadoException e) {
            return ResponseEntity.badRequest()
                    .body("Dados do chamado: " + chamado.id() + " nao encontrados! Favor verifique novamente!");
        } catch (TecnicoException e) {
            return ResponseEntity.badRequest()
                    .body("Nome do técnico: " + chamado.nomeTecnico() + " nao encontrado! Favor verifique novamente!");
        }
    }

    @GetMapping("/listar/chamados/abertos")
    @Operation(summary = "Listagem de chamados abertos", description = "Responsável pela listagem de chamados abertos.")
    public List<String> lisarChamadosAbertos() {
        return chamadoService.listarChamadosAbertos();
    }

    @PutMapping("/finalizar")
    @Operation(summary = "Finalização de chamados", description = "Responsável pela finalização de chamados.")
    @Parameter(name = "Id", description = "Id do chamado.")
    @Parameter(name = "Finalização", description = "Descrição da finalização.")
    public ResponseEntity<String> finalizarChamado(@RequestBody @Valid FinalizarChamado chamado) {

        try {
            chamadoService.finalizarChamado(chamado);
            return ResponseEntity.status(200).body("Chamado: " + chamado.id() + " foi finalizado com sucesso!");
        } catch (ChamadoException e) {
            return ResponseEntity.badRequest()
                    .body("Dados do chamado: " + chamado.id() + " nao encontrados! Favor verifique novamente!");
        }
    }
}
