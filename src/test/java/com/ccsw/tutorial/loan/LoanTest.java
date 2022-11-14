package com.ccsw.tutorial.loan;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoanTest {

    private static final Long EXIST_LOAN_ID = 1L;

    @Mock
    LoanRepository loanRepository;

    @InjectMocks
    LoanServiceImpl loanService;

    @Test
    public void deleteLoanExistsIdShouldDelete() {
        loanService.delete(EXIST_LOAN_ID);

        verify(loanRepository).deleteById(EXIST_LOAN_ID);
    }

}
