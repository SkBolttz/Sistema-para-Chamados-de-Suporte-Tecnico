package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.AberturaChamadoDTO;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.AtenderChamado;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.DTO.FinalizarChamado;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum.Prioridade;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum.StatusChamado;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.TodosChamados;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.LogChamado;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Tecnico;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.Usuario;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.ChamadoException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.TecnicoException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Exception.UsuarioException;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.ChamadoRepository;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.LogChamadoRepository;
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
    @Autowired
    private LogChamadoRepository logChamadoRepository;

    @Transactional
    public void abrirChamado(AberturaChamadoDTO chamado) throws UsuarioException {
        
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
        chamadoEntity.setDataAbertura(LocalDateTime.now());

        chamadoRepository.save(chamadoEntity);

    }

    @Transactional
    public void atenderChamado(AtenderChamado chamado) throws ChamadoException, TecnicoException {

        Optional<TodosChamados> chamadoEntity = chamadoRepository.findById(chamado.id())
        .stream()
        .filter(e -> e.getStatus() == StatusChamado.ABERTO)
        .findFirst();

        if(!chamadoEntity.isPresent()){
            throw new ChamadoException("Chamado nao encontrado!");
        }

        if(chamadoEntity.isPresent() && chamadoEntity.get().getStatus() == StatusChamado.ABERTO){
            
            Tecnico tecnicoEntity = tecnicoRepository.findByNome(chamado.nomeTecnico());
            
            if(tecnicoEntity == null){
                throw new TecnicoException("Tecnico nao encontrado!");
            }

                chamadoEntity.get().setStatus(StatusChamado.ANDAMENTO);
                chamadoEntity.get().setTecnico(tecnicoEntity.getId());
                chamadoRepository.save(chamadoEntity.get());
        }
}

    public List<String> listarChamadosAbertos(){
        return chamadoRepository.findAll()
        .stream()
        .filter(e -> e.getStatus() == StatusChamado.ABERTO)
        .map(e -> e.toString())
        .toList();
    }

@Transactional
public void finalizarChamado(FinalizarChamado chamado) throws ChamadoException {
    
    TodosChamados chamadoEntity = chamadoRepository.findById(chamado.id())
        .orElseThrow(() -> new ChamadoException("Chamado nao encontrado!"));

    if (chamadoEntity.getStatus() != StatusChamado.ANDAMENTO) {
        throw new ChamadoException("Chamado nao encontrado!");
    }

    chamadoEntity.setStatus(StatusChamado.ENCERRADO);
    chamadoEntity.setDataFechamento(LocalDateTime.now());
    chamadoRepository.save(chamadoEntity);

    LogChamado logChamado = new LogChamado();
    logChamado.setDataLog(LocalDateTime.now());
    logChamado.setFinalizacao(chamado.finalizacao());
    logChamado.setChamado(chamadoEntity);
    logChamadoRepository.save(logChamado);
}
}
