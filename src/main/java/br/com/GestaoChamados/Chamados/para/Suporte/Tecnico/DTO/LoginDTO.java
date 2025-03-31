package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
    @NotBlank
    String nome,
    @NotBlank
    String senha
) {
    
}
