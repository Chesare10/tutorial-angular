package com.ccsw.tutorial.loan;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.tutorial.loan.model.Loan;

public interface LoanRepository extends CrudRepository<Loan, Long> {

    @Query("select l from Loan l where (:game_id is null or l.game.id = :game_id) and (:client_id is null or l.client.id = :client_id)"
            + " and (:date is null or :date BETWEEN l.dateLoan AND l.dateReturn)")
    Page<Loan> find(@Param("game_id") Long game_id, @Param("client_id") Long client_id, @Param("date") Date date,
            Pageable pageable);

    @Query("select count (l) from Loan l where (:client_id is null or l.client.id = :client_id)"
            + " and (:date_loan is null or :date_loan BETWEEN l.dateLoan AND l.dateReturn or :date_return BETWEEN l.dateLoan AND l.dateReturn)")
    long countClientLoans(@Param("client_id") long clientId, @Param("date_loan") Date dateLoan,
            @Param("date_return") Date dateReturn);

    @Query("select count (l) from Loan l where (:game_id is null or l.game.id = :game_id)"
            + " and (:date_loan is null or :date_loan BETWEEN l.dateLoan AND l.dateReturn  or :date_return BETWEEN l.dateLoan AND l.dateReturn)")
    long countGameLoans(@Param("game_id") long gameId, @Param("date_loan") Date dateLoan,
            @Param("date_return") Date dateReturn);

}
