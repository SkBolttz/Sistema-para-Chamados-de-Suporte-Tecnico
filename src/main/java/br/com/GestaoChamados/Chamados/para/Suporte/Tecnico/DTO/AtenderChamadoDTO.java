package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AtenderChamadoDTO(
                @NotNull Long id,
                @NotBlank @Size(min = 6, max = 100, message = "O nome do técnico deve ter entre 10 e 100 caracteres") String nomeTecnico) {

}
