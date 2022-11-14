import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Category } from './model/Category';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpClient:HttpClient) { }

  getCategories():Observable<Category[]>{
    return this.httpClient.get<Category[]>('http://localhost:8080/category');
  }

  saveCategory(category:Category):Observable<Category>{

    let url = "http://localhost:8080/category";
    if(category.id != null) url += "/" + category.id;

    return this.httpClient.put<Category>(url, category);
  }

  deleteCategory(idCategory:number):Observable<any>{
    return this.httpClient.delete('http://localhost:8080/category/' + idCategory);
  }
}
