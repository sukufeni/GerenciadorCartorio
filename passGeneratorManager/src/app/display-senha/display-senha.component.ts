import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Pessoa } from '../pessoa/Pessoa';
import { PessoaService } from '../pessoa/pessoa.service';
import { Protocolo } from '../protocolo/Protocolo';
import { ProtocoloService } from '../protocolo/protocolo.service';
import { Senha } from '../senha/Senha';
import { SenhaService } from '../senha/senha.service';

@Component({
  selector: 'app-display-senha',
  templateUrl: './display-senha.component.html',
  styleUrls: ['./display-senha.component.css']
})
export class DisplaySenhaComponent implements OnInit {

  public currSenha: Senha;

  constructor(private senhaService: SenhaService, private protocoloService: ProtocoloService, private pessoaService: PessoaService) { }

  ngOnInit(): void {
    this.proximaSenha();
  }

  public proximaSenha(): void {
    this.senhaService.proximaSenha().subscribe(
      (response: Senha) => {
        this.currSenha = response;
        this.getPessoa();
        this.getProtocolo();
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }

  private getPessoa(): void {
    this.pessoaService.getPessoa(this.currSenha.idPessoa).subscribe(
      (response: Pessoa) => {
        this.currSenha.pessoa = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }

  private getProtocolo(): void {
    this.protocoloService.getProtocolobyId(this.currSenha.id).subscribe(
      (response: Protocolo) => { this.currSenha.protocolo = response; },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }
}
