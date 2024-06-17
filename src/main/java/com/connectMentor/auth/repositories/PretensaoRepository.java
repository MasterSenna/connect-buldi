package com.connectMentor.auth.repositories;

import com.connectMentor.auth.domain.mentorado.Pretensao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PretensaoRepository extends JpaRepository<Pretensao, Long> {

}
