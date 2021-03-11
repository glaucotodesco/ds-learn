package com.devsuperior.dslearn.repositories;


import com.devsuperior.dslearn.entities.Enrollment;
import com.devsuperior.dslearn.entities.pk.EnrollmentPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository  extends JpaRepository<Enrollment, EnrollmentPK>{
    
}
