import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
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
  public protocolos: Protocolo[]=[];
  public pessoa: any;
  constructor(private protocoloService: ProtocoloService, private pessoaService: PessoaService) { }

  ngOnInit() {
    this.getProtocolos();
  }

  private getPessoafromProtocolo(idPessoa: number):void{
    this.pessoaService.getPessoa(idPessoa).subscribe(
      (response:Pessoa)=>{
        this.pessoa= response;
      }
    );
  }
  
  public getProtocolos():void{
    this.protocoloService.getProtocolos().subscribe(
      (response:Protocolo[])=>{
        this.protocolos=response;
        this.protocolos.forEach(Protocolo => {
          this.getPessoafromProtocolo(Protocolo.titularProtocolo);
        });
      },
      (error: HttpErrorResponse)=>{
        alert(error.message)
      }
    );
  }
}
