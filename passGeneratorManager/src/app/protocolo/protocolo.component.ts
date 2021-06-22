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
  constructor(private protocoloService: ProtocoloService, private pessoaService: PessoaService) { }

  ngOnInit() {
    this.getProtocolos();
  }

  private getPessoafromProtocolo():void{
    this.protocolos.forEach(Protocolo => {
      this.pessoaService.getPessoa(Protocolo.titularProtocolo).subscribe(
        (response:Pessoa)=>{
          Protocolo.nomeTitular = response.nome;
        }
      );  
    });
  }
    private getCartorioFromProtcolo():void{
    this.protocolos.forEach(auxProtocolo=> {
      if(auxProtocolo.cartorio==1){
        auxProtocolo.tituloCartorio = "Tabelionato de Notas"
      }
      else if(auxProtocolo.cartorio==2){
        auxProtocolo.tituloCartorio = "Protesto de Títulos"
      }
      else if(auxProtocolo.cartorio==3){
        auxProtocolo.tituloCartorio = "Registro Civil"
      }
    });
  }
  
  public getProtocolos():void{
    this.protocoloService.getProtocolos().subscribe(
      (response:Protocolo[])=>{
        this.protocolos=response;
        this.getPessoafromProtocolo();
        this.getCartorioFromProtcolo();
      },
      (error: HttpErrorResponse)=>{
        alert(error.message)
      }
    );
  }
}
