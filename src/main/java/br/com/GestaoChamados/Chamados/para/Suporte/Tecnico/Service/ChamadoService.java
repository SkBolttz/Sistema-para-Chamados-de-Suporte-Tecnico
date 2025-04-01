package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.AberturaChamadoDTO;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum.Prioridade;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum.StatusChamado;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.ChamadoAberto;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Tecnico;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Usuario;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.UsuarioException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.ChamadoRepository;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.UsuarioRepository;

@Service
public class ChamadoService {
    
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public ResponseEntity<String> abrirChamado(AberturaChamadoDTO chamado, Tecnico tecnico) throws UsuarioException {
        
        Usuario user = usuarioRepository.findByNome(chamado.nomeUsuario());
        
        if(user == null){
            throw new UsuarioException("Usuário incorreto!");
        }

        ChamadoAberto chamadoEntity = new ChamadoAberto();

        chamadoEntity.setUsuario(user.getId());
        chamadoEntity.setStatus(StatusChamado.ABERTO);
        chamadoEntity.setPrioridade(Prioridade.BAIXA);
        chamadoEntity.setTitulo(chamado.titulo());
        chamadoEntity.setDescricao(chamado.descricao());


        chamadoRepository.save(chamadoEntity);

        return ResponseEntity.ok("Chamado aberto com sucesso!");
    }


}
