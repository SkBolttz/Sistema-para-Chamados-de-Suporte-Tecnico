package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum;

public enum StatusChamado {

    ABERTO("Aberto"),
    ANDAMENTO("Andamento"),
    ENCERRADO("Encerrado");

    private String status;

    StatusChamado(String status) {
        this.status = status;
    }
}
