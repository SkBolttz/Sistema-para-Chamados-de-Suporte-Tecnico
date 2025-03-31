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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "tb_tecnico")
public class Tecnico implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank @Column(unique = true)
    private String nome;
    @NotBlank @Column(unique = true)
    private String email;
    @NotBlank @Column(unique = true)
    private String telefone;
    @NotBlank
    private String senha;
    private Role role;
    @NotNull
    private NivelTecnico nivelTecnico;
    private boolean ativo;
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados;
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
}
