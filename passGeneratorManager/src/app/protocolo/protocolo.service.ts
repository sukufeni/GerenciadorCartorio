import { Injectable } from '@angular/core';
import { Protocolo } from './Protocolo';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { Pessoa } from '../pessoa/Pessoa';
@Injectable({
  providedIn: 'root'
})
export class ProtocoloService {

  constructor(private http: HttpClient) { }

  public getProtocolobyId(id: number): Observable<Protocolo> {
    return this.http.get<Protocolo>(environment.apiBaseUrl + "/protocolo/find/" + id);
  }

  public getProtocolobyPessoa(idPessoa: number): Observable<Protocolo> {
    return this.http.get<Protocolo>(environment.apiBaseUrl + "/protocolo/pessoa/find/" + idPessoa);
  }

  public getProtocolos(): Observable<Protocolo[]> {
    return this.http.get<Protocolo[]>(environment.apiBaseUrl + "/protocolo/all");
  }

  public getTipoProtocolos(): Observable<Map<string, number>[]> {
    return this.http.get<Map<string, number>[]>(environment.apiBaseUrl + "/protocolo/tipos");
  }

  public gerarProtocolo(protocolo: Protocolo, idTitular: number): Observable<Protocolo> {
    var auxProtocolo = {
      titularProtocolo: idTitular,
      dataEntrega: protocolo.dataEntrega,
      qualidadeProtocolo: protocolo.qualidadeProtocolo
    }
    return this.http.post<Protocolo>(environment.apiBaseUrl + "/protocolo/gerar", auxProtocolo);
  }

  public imprimirProtocolo(idCartorio: number, data: Date): Observable<Protocolo[]> {
    var auxQuery = {
      "idCartorio": idCartorio,
      "dataProtocolo": data
    }
    return this.http.post<Protocolo[]>(environment.apiBaseUrl + "/protocolo/imprimir", auxQuery);
  }
}
