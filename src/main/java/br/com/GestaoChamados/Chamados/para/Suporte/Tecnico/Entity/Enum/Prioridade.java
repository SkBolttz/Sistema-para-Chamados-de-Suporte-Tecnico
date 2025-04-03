package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum;

public enum Prioridade {

    BAIXA("Baixa"),
    MEDIA("Media"),
    ALTA("Alta"),
    CRITICA("Critica");

    @SuppressWarnings("unused")
    private String prioridade;

    Prioridade(String prioridade) {
        this.prioridade = prioridade;
    }
}
