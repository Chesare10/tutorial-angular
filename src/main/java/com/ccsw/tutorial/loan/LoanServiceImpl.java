package com.ccsw.tutorial.loan;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    GameService gameService;

    @Override
    public Page<Loan> findPage(Long idGame, Long idClient, Date date, LoanSearchDto dto) {
        return this.loanRepository.find(idGame, idClient, date, dto.getPageable());
    }

    @Override
    public void save(LoanDto dto) throws InvalidLoanDatesException, LoanLongerThan14DaysException,
            SameClientTwoLoansException, SameGameTwoClientsException {

        if (!dto.getDateLoan().after(dto.getDateReturn())) {
            long diffInMillies = Math.abs(dto.getDateReturn().getTime() - dto.getDateLoan().getTime());
            long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if (diffInDays <= 14) {
                if (this.verifyClientHasMaxTwoLoans(dto.getClient().getId(), dto.getDateLoan(), dto.getDateReturn())) {
                    if (this.verifyGameHasMaxOneClient(dto.getGame().getId(), dto.getDateLoan(), dto.getDateReturn())) {
                        Loan loan = new Loan();

                        BeanUtils.copyProperties(dto, loan, "id", "game", "client");

                        loan.setGame(gameService.get(dto.getGame().getId()));
                        loan.setClient(clientService.get(dto.getClient().getId()));

                        this.loanRepository.save(loan);
                    } else
                        throw new SameGameTwoClientsException();

                } else
                    throw new SameClientTwoLoansException();

            } else
                throw new LoanLongerThan14DaysException();
        } else
            throw new InvalidLoanDatesException();
    }

    @Override
    public void delete(Long id) {
        this.loanRepository.deleteById(id);
    }

    @Override
    public Boolean verifyClientHasMaxTwoLoans(long clientId, Date dateLoan, Date dateReturn) {

        Boolean ok = true;

        long client_loans = this.loanRepository.countClientLoans(clientId, dateLoan, dateReturn);

        if (client_loans >= 2)
            ok = false;

        return ok;
    }

    @Override
    public Boolean verifyGameHasMaxOneClient(long gameId, Date dateLoan, Date dateReturn) {
        Boolean ok = true;

        long game_loans = this.loanRepository.countGameLoans(gameId, dateLoan, dateReturn);

        if (game_loans >= 1)
            ok = false;

        return ok;
    }
}
