package com.connectMentor.auth.services;

import com.connectMentor.auth.Util.PasswordUtil;
import com.connectMentor.auth.domain.mentorado.Mentor;
import com.connectMentor.auth.repositories.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class MentorService {

	@Autowired
	private MentorRepository mentorRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private FileService fileService;

	public void salvarMentor(Mentor mentor, MultipartFile certificadoFile) throws IOException {
		if (certificadoFile != null && !certificadoFile.isEmpty()) {
			String fileName = "certificado_" + System.currentTimeMillis() + ".pdf";
			String filePath = "C:\\Users\\Felipe Senna\\Documents\\imagensProj";
			String certificadoPath = fileService.addImage(fileName, certificadoFile.getBytes(), filePath);
			mentor.setCertificadoPath(certificadoPath);
		}
		mentor.setSenha(PasswordUtil.encode(mentor.getSenha())); // Criptografando a senha antes de salvar
		mentorRepository.save(mentor);
		enviarEmailConfirmacao(mentor); // Enviando email de confirmação após salvar o mentor
	}

	public Mentor buscarIdMentor(Long idMentor) {
		Optional<Mentor> mentor = mentorRepository.findById(idMentor);
		return mentor.orElse(null);
	}

	private void enviarEmailConfirmacao(Mentor mentor) {
		String subject = "Confirmação de Cadastro";
		String content = "Olá " + mentor.getNome() + ",\n\nSeu cadastro foi realizado com sucesso!\n\nObrigado!";
		emailService.sendEmail(mentor.getEmail(), subject, content);
	}

	public Mentor buscarPorEmail(String email) {
		Optional<Mentor> mentor = mentorRepository.findByEmail(email);
		return mentor.orElse(null);
	}

	public Mentor buscarPorSenha(String senha) {
		Optional<Mentor> mentor = mentorRepository.findBySenha(senha);
		return mentor.orElse(null);
	}

	public void atualizarFotoMentor(Long idMentor, byte[] fotoBytes) {
		Long idMentorLong = idMentor.longValue();
		Mentor mentor = mentorRepository.findById(idMentorLong).orElse(null);
		if (mentor != null) {
			mentor.setFoto(fotoBytes);
			mentorRepository.save(mentor);
		}
	}
}
