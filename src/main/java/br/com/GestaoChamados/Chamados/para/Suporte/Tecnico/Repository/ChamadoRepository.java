package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Chamado;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    
}
