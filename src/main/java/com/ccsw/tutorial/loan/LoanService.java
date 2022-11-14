package com.ccsw.tutorial.loan;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

public interface LoanService {

    Page<Loan> findPage(Long idGame, Long idClient, Date date, LoanSearchDto dto);

    void save(LoanDto dto) throws InvalidLoanDatesException, LoanLongerThan14DaysException, SameClientTwoLoansException,
            SameGameTwoClientsException;

    void delete(Long id);

    Boolean verifyClientHasMaxTwoLoans(long clientId, Date dateLoan, Date dateReturn);

    Boolean verifyGameHasMaxOneClient(long gameId, Date dateLoan, Date dateReturn);
}
