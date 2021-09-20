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

  public deleteProtocolo(idProtocolo: String, motivo: String): Observable<Boolean> {
    var auxProtocolo = {
      idProtocolo: idProtocolo,
      motivo: motivo
    }
    return this.http.put<Boolean>(environment.apiBaseUrl + "/protocolo/disable", auxProtocolo);
  }

  public imprimirProtocoloDetalhado(idProtocolo: String): Observable<Blob> {
    var auxQuery = {
      "idProtocolo": idProtocolo
    }
    return this.http.post(environment.apiBaseUrl + "/protocolo/imprimir/detalhado", auxQuery, { responseType: 'blob' });
  }
  public imprimirProtocolo(idCartorio: number): Observable<Protocolo[]> {
    var auxQuery = {
      "idCartorio": idCartorio
    }
    return this.http.post<Protocolo[]>(environment.apiBaseUrl + "/protocolo/imprimir", auxQuery);
  }
}
