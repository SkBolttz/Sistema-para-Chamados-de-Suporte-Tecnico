package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_log_chamado")
public class LogChamado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private long idChamado;
    @NotBlank
    private String finalizacao;
    private LocalDateTime dataLog;
}
