package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity;

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
