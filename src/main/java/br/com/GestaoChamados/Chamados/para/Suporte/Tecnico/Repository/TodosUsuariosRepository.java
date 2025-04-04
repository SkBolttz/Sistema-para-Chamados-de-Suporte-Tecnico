package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.TodosUsuarios;


@Repository
public interface TodosUsuariosRepository extends JpaRepository<TodosUsuarios, Long> {

    TodosUsuarios findByEmail(String username);

    TodosUsuarios findByNome(String username);

    TodosUsuarios findByEmailAndSenha(String email, String senha);

    TodosUsuarios findByTelefone(String telefone);
    
}
