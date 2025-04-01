package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.AberturaChamadoDTO;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.AtenderChamado;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum.Prioridade;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum.StatusChamado;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.TodosChamados;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Tecnico;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Usuario;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.UsuarioException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.ChamadoRepository;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.TecnicoRepository;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.UsuarioRepository;

@Service
public class ChamadoService {
    
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Transactional
    public ResponseEntity<String> abrirChamado(AberturaChamadoDTO chamado, Tecnico tecnico) throws UsuarioException {
        
        Usuario user = usuarioRepository.findByNome(chamado.nomeUsuario());
        
        if(user == null){
            throw new UsuarioException("Usuário incorreto!");
        }

        TodosChamados chamadoEntity = new TodosChamados();

        chamadoEntity.setUsuario(user.getId());
        chamadoEntity.setStatus(StatusChamado.ABERTO);
        chamadoEntity.setPrioridade(Prioridade.BAIXA);
        chamadoEntity.setTitulo(chamado.titulo());
        chamadoEntity.setDescricao(chamado.descricao());


        chamadoRepository.save(chamadoEntity);

        return ResponseEntity.ok("Chamado aberto com sucesso!");
    }

    public ResponseEntity<String> atenderChamado(AtenderChamado chamado) {

        Optional<TodosChamados> chamadoEntity = chamadoRepository.findById(chamado.id());

        if(chamadoEntity.isPresent() && chamadoEntity.get().getStatus() == StatusChamado.ABERTO){
            
            Tecnico tecnicoEntity = tecnicoRepository.findByNome(chamado.nomeTecnico());

            var role = tecnicoEntity.getAuthorities().stream().findFirst().get().getAuthority();

            if(!role.equalsIgnoreCase("ROLE_TECNICO")){
                return ResponseEntity.badRequest().body("Apenas tecnicos podem atender chamados!");
            }

            if(tecnicoEntity != null){

                chamadoEntity.get().setStatus(StatusChamado.ANDAMENTO);
                chamadoEntity.get().setTecnico(tecnicoEntity.getId());
                chamadoRepository.save(chamadoEntity.get());

                return ResponseEntity.ok("Chamado " + chamado.titulo() + " atendido com sucesso!");
            }
        }
        return ResponseEntity.badRequest().body("Chamado " + chamado.titulo() + " nao encontrado!");
}

    public List<String> listarChamadosAbertos(){
        return chamadoRepository.findAll()
        .stream()
        .filter(e -> e.getStatus() == StatusChamado.ABERTO)
        .map(e -> e.toString())
        .toList();
    }
}
