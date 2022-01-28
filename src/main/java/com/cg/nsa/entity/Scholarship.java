package com.cg.nsa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Scholarship {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int scholarshipId;
	private String scholarshipName;		//Prime Minister Scholarship Scheme/SwarnaJayanti Fellowships Scheme, etc..
	private String field;		// Medical, Law, Engineering
	private String course;		// LLB, MBA, MBBS, BE, BTech, MTech, BCA
	private int courseYear;		// Current course year
	private double sscScore;
	private double hscScore;
	private double familyIncome;
	private String bankName;
	private String bankIfsc;
	@Column(unique = true)
	private String accountNo;
	@OneToOne
	private Student student;		
	@OneToOne
	private Institution institute;	
	private String appStatus;		// Pending/Approved/Rejected
	private String approval;		// Pending/Granted
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + scholarshipId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scholarship other = (Scholarship) obj;
		if (scholarshipId != other.scholarshipId)
			return false;
		return true;
	}
	public int getScholarshipId() {
		return scholarshipId;
	}
	public void setScholarshipId(int scholarshipId) {
		this.scholarshipId = scholarshipId;
	}
	public String getScholarshipName() {
		return scholarshipName;
	}
	public void setScholarshipName(String scholarshipName) {
		this.scholarshipName = scholarshipName;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public int getCourseYear() {
		return courseYear;
	}
	public void setCourseYear(int courseYear) {
		this.courseYear = courseYear;
	}
	public double getSscScore() {
		return sscScore;
	}
	public void setSscScore(double sscScore) {
		this.sscScore = sscScore;
	}
	public double getHscScore() {
		return hscScore;
	}
	public void setHscScore(double hscScore) {
		this.hscScore = hscScore;
	}
	public double getFamilyIncome() {
		return familyIncome;
	}
	public void setFamilyIncome(double familyIncome) {
		this.familyIncome = familyIncome;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankIfsc() {
		return bankIfsc;
	}
	public void setBankIfsc(String bankIfsc) {
		this.bankIfsc = bankIfsc;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Institution getInstitute() {
		return institute;
	}
	public void setInstitute(Institution institute) {
		this.institute = institute;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}


}
