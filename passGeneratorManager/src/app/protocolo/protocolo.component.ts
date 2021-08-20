import { HttpErrorResponse } from '@angular/common/http';
import { stringify } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Pessoa } from '../pessoa/Pessoa';
import { PessoaService } from '../pessoa/pessoa.service';
import { Protocolo } from './Protocolo';
import { ProtocoloService } from './protocolo.service';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-protocolo',
  templateUrl: './protocolo.component.html',
  styleUrls: ['./protocolo.component.css']
})
export class ProtocoloComponent implements OnInit {
  public protocolos: Protocolo[] = [];
  public deleteProtocolo: Protocolo | null = null;
  constructor(
    private router: Router,
    private protocoloService: ProtocoloService,
    private pessoaService: PessoaService) { }


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
        console.log(error.message)
        this.router.navigate(['/error'])
      }
    );
  }

  public onModalImprimirOpen(): void {
    const container = document.getElementById('main-container');
    const btn = document.createElement('button');
    btn.type = 'button';
    btn.style.display = 'none';
    btn.setAttribute('data-toggle', 'modal');
    btn.setAttribute('data-target', '#imprimirProtocoloModel');
    container?.appendChild(btn);
    btn.click();
  }

  public onModadeletelOpen(protocolo: Protocolo): void {
    const container = document.getElementById('main-container');
    const btn = document.createElement('button');
    btn.type = 'button';
    btn.style.display = 'none';


    btn.setAttribute('data-toggle', 'modal');
    btn.setAttribute('data-target', '#deleteProtocoloaModal');
    this.deleteProtocolo = protocolo;

    container?.appendChild(btn);
    btn.click();
  }

  public onDeleteClick(deleteForm: NgForm): void {
    var idProtocolo = this.deleteProtocolo?.id.toString();
    var observacao = deleteForm.value["observacao"];
    this.protocoloService.deleteProtocolo(idProtocolo, observacao).subscribe(
      (Response: Boolean) => {
        if (Response === true) {
          window.location.reload();
          alert("Protocolo Excluido com sucesso! ");
        }
      },
      (error: HttpErrorResponse) => {
        alert("Não foi possível apagar o Protocolo.");
      }
    );
  }

  public onImprimirClick(addForm: NgForm): void {
    var dataProtocolo = new Date().toLocaleDateString();
    var idCartorio = addForm.value["idCartorio"];
    var filename: string = `Relatorio_${dataProtocolo}_${this.getTituloCartorio(idCartorio)}.xlsx`;
    this.protocoloService.imprimirProtocolo(idCartorio).subscribe(
      (response: Protocolo[]) => {
        this.protocolos = response;
        this.getPessoafromProtocolo();
        this.setTituloCartorioFromProtocolos();
        
        const ws: XLSX.WorkSheet = XLSX.utils.json_to_sheet(JSON.parse(JSON.stringify(this.protocolos)));
        const wb: XLSX.WorkBook = XLSX.utils.book_new();

        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

        /* save to file */
        XLSX.writeFile(wb, filename);
      },
      (error: HttpErrorResponse) => {
        alert("Não foi possivel gerar o Relatório.")
      }
    );
  }
}
