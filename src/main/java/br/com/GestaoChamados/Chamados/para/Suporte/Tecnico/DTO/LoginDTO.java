package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotBlank @Size(min = 6, max = 100, message = "O nome de usuário deve ter entre 6 e 100 caracteres") String nome,
        @NotBlank @Size(min = 6, max = 100, message = "A senha de usuário deve ter entre 6 e 100 caracteres") String senha) {

}
