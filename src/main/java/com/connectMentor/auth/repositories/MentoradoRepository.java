package com.connectMentor.auth.repositories;

import java.util.Optional;

import com.connectMentor.auth.domain.mentorado.Mentorado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MentoradoRepository extends JpaRepository<Mentorado, Long> {

	Optional<Mentorado> findByEmail(String email);

	Optional<Mentorado> findBySenha(String senha);

}
