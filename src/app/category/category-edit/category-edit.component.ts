import { Component, OnInit, Inject} from '@angular/core';
import { CategoryService } from '../category.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Category } from '../model/Category';

@Component({
  selector: 'app-category-edit',
  templateUrl: './category-edit.component.html',
  styleUrls: ['./category-edit.component.scss']
})
export class CategoryEditComponent implements OnInit {

  category:Category;

  constructor(private categoryService:CategoryService, 
              public dialogRef:MatDialogRef<CategoryEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    if (this.data.category != null) {
      //this.category = this.data.category;
      this.category = Object.assign({}, this.data.category);
    }
    else {
      this.category = new Category();
    }
  }

  onSave(){
      this.categoryService.saveCategory(this.category).subscribe(result => {this.dialogRef.close();});
  }

  onClose(){
      this.dialogRef.close();
  }
}
