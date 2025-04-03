package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum;

public enum NivelTecnico {

    ESTAGIARIO("Estagiario"),
    JUNIOR("Junior"),
    PLENO("Pleno"),
    SENIOR("Senior");

    @SuppressWarnings("unused")
    private String nivelTecnico;

    NivelTecnico(String nivelTecnico) {
        this.nivelTecnico = nivelTecnico;
    }
}
