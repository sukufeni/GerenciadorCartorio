import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, Optional } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Pessoa } from '../pessoa/Pessoa';
import { PessoaService } from '../pessoa/pessoa.service';
import { Protocolo } from '../protocolo/Protocolo';
import { ProtocoloService } from '../protocolo/protocolo.service';
import { Senha } from './Senha';
import { SenhaService } from './senha.service';

@Component({
  selector: 'app-senha',
  templateUrl: './senha.component.html',
  styleUrls: ['./senha.component.css']
})
export class SenhaComponent implements OnInit {
  public senhas: Senha[] = [];
  public senha!: Senha;
  public deleteSenha: Senha;
  public protocolo!: Protocolo;
  private idPessoa: number = -1;
  private idProtocolo: number = -1;
  public TipoProtocolos: any[] = [];

  constructor(private senhaService: SenhaService, private pessoaService: PessoaService, private protocoloService: ProtocoloService) { }

  ngOnInit() {
    this.getSenhas();
    this.getTipoProtocolos();
  }

  public getTipoProtocolos(): void {
    this.protocoloService.getTipoProtocolos().subscribe(
      (response: Map<String, Number>[]) => {
        this.TipoProtocolos = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    )
  }

  public getSenhas(): void {
    this.senhaService.getSenhas().subscribe(
      (response: Senha[]) => {
        this.senhas = response;
        this.getPessoa();
        this.getProtocolo();
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }

  public proximaSenha(): void {
    this.senhaService.proximaSenha().subscribe(
      (response: Senha) => { this.senha = response; },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }

  public gerarSenha(senha: NgForm): void {
    this.pessoaService.gerarPessoa(senha.value).subscribe((responsePessoa: Pessoa) => {
      this.idPessoa = responsePessoa.id;
      this.protocoloService.gerarProtocolo(senha.value, this.idPessoa).subscribe(
        (responseProtocolo: Protocolo) => {
          this.idProtocolo = responseProtocolo.id;
          this.senhaService.gerarSenha(senha.value, this.idPessoa, this.idProtocolo).subscribe(
            (response: Senha) => {
              this.senha = response;
              window.location.reload();
            },
            (error: HttpErrorResponse) => {
              alert(error.message)
            }
          );
        },
        (error: HttpErrorResponse) => {
          alert(error.message)
        }
      );
    },
      (error: HttpErrorResponse) => {
        alert(error.message)
      });
  }

  private getPessoa(): void {
    this.senhas.forEach(auxSenha => {
      this.pessoaService.getPessoa(auxSenha.idPessoa).subscribe(
        (response: Pessoa) => {
          auxSenha.pessoa = response;
        },
        (error: HttpErrorResponse) => {
          alert(error.message)
        }
      );
    });
  }

  private getProtocolo(): void {
    this.senhas.forEach(auxSenha => {
      this.protocoloService.getProtocolobyId(auxSenha.id).subscribe(
        (response: Protocolo) => { auxSenha.protocolo = response; },
        (error: HttpErrorResponse) => {
          alert(error.message)
        }
      );
    });
  }

  public onDeleteSenha(id: number): void {
    this.senhaService.deleteSenha(id).subscribe(
      (resp: void)=>{
        window.location.reload();
        this.getSenhas();
      }
    )
  }

  public onModalOpen(senha: Senha | null, mode: string): void {
    const container = document.getElementById('main-container');
    const btn = document.createElement('button');
    btn.type = 'button';
    btn.style.display = 'none';
    btn.setAttribute('data-toggle', 'modal');

    if (mode === 'add') {
      btn.setAttribute('data-target', '#addSenhaModal');
    }
    if (mode === 'proxima') {

    }
    if (mode === 'delete') {
      this.deleteSenha = senha;
      console.log(senha?.id);
      btn.setAttribute('data-target', '#DeleteSenhaModal');
    }
    container?.appendChild(btn);
    btn.click();
  }
}
