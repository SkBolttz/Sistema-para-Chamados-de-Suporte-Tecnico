package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO;


import jakarta.validation.constraints.NotBlank;
public record DeletarUsuario
(
    @NotBlank
    String nome
) {
    
}
