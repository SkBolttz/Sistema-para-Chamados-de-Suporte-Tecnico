package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AberturaChamadoDTO(
        @NotBlank @Size(min = 10, max = 100, message = "O título deve ter entre 10 e 100 caracteres") String titulo,
        @NotBlank @Size(min = 10, max = 100, message = "A descrição deve ter entre 10 e 100 caracteres") String descricao,
        @NotBlank @Size(min = 10, max = 100, message = "O nome de usuário deve ter entre 10 e 100 caracteres") String nomeUsuario) {

}
