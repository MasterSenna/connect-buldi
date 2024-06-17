package com.connectMentor.auth.repositories;



import com.connectMentor.auth.domain.mentorado.MentoradoPretensao;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface MentoradoPretensaoRepository extends JpaRepository<MentoradoPretensao, Long> {

}
