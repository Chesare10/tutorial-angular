import { DialogRef } from '@angular/cdk/dialog';
import { Component, OnInit, Inject} from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Client } from '../model/Client';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-client-edit',
  templateUrl: './client-edit.component.html',
  styleUrls: ['./client-edit.component.scss']
})
export class ClientEditComponent implements OnInit {

  client:Client;

  constructor(public dialog:DialogRef<ClientEditComponent>, 
              @Inject(MAT_DIALOG_DATA) public data:any,
              private clientService:ClientService) { }

  ngOnInit(): void {
    if(this.data != null){
      this.client = Object.assign({}, this.data.client);
    }else{
      this.client = new Client();
    }
  }

  onSave(){
    this.clientService.saveClient(this.client).subscribe(result => {this.dialog.close();},
    error => {alert("El nombre del cliente ya existe. Elija otro nombre")});
    
  }

  onClose(){
    this.dialog.close();
  }

}
