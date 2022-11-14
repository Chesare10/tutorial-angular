import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthorListComponent } from './author-list/author-list.component';
import { AuthorEditComponent } from './author-edit/author-edit.component';
import { MatTableModule } from '@angular/material/table';
import { CoreModule } from '../core/core.module';
import { MatDialogModule, MAT_DIALOG_DATA  } from '@angular/material/dialog';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AuthorListComponent,
    AuthorEditComponent
  ],
  imports: [
    CommonModule,
    MatTableModule,
    CoreModule,
    MatDialogModule,
    MatPaginatorModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    HttpClientModule
  ],
  providers: [
    {
      provide:MAT_DIALOG_DATA,
      useValue: {},
    }, 
  ]
})
export class AuthorModule { }
