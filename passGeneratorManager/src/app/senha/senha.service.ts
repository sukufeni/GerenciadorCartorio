import { HttpClient } from '@angular/common/http';
import { Variable } from '@angular/compiler/src/render3/r3_ast';
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

  public gerarSenha(senha: Senha, idPessoa: Number, idProtocolo: Number): Observable<Senha> {
    var auxSenha = {
      categoria: senha.categoria,
      idPessoa: idPessoa,
      idProtocolo: idProtocolo
    }
    return this.http.post<Senha>(this.url + "/gerar", auxSenha);
  }

  public getSenhas(): Observable<Senha[]> {
    return this.http.get<Senha[]>(this.url + "/all");
  }
  public deleteSenha(id:number): Observable<void>{
    return this.http.delete<void>(`${this.url}/delete/${id}`);
  }

}
