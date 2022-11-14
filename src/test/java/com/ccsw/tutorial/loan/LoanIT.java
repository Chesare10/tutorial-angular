package com.ccsw.tutorial.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.util.UriComponentsBuilder;

import com.ccsw.tutorial.author.model.AuthorSearchDto;
import com.ccsw.tutorial.category.model.CategoryDto;
import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.game.model.GameDto;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LoanIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICEPATH = "/loan/";

    private static final String GAME_ID_PARAM = "idGame";
    private static final String CLIENT_ID_PARAM = "idClient";
    private static final String DATE_PARAM = "date";

    private static final Long EXISTS_GAME_ID = 1L;
    private static final Long EXISTS_CLIENT_ID = 1L;
    private static final String EXISTS_DATE = "4/11/2022";

    private static final Long NOT_EXISTS_GAME_ID = 20L;
    private static final Long NOT_EXISTS_CLIENT_ID = 20L;
    private static final String NOT_EXISTS_DATE = "4/11/1900";

    private static final Long DELETE_LOAN_ID = 1L;

    private static final int PAGE_SIZE = 5;
    private static final int TOTAL_LOANS = 8;

    private static final String VALID_LOAN_DATE = "03-10-2022";
    private static final String VALID_RETURN_DATE = "10-10-2022";
    private static final String INVALID_RETURN_DATE = "20-11-2022";
    private static final String INVALID_LOAN_DATE = "20-12-2022";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<Page<LoanDto>> responseTypePage = new ParameterizedTypeReference<Page<LoanDto>>() {
    };

    private String getUrlWithParams() {
        return UriComponentsBuilder.fromHttpUrl(LOCALHOST + port + SERVICEPATH)
                .queryParam(GAME_ID_PARAM, "{" + GAME_ID_PARAM + "}")
                .queryParam(CLIENT_ID_PARAM, "{" + CLIENT_ID_PARAM + "}").queryParam(DATE_PARAM, "{" + DATE_PARAM + "}")
                .encode().toUriString();
    }

    @Test
    public void findWithoutFiltersShouldReturnAllLoansInDB() {

        int LOANS_WITHOUT_FILTER = 8;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, null);
        params.put(DATE_PARAM, null);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage, params);

        assertNotNull(response);
        assertEquals(LOANS_WITHOUT_FILTER, response.getBody().getTotalElements());
    }

    @Test
    public void findExistsGameShouldReturnLoans() {

        int LOANS_WITH_FILTER = 1;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, EXISTS_GAME_ID);
        params.put(CLIENT_ID_PARAM, null);
        params.put(DATE_PARAM, null);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage, params);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
    }

    @Test
    public void findExistsClientShouldReturnLoans() {

        int LOANS_WITH_FILTER = 2;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, EXISTS_CLIENT_ID);
        params.put(DATE_PARAM, null);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage, params);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
    }

    @Test
    public void findExistsDateShouldReturnLoans() {

        int LOANS_WITH_FILTER = 3;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, null);
        params.put(DATE_PARAM, EXISTS_DATE);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage, params);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
    }

    @Test
    public void findExistsGameDateShouldReturnLoans() {

        int LOANS_WITH_FILTER = 1;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, EXISTS_GAME_ID);
        params.put(CLIENT_ID_PARAM, null);
        params.put(DATE_PARAM, EXISTS_DATE);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage, params);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
    }

    @Test
    public void findExistsClientDateShouldReturnLoans() {

        int LOANS_WITH_FILTER = 1;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, EXISTS_CLIENT_ID);
        params.put(DATE_PARAM, EXISTS_DATE);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage, params);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
    }

    @Test
    public void findExistsGameClientShouldReturnLoans() {

        int LOANS_WITH_FILTER = 1;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, EXISTS_GAME_ID);
        params.put(CLIENT_ID_PARAM, EXISTS_CLIENT_ID);
        params.put(DATE_PARAM, null);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage, params);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
    }

    @Test
    public void findExistsGameClientDateShouldReturnLoans() {

        int LOANS_WITH_FILTER = 1;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, EXISTS_GAME_ID);
        params.put(CLIENT_ID_PARAM, EXISTS_CLIENT_ID);
        params.put(DATE_PARAM, EXISTS_DATE);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage, params);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
    }

    @Test
    public void findNotExistsGameShouldReturnEmpty() {

        int LOANS_WITH_FILTER = 0;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, NOT_EXISTS_GAME_ID);
        params.put(CLIENT_ID_PARAM, null);
        params.put(DATE_PARAM, null);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage, params);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
    }

    @Test
    public void findNotExistsClientShouldReturnEmpty() {

        int LOANS_WITH_FILTER = 0;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, NOT_EXISTS_CLIENT_ID);
        params.put(DATE_PARAM, null);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage, params);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
    }

    @Test
    public void findNotExistsDateShouldReturnEmpty() {

        int LOANS_WITH_FILTER = 0;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, null);
        params.put(DATE_PARAM, NOT_EXISTS_DATE);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage, params);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
    }

    @Test
    public void findNotExistsGameOrClientShouldReturnEmpty() {

        int LOANS_WITH_FILTER = 0;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, NOT_EXISTS_GAME_ID);
        params.put(CLIENT_ID_PARAM, NOT_EXISTS_CLIENT_ID);
        params.put(DATE_PARAM, null);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage, params);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
    }

    @Test
    public void findNotExistsGameOrDateShouldReturnEmpty() {

        int LOANS_WITH_FILTER = 0;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, NOT_EXISTS_GAME_ID);
        params.put(CLIENT_ID_PARAM, null);
        params.put(DATE_PARAM, NOT_EXISTS_DATE);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage, params);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
    }

    @Test
    public void findNotExistsClientOrDateShouldReturnEmpty() {

        int LOANS_WITH_FILTER = 0;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, NOT_EXISTS_CLIENT_ID);
        params.put(DATE_PARAM, NOT_EXISTS_DATE);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage, params);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
    }

    @Test
    public void findNotExistsGameOrClientOrDateShouldReturnEmpty() {

        int LOANS_WITH_FILTER = 0;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, NOT_EXISTS_GAME_ID);
        params.put(CLIENT_ID_PARAM, NOT_EXISTS_CLIENT_ID);
        params.put(DATE_PARAM, NOT_EXISTS_DATE);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage, params);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
    }

    @Test
    public void deleteWithExistsIdShouldDeleteLoan() {

        long LOANS_WITH_DELETE = 7;

        restTemplate.exchange(LOCALHOST + port + SERVICEPATH + DELETE_LOAN_ID, HttpMethod.DELETE, null, Void.class);

        AuthorSearchDto searchDto = new AuthorSearchDto();
        searchDto.setPageable(PageRequest.of(0, TOTAL_LOANS));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICEPATH, HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_DELETE, response.getBody().getTotalElements());

    }

    @Test
    public void deleteWithNotExistsIdShouldThrowException() {

        long deleteLoanId = TOTAL_LOANS + 1;

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICEPATH + deleteLoanId,
                HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void saveWithValidDataShouldInsertNewLoan() throws ParseException {
        LoanDto dto = new LoanDto();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);

        GameDto gameDto = new GameDto();
        gameDto.setId(1L);
        gameDto.setCategory(categoryDto);

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);

        dto.setClient(clientDto);
        dto.setGame(gameDto);

        Date dateLoan = formatter.parse(VALID_LOAN_DATE);
        dto.setDateLoan(dateLoan);

        Date dateReturn = formatter.parse(VALID_RETURN_DATE);
        dto.setDateReturn(dateReturn);

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, null);
        params.put(DATE_PARAM, null);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage, params);

        assertNotNull(response);
        assertEquals(8, response.getBody().getTotalElements());

        restTemplate.exchange(LOCALHOST + port + SERVICEPATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST, new HttpEntity<>(searchDto),
                responseTypePage, params);

        assertNotNull(response);
        assertEquals(9, response.getBody().getTotalElements());
    }

    @Test
    public void savwWithLoanLongerThan14DaysShouldBadRequestError() throws ParseException {
        LoanDto dto = new LoanDto();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);

        GameDto gameDto = new GameDto();
        gameDto.setId(1L);
        gameDto.setCategory(categoryDto);

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);

        dto.setClient(clientDto);
        dto.setGame(gameDto);

        Date dateLoan = formatter.parse(VALID_LOAN_DATE);
        dto.setDateLoan(dateLoan);

        Date dateReturn = formatter.parse(INVALID_RETURN_DATE);
        dto.setDateReturn(dateReturn);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICEPATH, HttpMethod.PUT,
                new HttpEntity<>(dto), Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void savwWithLoanAfterReturnShouldBadRequestError() throws ParseException {
        LoanDto dto = new LoanDto();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);

        GameDto gameDto = new GameDto();
        gameDto.setId(1L);
        gameDto.setCategory(categoryDto);

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);

        dto.setClient(clientDto);
        dto.setGame(gameDto);

        Date dateLoan = formatter.parse(INVALID_LOAN_DATE);
        dto.setDateLoan(dateLoan);

        Date dateReturn = formatter.parse(INVALID_RETURN_DATE);
        dto.setDateReturn(dateReturn);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICEPATH, HttpMethod.PUT,
                new HttpEntity<>(dto), Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void saveWithGameHavingClientShouldConflictError() throws ParseException {
        LoanDto dto = new LoanDto();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);

        GameDto gameDto = new GameDto();
        gameDto.setId(1L);
        gameDto.setCategory(categoryDto);

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);

        dto.setClient(clientDto);
        dto.setGame(gameDto);

        Date dateLoan = formatter.parse("4-11-2022");
        dto.setDateLoan(dateLoan);

        Date dateReturn = formatter.parse("14-11-2022");
        dto.setDateReturn(dateReturn);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICEPATH, HttpMethod.PUT,
                new HttpEntity<>(dto), Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void saveWithClientHavingMaxGamesShouldConflictError() throws ParseException {
        LoanDto dto = new LoanDto();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);

        GameDto gameDto = new GameDto();
        gameDto.setId(4L);
        gameDto.setCategory(categoryDto);

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);

        dto.setClient(clientDto);
        dto.setGame(gameDto);

        Date dateLoan = formatter.parse("04-11-2022");
        dto.setDateLoan(dateLoan);

        Date dateReturn = formatter.parse("11-11-2022");
        dto.setDateReturn(dateReturn);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICEPATH, HttpMethod.PUT,
                new HttpEntity<>(dto), Void.class);

        gameDto.setId(5L);
        gameDto.setCategory(categoryDto);

        dto.setGame(gameDto);
        dateLoan = formatter.parse("08-11-2022");
        dateReturn = formatter.parse("20-11-2022");

        response = restTemplate.exchange(LOCALHOST + port + SERVICEPATH, HttpMethod.PUT, new HttpEntity<>(dto),
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

}
