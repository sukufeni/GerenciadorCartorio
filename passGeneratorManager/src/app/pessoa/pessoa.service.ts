import { Injectable } from '@angular/core';
import { Pessoa } from './Pessoa';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
@Injectable({
  providedIn: 'root'
})
export class PessoaService {
  constructor(private http: HttpClient) { }

  public getPessoas(): Observable<Pessoa[]> {
    return this.http.get<Pessoa[]>(environment.apiBaseUrl + "/pessoa/all");
  }
  public getPessoa(id: number): Observable<Pessoa> {
    return this.http.get<Pessoa>(environment.apiBaseUrl + "/pessoa/find/" + id);
  }
  public gerarPessoa(pessoa: Pessoa): Observable<Pessoa> {
    var auxPessoa = {
      nome: pessoa.nome,
      cpf : pessoa.cpf,
      email: pessoa.email,
      telefone: pessoa.telefone
    }
    return this.http.post<Pessoa>(environment.apiBaseUrl + "/pessoa/gerar", auxPessoa);
  }
}
