package com.devsuperior.dslearn.repositories;


import com.devsuperior.dslearn.entities.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository  extends JpaRepository<Reply, Long>{
    
}
