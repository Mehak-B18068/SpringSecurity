package com.mehak.springboot1.repositories;

import com.mehak.springboot1.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker,Long> {

}
