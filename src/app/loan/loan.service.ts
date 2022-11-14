import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { LoanPage } from './model/LoanPage';
import { Pageable } from '../core/model/page/Pageable';
import { HttpClient } from '@angular/common/http';
import { Loan } from './model/Loan';

@Injectable({
  providedIn: 'root'
})
export class LoanService {

  constructor(private http:HttpClient) { }

  getLoans(pageable:Pageable, idGame?:number, idClient?:number, date?:Date):Observable<LoanPage>{
    return this.http.post<LoanPage>(this.composeFindUrl(idGame, idClient, date), {pageable:pageable});
  }

  deleteLoan(loanId:number):Observable<void>{
    return this.http.delete<void>('http://localhost:8080/loan/'+ loanId);
  }

  private composeFindUrl(gameId?:number, clientId?:number, date?:Date) : string {
    let params = '';

    if (gameId != null) {
        params += 'idGame='+ gameId;
    }

    if (clientId != null) {
        if (params != '') params += "&";
        params += "idClient="+ clientId;
    }

    if (date!= null) {
      if (params != '') params += "&";
      //params += "date="+ date.toISOString();
      params += "date="+ date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
  }

    let url = 'http://localhost:8080/loan'

    if (params == '') return url;
    else return url + '?'+params;
  }

  saveLoan(loan:Loan):Observable<void>{
    let url = 'http://localhost:8080/loan';
    return this.http.put<void>(url, loan);
  }
}
