package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum;

public enum Role {
    USUARIO("Usuario"),
    TECNICO("Tecnico"),
    ADMINISTRADOR("Administrador");

    @SuppressWarnings("unused")
    private String role;

    Role(String role) {
        this.role = role;
    }
}
