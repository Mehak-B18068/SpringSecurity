package com.mehak.springboot1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mehak.springboot1.models.Session;
public interface SessionRepository extends JpaRepository<Session,Long> {

}
