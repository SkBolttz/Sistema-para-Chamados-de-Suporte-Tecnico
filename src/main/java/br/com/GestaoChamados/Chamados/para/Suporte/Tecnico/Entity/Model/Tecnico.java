package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum.NivelTecnico;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum.Role;
import jakarta.persistence.Column;
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
@Table(name = "tb_tecnico")
public class Tecnico implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Column(unique = true)
    @Size(min = 6, max = 100, message = "O nome de usuário deve ter entre 6 e 100 caracteres")
    private String nome;
    @NotBlank
    @Column(unique = true)
    @Size(min = 6, max = 100, message = "O e-mail de usuário deve ter entre 6 e 100 caracteres")
    private String email;
    @NotBlank
    @Column(unique = true)
    @Size(min = 6, max = 100, message = "O telefone de usuário deve ter entre 6 e 100 caracteres")
    private String telefone;
    @NotBlank
    @Size(min = 6, max = 100, message = "A senha de usuário deve ter entre 6 e 100 caracteres")
    private String senha;
    @Enumerated(EnumType.STRING)
    private Role role;
    @NotNull
    @Enumerated(EnumType.STRING)
    private NivelTecnico nivelTecnico;
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

    public Tecnico(TodosUsuarios user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.telefone = user.getTelefone();
        this.senha = user.getSenha();
        this.role = user.getRole();
        this.ativo = user.isAtivo();
    }
}
