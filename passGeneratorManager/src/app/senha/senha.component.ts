import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, Optional } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Pessoa } from '../pessoa/Pessoa';
import { PessoaService } from '../pessoa/pessoa.service';
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
  private idPessoa: Number = -1;

  constructor(private senhaService: SenhaService, private pessoaService: PessoaService) { }

  ngOnInit() {
    this.getSenhas();
  }
  public getSenhas(): void {
    this.senhaService.getSenhas().subscribe(
      (response: Senha[]) => {
        this.senhas = response;
        this.senhas.forEach(element => {
          this.getPessoa(element.idPessoa);
          element.pessoa = this.pessoa;
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
    this.pessoaService.gerarPessoa(senha.value).subscribe((response: Pessoa) => {
      this.idPessoa = response.id;
      this.senhaService.gerarSenha(senha.value, this.idPessoa).subscribe(
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
      });
  }

  private getPessoa(id: number): void {
    this.pessoaService.getPessoa(id).subscribe(
      (response: Pessoa) => { this.pessoa = response; },
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
