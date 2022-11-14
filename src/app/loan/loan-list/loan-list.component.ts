import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { LoanService } from '../loan.service';
import { MatDialog } from '@angular/material/dialog';
import { LoanEditComponent } from '../loan-edit/loan-edit.component';
import { PageEvent } from '@angular/material/paginator';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { Loan } from '../model/Loan';
import { GameService } from 'src/app/game/game.service';
import { ClientService } from 'src/app/client/client.service';
import { Client } from 'src/app/client/model/Client';
import { Game } from 'src/app/game/model/Game';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';

@Component({
  selector: 'app-loan-list',
  templateUrl: './loan-list.component.html',
  styleUrls: ['./loan-list.component.scss']
})
export class LoanListComponent implements OnInit {

  pageNumber:number = 0;
  pageSize:number = 5;
  totalElements:number = 0;

  games:Game[];
  clients:Client[];
  clientFilter:Client;
  gameFilter:Game;
  dateFilter:Date;

  gameIdFilter:number;
  clientIdFilter:number;
  dateTrueFilter:Date;

  dataSource = new MatTableDataSource<Loan>();
  displayedColumns: string[] = ['id', 'game', 'client', 'dateLoan', 'dateReturn', 'action'];

  constructor(private loanService:LoanService, private dialog:MatDialog, 
    private gameService:GameService, private clientService:ClientService) { }

  ngOnInit(): void {

    this.gameService.getGames().subscribe(games => this.games = games);
    this.clientService.getClients().subscribe(clients => this.clients = clients);

    this.loadPage();
  }

  createLoan() {    
    const dialogRef = this.dialog.open(LoanEditComponent, {
        data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
        this.onSearch();
    });    
  }  

  deleteLoan(loan:Loan){
    const dialogRef = this.dialog.open(DialogConfirmationComponent, {
      data: { title:"Eliminar préstamo", 
              description:"Atención si elimina el préstamo se perderán sus datos.<br> ¿Desea eliminar el préstamo?" 
            }
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.loanService.deleteLoan(loan.id).subscribe(result => {
          this.onSearch()});
      }
    }
    );
  }

  loadPage(event?:PageEvent){
    let pageable:Pageable = {
      pageNumber:this.pageNumber,
      pageSize:this.pageSize,
      sort: [
        {property:'id', direction:'ASC'}
      ]
    }

    if(event != null){
      pageable.pageNumber = event.pageIndex;
      pageable.pageSize = event.pageSize;
    }

    this.loanService.getLoans(pageable, this.gameIdFilter, this.clientIdFilter, this.dateTrueFilter).subscribe(loans => {
      this.dataSource.data = loans.content,
      this.pageNumber = loans.pageable.pageNumber,
      this.pageSize = loans.pageable.pageSize,
      this.totalElements = loans.totalElements
    }   
    );
  }

  onCleanFilter(){

    this.gameFilter = null;
    this.clientFilter = null;
    this.dateFilter =  null;
    
    this.onSearch();
  }

  onSearch(){

    this.gameIdFilter = this.gameFilter != null ? this.gameFilter.id : null;
    this.clientIdFilter = this.clientFilter != null ? this.clientFilter.id : null;
    this.dateTrueFilter = this.dateFilter != null ? this.dateFilter : null;

    this.loadPage();
  }
}
