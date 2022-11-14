import { Component, Inject, OnInit } from '@angular/core';
import { AuthorService } from '../author.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Author } from '../model/Author';

@Component({
  selector: 'app-author-edit',
  templateUrl: './author-edit.component.html',
  styleUrls: ['./author-edit.component.scss']
})
export class AuthorEditComponent implements OnInit {

  author:Author;

  constructor(private authorService:AuthorService, 
              public dialog:MatDialogRef<AuthorEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data:any) { }

  ngOnInit(): void {
    if(this.data.author != null){
        this.author = Object.assign({}, this.data.author);
    }
    else{
        this.author = new Author();
    }
  }

  onSave(){
    this.authorService.saveAuthor(this.author).subscribe(result => {this.dialog.close();});
  }

  onClose(){
      this.dialog.close();
  }

}
