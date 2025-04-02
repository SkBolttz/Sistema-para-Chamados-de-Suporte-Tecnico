package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AtualizarDados 
(
    @NotNull
    Long id,

    String nome,

    String email,

    String telefone,

    @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres")
    String senha
){
}
