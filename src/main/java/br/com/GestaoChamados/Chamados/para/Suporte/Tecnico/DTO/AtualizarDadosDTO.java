package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AtualizarDadosDTO(
                @NotNull Long id,
                @Size(min = 6, max = 100, message = "O nome de usuário deve ter entre 6 e 100 caracteres") String nome,
                @Size(min = 6, max = 100, message = "O e-mail de usuário deve ter entre 6 e 100 caracteres") String email,
                @Size(min = 6, max = 100, message = "O telefone de usuário deve ter entre 6 e 100 caracteres") String telefone,
                @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres") String senha) {
}
