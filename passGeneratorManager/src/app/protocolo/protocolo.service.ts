import { Injectable } from '@angular/core';
import {Protocolo} from './Protocolo';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import { Pessoa } from '../pessoa/Pessoa';
@Injectable({
  providedIn: 'root'
})
export class ProtocoloService {

  constructor(private http:HttpClient) { }

  public getProtocolobyId(id:number): Observable<Protocolo>{
    return this.http.get<Protocolo>(environment.apiBaseUrl+"/protocolo/find/"+id);
  }

  public getProtocolobyPessoa(pessoa:Pessoa): Observable<Protocolo>{
    return this.http.get<Protocolo>(environment.apiBaseUrl+"/protocolo/pessoa/find"+pessoa.id);
  }

  public getProtocolos():Observable<Protocolo[]>{
    return this.http.get<Protocolo[]>(environment.apiBaseUrl+"protocolo/all");
  }

  public gerarProtocolo(protocolo:Protocolo, idTitular: Number):Observable<Protocolo>{
    var auxProtocolo= {
      titularProtocolo : idTitular,
      dataEntrega : protocolo.dataEntrega,
      qualidadeProtocolo : protocolo.qualidadeProtocolo
    }
    return this.http.post<Protocolo>(environment.apiBaseUrl+"/protocolo/gerar",auxProtocolo);
  }

}
