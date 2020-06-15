package com.revature.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.services.BatchTechnicalStatusBySkillCategoryService;
import com.revature.tables.BatchTechnicalStatusBySkillCategory;

@RestController
@CrossOrigin(origins ="http://localhost:4200", allowCredentials = "true")
@RequestMapping(path="/BatchTechnicalStatusBySkillCategory")
public class BatchTechnicalStatusBySkillCategoryController {
	private BatchTechnicalStatusBySkillCategoryService tsbscServ;
	
	public Logger log = Logger.getLogger(BatchTechnicalStatusBySkillCategoryController.class);
	
	@Autowired
	public BatchTechnicalStatusBySkillCategoryController(BatchTechnicalStatusBySkillCategoryService serv) {
		tsbscServ = serv;
	}
	
	@GetMapping
	public ResponseEntity<List<BatchTechnicalStatusBySkillCategory>> getTableDataObject(){
		log.info("Retriving graph table data for get request ");
		List<BatchTechnicalStatusBySkillCategory> graphTableObjects = tsbscServ.getTableData();
		return ResponseEntity.ok(graphTableObjects);
	}
	
}