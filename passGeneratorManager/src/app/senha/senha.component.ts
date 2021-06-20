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
  public pessoas: Pessoa[] = [];
  public pessoa!: Pessoa;
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
        this.senhas.forEach(element => {
          this.getPessoa(element.idPessoa);
          this.getProtocolo(Number(element.idProtocolo));
          element.pessoa = this.pessoa;
          element.protocolo = this.protocolo;
        });
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

  private getPessoa(id: number): void {
    this.pessoaService.getPessoa(id).subscribe(
      (response: Pessoa) => {
        this.pessoa = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }
  private getProtocolo(id: number): void {
    this.protocoloService.getProtocolobyId(id).subscribe(
      (response: Protocolo) => { this.protocolo = response; },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
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
    //Tratar o gerar senha aqui?
    if (mode === 'delete') {
      btn.setAttribute('data-target', '#DeleteSenhaModal');
    }
    container?.appendChild(btn);
    btn.click();
  }
}
