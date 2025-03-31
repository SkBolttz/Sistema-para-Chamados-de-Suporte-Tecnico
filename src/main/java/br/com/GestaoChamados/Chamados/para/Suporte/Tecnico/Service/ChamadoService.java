package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.UsuarioDTO;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum.Prioridade;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum.StatusChamado;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Chamado;
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

    public ResponseEntity<String> abrirChamado(Chamado chamado, UsuarioDTO usuario) throws UsuarioException {
        
        Usuario user = usuarioRepository.findByNome(usuario.nome());

        if(user == null){
            throw new UsuarioException("Usuário incorreto!");
        }

        chamado.setUsuario(user);
        chamado.setStatus(StatusChamado.ABERTO);
        chamado.setPrioridade(Prioridade.BAIXA);

        chamadoRepository.save(chamado);

        return ResponseEntity.ok("Chamado aberto com sucesso!");
    }


}
