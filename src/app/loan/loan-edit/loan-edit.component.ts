import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ClientService } from 'src/app/client/client.service';
import { GameService } from 'src/app/game/game.service';
import { Loan } from '../model/Loan';
import { Game } from 'src/app/game/model/Game';
import { Client } from 'src/app/client/model/Client';
import { LoanService } from '../loan.service';


@Component({
  selector: 'app-loan-edit',
  templateUrl: './loan-edit.component.html',
  styleUrls: ['./loan-edit.component.scss']
})
export class LoanEditComponent implements OnInit {

  loan:Loan;
  clients:Client[];
  games:Game[];

  constructor(public dialogRef:MatDialogRef<LoanEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data:any,
              private clientService:ClientService,
              private gameService:GameService,
              private loanService:LoanService) { }

  ngOnInit(): void {
    this.loan = new Loan();

    this.clientService.getClients().subscribe(
      clients => {
        this.clients = clients;
      }
    )

    this.gameService.getGames().subscribe(
      games =>{
        this.games = games;
      }
    )
  }

  onSave(){
    this.loanService.saveLoan(this.loan).subscribe(result => {this.dialogRef.close();},
    error => {alert(error.error.message);});
  }

  onClose(){
    this.dialogRef.close();
  }
}
