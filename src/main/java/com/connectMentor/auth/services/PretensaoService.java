package com.connectMentor.auth.services;

import java.util.Optional;

import com.connectMentor.auth.domain.mentorado.Pretensao;
import com.connectMentor.auth.repositories.PretensaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PretensaoService {

	@Autowired
	PretensaoRepository repository;

	// Método para recuperar o mentorado pelo id;
	public Pretensao buscarIdPretensao(Long idPretensao) {
		Optional<Pretensao> pretensao = repository.findById(idPretensao);
		return pretensao.orElse(null);
	}
}
