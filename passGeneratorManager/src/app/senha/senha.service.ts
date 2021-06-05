import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Senha } from './Senha';

@Injectable({
  providedIn: 'root'
})

export class SenhaService {
  private url = environment.apiBaseUrl + "/senhas";
  constructor(private http: HttpClient) { }

  public proximaSenha(): Observable<Senha> {
    return this.http.get<Senha>(this.url + "/proximaSenha");
  }

  public gerarSenha(senha: Senha): Observable<Senha> {
    return this.http.post<Senha>(this.url + "/gerar", senha);
  }

  public getSenhas(): Observable<Senha[]> {
    return this.http.get<Senha[]>(this.url + "/all");
  }

}
