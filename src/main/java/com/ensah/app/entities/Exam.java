package com.ensah.app.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Exam {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long idExem;
	 private Date date;
	 private Date  heureDebut;
	 private Date heureFin;
	 private int duréeprévue;
	 private int duréeréelle;
	 private String epreuve;
	 private String pv;
	 private String rapport;
	 
	 
	 @ManyToOne
	  private TypeExam typeExam;
	 @OneToMany(mappedBy = "exam")
	  private List<Surveillance> Surveillances;
	 
	 @ManyToOne
	 @JoinColumn(name = "id_element")
	 private ElementPedagodique elementPedagogique;
	 
	 @ManyToOne
	 private Semestre semestre;
	 
	 @ManyToOne
	  private Session session;
	 
	 
	public Long getIdExem() {
		return idExem;
	}
	public void setIdExem(Long idExem) {
		this.idExem = idExem;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}
	public Date getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}
	public int getDuréeprévue() {
		return duréeprévue;
	}
	public void setDuréeprévue(int duréeprévue) {
		this.duréeprévue = duréeprévue;
	}
	public int getDuréeréelle() {
		return duréeréelle;
	}
	public void setDuréeréelle(int duréeréelle) {
		this.duréeréelle = duréeréelle;
	}
	public String getEpreuve() {
		return epreuve;
	}
	public void setEpreuve(String epreuve) {
		this.epreuve = epreuve;
	}
	public String getPv() {
		return pv;
	}
	public void setPv(String pv) {
		this.pv = pv;
	}
	public String getRapport() {
		return rapport;
	}
	public void setRapport(String rapport) {
		this.rapport = rapport;
	}
	public TypeExam getTypeExam() {
		return typeExam;
	}
	public void setTypeExam(TypeExam typeExam) {
		this.typeExam = typeExam;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	@Override
	public String toString() {
		return "Exam [idExem=" + idExem + ", date=" + date + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin
				+ ", duréeprévue=" + duréeprévue + ", duréeréelle=" + duréeréelle + ", epreuve=" + epreuve + ", pv="
				+ pv + ", rapport=" + rapport + ", typeExam=" + typeExam + ", semestre=" + semestre + ", session="
				+ session + "]";
	}
	public Exam(Long idExem, Date date, Date heureDebut, Date heureFin, int duréeprévue, int duréeréelle,
			String epreuve, String pv, String rapport, TypeExam typeExam, Semestre semestre, Session session) {
		super();
		this.idExem = idExem;
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.duréeprévue = duréeprévue;
		this.duréeréelle = duréeréelle;
		this.epreuve = epreuve;
		this.pv = pv;
		this.rapport = rapport;
		this.typeExam = typeExam;
		this.semestre = semestre;
		this.session = session;
	}
	 
	 
	 
     public Exam() {}


}
