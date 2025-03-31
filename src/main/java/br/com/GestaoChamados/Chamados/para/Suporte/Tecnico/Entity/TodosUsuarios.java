package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "tb_todos_usuarios")
public class TodosUsuarios implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank @Column(unique = true)
    private String nome;
    @NotBlank @Column(unique = true)
    private String email;
    @NotBlank @Column(unique = true)
    private String telefone;
    @NotBlank @JsonAlias
    private String senha;
    @NotNull
    private Role role;
    @NotNull
    private boolean ativo;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name().toUpperCase()));
    }
    @Override
    public String getPassword() {
        return this.getSenha();
    }
    @Override
    public String getUsername() {
        return this.getEmail();
    }
    public TodosUsuarios(Tecnico tecnico) {}
    public TodosUsuarios(Usuario usuario) {}
    public TodosUsuarios(Administrador admin) {
        //TODO Auto-generated constructor stub
    }
}
