package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FinalizarChamadoDTO(
                @NotNull Long id,
                @NotBlank @Size(min = 10, max = 100, message = "A descrição deve ter entre 10 e 100 caracteres") String finalizacao) {

}
