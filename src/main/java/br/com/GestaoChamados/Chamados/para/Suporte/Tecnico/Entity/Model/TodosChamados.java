package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model;

import java.time.LocalDateTime;

import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum.Prioridade;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum.StatusChamado;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "tb_todos_chamados")
public class TodosChamados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Size(min = 10, max = 100, message = "O título deve ter entre 10 e 100 caracteres")
    private String titulo;
    @NotBlank
    @Size(min = 10, max = 100, message = "A descrição deve ter entre 10 e 100 caracteres")
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusChamado status;
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;
    @NotNull
    private Long usuario;
    private Long tecnico;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
}
