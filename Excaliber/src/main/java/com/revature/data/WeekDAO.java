package com.revature.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Week;

@Repository
public interface WeekDAO extends JpaRepository<Week, Integer> {

}
