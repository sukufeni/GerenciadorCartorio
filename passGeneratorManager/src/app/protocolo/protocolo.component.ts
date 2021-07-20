import { HttpErrorResponse } from '@angular/common/http';
import { stringify } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Pessoa } from '../pessoa/Pessoa';
import { PessoaService } from '../pessoa/pessoa.service';
import { Protocolo } from './Protocolo';
import { ProtocoloService } from './protocolo.service';

@Component({
  selector: 'app-protocolo',
  templateUrl: './protocolo.component.html',
  styleUrls: ['./protocolo.component.css']
})
export class ProtocoloComponent implements OnInit {
  public protocolos: Protocolo[] = [];
  constructor(private protocoloService: ProtocoloService, private pessoaService: PessoaService) { }

  ngOnInit() {
    this.getProtocolos();
  }

  private getPessoafromProtocolo(): void {
    this.protocolos.forEach(Protocolo => {
      this.pessoaService.getPessoa(Protocolo.titularProtocolo).subscribe(
        (response: Pessoa) => {
          Protocolo.nomeTitular = response.nome;
        }
      );
    });
  }
  private setTituloCartorioFromProtocolos(): void {
    this.protocolos.forEach(auxProtocolo => {
      if (auxProtocolo.cartorio == 1) {
        auxProtocolo.tituloCartorio = "Tabelionato de Notas"
      }
      else if (auxProtocolo.cartorio == 2) {
        auxProtocolo.tituloCartorio = "Protesto de Títulos"
      }
      else if (auxProtocolo.cartorio == 3) {
        auxProtocolo.tituloCartorio = "Registro Civil"
      }
    });
  }

  private getTituloCartorio(idCartorio: any): string {

    if (idCartorio == 1) {
      return "Tabelionato de Notas";
    }
    else if (idCartorio == 2) {
      return "Protesto de Títulos";
    }
    else if (idCartorio == 3) {
      idCartorio = "Registro Civil";
    }
    return "";
  }

  public getProtocolos(): void {
    this.protocoloService.getProtocolos().subscribe(
      (response: Protocolo[]) => {
        this.protocolos = response;
        this.getPessoafromProtocolo();
        this.setTituloCartorioFromProtocolos();
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }
  public onModalOpen(): void {
    const container = document.getElementById('main-container');
    const btn = document.createElement('button');
    btn.type = 'button';
    btn.style.display = 'none';
    btn.setAttribute('data-toggle', 'modal');
    btn.setAttribute('data-target', '#imprimirProtocoloModel');
    container?.appendChild(btn);
    btn.click();
  }

  public onImprimirClick(addForm: NgForm): void {
    var dataProtocolo = addForm.value["dataProtocolo"];
    var idCartorio = addForm.value["idCartorio"];
    var filename: string = `Relatorio_${dataProtocolo}_${this.getTituloCartorio(idCartorio)}`;
    this.protocoloService.imprimirProtocolo(idCartorio, dataProtocolo).subscribe(
      (response: Protocolo[]) => {
        this.protocolos = response;
        this.getPessoafromProtocolo();
        this.setTituloCartorioFromProtocolos();
        var link = document.createElement("a");
        link.download = `${filename}.json`;
        var data = "text/json;charset=utf-8," + JSON.stringify(this.protocolos);
        link.href = "data:" + data;
        link.click();
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }
}
