package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table
public class Trainer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String email;
	@Column
	private String employeeId; // the trainer's ID from Caliber
	
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name="trainer_batch",
			joinColumns=@JoinColumn(name="trainer_id"),
			inverseJoinColumns=@JoinColumn(name="batch_id")) //needs column names--------------------------
	private Set<Batch> batches;

	public Trainer(String firstName, String lastName, String email, String name) {
		id = 0;
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		employeeId = "";
		batches = null;
	}

	public Trainer(String firstName, String lastName, String email) {
		this.id = 0;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		//this.employeeId = employeeId;
		//this.batches = batches;
	}

	public Integer getTrainerId() {
		return id;
	}

	public void setTrainerId(Integer trainerId) {
		this.id = trainerId;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Set<Batch> getBatches() {
		return batches;
	}

	public void setBatches(Set<Batch> batches) {
		this.batches = batches;
	}

	public Trainer(Integer trainerId, String firstName, String lastName, String email, String employeeId, Set<Batch> batches) {
		this.id = trainerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.employeeId = employeeId;
		this.batches = batches;
	}
}
