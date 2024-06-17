package com.connectMentor.auth.services;

import java.util.List;

import com.connectMentor.auth.domain.mentorado.MentoradoPretensao;
import com.connectMentor.auth.repositories.MentoradoPretensaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MentoradoPretensaoService {

	// Injeção de dependências;
	@Autowired
	MentoradoPretensaoRepository repository;

	// Método para salvar pretensoes dos mentorados;
	public void salvarPretensao(MentoradoPretensao mentorado) {
		repository.save(mentorado);
	}

	// Método para buscar as pretensões do mentorado;
	public List<MentoradoPretensao> findAll() {
		return repository.findAll();
	}

}
