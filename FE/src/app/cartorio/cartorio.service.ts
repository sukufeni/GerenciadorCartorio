import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Cartorio } from "./Cartorio";

@Injectable({
  providedIn: 'root'
})
export class CartorioService {

  constructor(private http: HttpClient) { }

  public getCartorios(): Observable<Cartorio[]> {
    return this.http.get<Cartorio[]>(environment.apiBaseUrl + "/cartorio/all");
  }

  public getCartorio(id:number):Observable<Cartorio>{
    return this.http.get<Cartorio>(environment.apiBaseUrl+"/cartorio/find/"+id);
  }
}
