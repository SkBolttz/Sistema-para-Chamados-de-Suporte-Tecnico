package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Repository.TecnicoRepository;

@Service
public class TecnicoService {
    
    @Autowired
    private TecnicoRepository tecnicoRepository;
}
