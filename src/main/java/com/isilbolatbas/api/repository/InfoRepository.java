package com.isilbolatbas.api.repository;

import com.isilbolatbas.api.model.Info;
import com.isilbolatbas.api.model.InfoDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.List;

@Repository
public interface InfoRepository extends JpaRepository<Info, Integer> {
    @Query(
            value = "SELECT id, firstname, lastname, phone, email FROM isil.info",
            nativeQuery = true)
    Collection<InfoDetail> findAllInfoDetails();

}
