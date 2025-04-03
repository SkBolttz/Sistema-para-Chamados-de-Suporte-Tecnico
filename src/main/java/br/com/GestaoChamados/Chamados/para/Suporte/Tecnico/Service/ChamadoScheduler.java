package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum.Prioridade;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Enum.StatusChamado;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.TodosChamados;
import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.ChamadoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChamadoScheduler {

    private final ChamadoRepository chamadoRepository;

    public ChamadoScheduler(ChamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void alterarPrioridade() {
        List<TodosChamados> chamados = chamadoRepository.findAll()
                .stream()
                .filter(e -> e.getStatus() == StatusChamado.ABERTO)
                .filter(e -> e.getDataAbertura().plusMinutes(20).isBefore(LocalDateTime.now()))
                .toList();

        if (!chamados.isEmpty()) {
            chamados.forEach(this::aumentarPrioridade);
            chamadoRepository.saveAll(chamados);
        }
    }

    private void aumentarPrioridade(TodosChamados chamado) {
        switch (chamado.getPrioridade()) {
            case BAIXA -> chamado.setPrioridade(Prioridade.MEDIA);
            case MEDIA -> chamado.setPrioridade(Prioridade.ALTA);
            case ALTA -> chamado.setPrioridade(Prioridade.CRITICA);
            default -> {
            }
        }
    }
}
