
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Client } from '../model/Client';
import { ClientService } from '../client.service';
import { MatDialog } from '@angular/material/dialog';
import { ClientEditComponent } from '../client-edit/client-edit.component';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss']
})
export class ClientListComponent implements OnInit {

  dataSource = new MatTableDataSource<Client>();
  displayedColumns:string[] = ['id', 'name', 'action'];

  constructor(private clientService:ClientService, public dialog:MatDialog, private http:HttpClient) { }

  ngOnInit(): void {
    this.clientService.getClients().subscribe(data => this.dataSource.data = data);
  }

  editClient(client:Client):void{
     const dialogRef = this.dialog.open(ClientEditComponent, {data:{client:client}});
     dialogRef.afterClosed().subscribe(result => {this.ngOnInit();}); 
  }

  createClient():void{
    const dialogRef = this.dialog.open(ClientEditComponent, {data:{}});
    dialogRef.afterClosed().subscribe(result => {this.ngOnInit();});
  }

  deleteClient(client:Client):void{
    const dialogRef = this.dialog.open(DialogConfirmationComponent, {
      data:{title:"Eliminar cliente", description:"Atención si borra el cliente se perderán sus datos.<br> ¿Desea eliminar el cliente?"}
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.clientService.deleteClient(client.id).subscribe(result => {this.ngOnInit();})
      }
    })
  }
}
