package com.revature.data;

import com.revature.beans.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerDAO extends JpaRepository<Trainer, Integer> {

}
