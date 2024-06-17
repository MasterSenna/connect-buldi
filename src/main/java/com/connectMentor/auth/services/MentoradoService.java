package com.connectMentor.auth.services;

import com.connectMentor.auth.Util.PasswordUtil;
import com.connectMentor.auth.domain.mentorado.Mentorado;
import com.connectMentor.auth.repositories.MentoradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MentoradoService {

	@Autowired
	private MentoradoRepository mentoradoRepository;

	@Autowired
	private EmailService emailService;

	public void salvarMentorado(Mentorado mentorado) {
		mentorado.setSenha(PasswordUtil.encode(mentorado.getSenha())); // Criptografando a senha antes de salvar
		mentoradoRepository.save(mentorado);
		enviarEmailConfirmacao(mentorado); // Enviando email de confirmação após salvar o mentorado
	}

	public Mentorado buscarIdMentorado(Long idMentorado) {
		Optional<Mentorado> mentorado = mentoradoRepository.findById(idMentorado);
		return mentorado.orElse(null);
	}

	private void enviarEmailConfirmacao(Mentorado mentorado) {
		String subject = "Confirmação de Cadastro";
		String content = "Olá " + mentorado.getNome() + ",\n\nSeu cadastro foi realizado com sucesso!\n\nObrigado!";
		emailService.sendEmail(mentorado.getEmail(), subject, content);
	}

	public Mentorado buscarPorEmail(String email) {
		Optional<Mentorado> mentorado = mentoradoRepository.findByEmail(email);
		return mentorado.orElse(null);
	}

	public Mentorado buscarPorSenha(String senha) {
		Optional<Mentorado> mentorado = mentoradoRepository.findBySenha(senha);
		return mentorado.orElse(null);
	}

	public void atualizarFotoMentorado(Long idMentorado, byte[] fotoBytes) {
		Long idMentoradoLong = idMentorado.longValue();
		Mentorado mentorado = mentoradoRepository.findById(idMentoradoLong).orElse(null);
		if (mentorado != null) {
			mentorado.setFoto(fotoBytes);
			mentoradoRepository.save(mentorado);
		}
	}
}
