package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Assessment;
import com.revature.beans.Batch;
import com.revature.beans.Category;
import com.revature.beans.Trainer;
import com.revature.beans.Week;
import com.revature.tables.AssessmentByCategory;

@Service
public class AssessmentByCategoryService {
	private StoreRetrieveService SRSserv;
	@Autowired
	public AssessmentByCategoryService(StoreRetrieveService s) {
		SRSserv=s;
	}
	
	public AssessmentByCategory getABCTable(int id) {
		Trainer t=SRSserv.getTrainerById(id);
		AssessmentByCategory table=new AssessmentByCategory();
		
		List<Category> categories=SRSserv.getAllCategories();
	    ArrayList<ArrayList<Float>> assessScores=new ArrayList<ArrayList<Float>>(); ;
	    ArrayList<ArrayList<Float>> rawScores=new ArrayList<ArrayList<Float>>();
	    ArrayList<Float[]> averageForCat=new ArrayList<Float[]>();
	    ArrayList<ArrayList<String>> typeForScore=new ArrayList<ArrayList<String>>();
	    //Get rawScores and AssessmentScores for each category
	    for (Category cat:categories) {
	    	ArrayList<Float> singleCatAScores=new ArrayList<Float>();
	    	ArrayList<Float> singleCatRawScores=new ArrayList<Float>();
	    	ArrayList<String> singleCatTypeForScore=new ArrayList<String>();
		    for (Batch b:t.getBatches()) {
		    	for (Week w:b.getWeeks()) {
		    		for (Assessment a:w.getAssessments()) {
		    			if (a.getSkillCategory().getId()==cat.getId()) {
		    				Float assessScore=a.getAverage();
		    				Float rawScore=Float.valueOf(a.getScoreWeight());
		    				String type=a.getType();
		    				assessScore=(assessScore/100)*rawScore; //Convert AssessScore into suitable form
		    				singleCatAScores.add(assessScore);
		    				singleCatRawScores.add(rawScore);
		    				singleCatTypeForScore.add(type);
		    			}
		    			
		    		}
		    	}
		    }
		    assessScores.add(singleCatAScores); //Add scores for single category to list
		    rawScores.add(singleCatRawScores);
		    typeForScore.add(singleCatTypeForScore);
	    }
	    //Find average overall score for each category
	    for (int i=0;i<assessScores.size();i++) {
	    	float numeratorExam=0;
	    	float denominatorExam=0;
	    	float numeratorVerbal=0;
	    	float denominatorVerbal=0;
	    	float numeratorPresentation=0;
	    	float denominatorPresentation=0;
	    	
	    	for (int j=0;j<assessScores.get(i).size();j++) {
	    		String type=typeForScore.get(i).get(j);
	    		if (type.contains("Exam")) {
	    			numeratorExam+=assessScores.get(i).get(j);
	    			denominatorExam+=rawScores.get(i).get(j);
	    		}else if (type.contains("Verbal")) {
	    			numeratorVerbal+=assessScores.get(i).get(j);
	    			denominatorVerbal+=rawScores.get(i).get(j);
	    		}else if (type.contains("Presentation")) {
	    			numeratorPresentation+=assessScores.get(i).get(j);
	    			denominatorPresentation+=rawScores.get(i).get(j);
	    		}
	    	}
	    	float averageExam=0;
	    	float averageVerbal=0;
	    	float averagePresentation=0;
	    	if (denominatorExam!=0) {
	    		averageExam=(numeratorExam/denominatorExam)*100;
	    	}
	    	if (denominatorVerbal!=0) {
	    		averageVerbal=(numeratorVerbal/denominatorVerbal)*100;
	    	}
	    	if (denominatorPresentation!=0) {
	    		averagePresentation=(numeratorPresentation/denominatorPresentation)*100;
	    	}
	    	Float[] average= {averageExam, averageVerbal, averagePresentation};
	    	averageForCat.add(average);
	    }
	    
	    
	    table.setCategories(categories);
	    
	    table.setAverage(averageForCat);
		return table;
	}
}