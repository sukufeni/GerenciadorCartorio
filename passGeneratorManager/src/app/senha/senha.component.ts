import { HttpErrorResponse } from '@angular/common/http';
import { NONE_TYPE } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
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
  constructor(private senhaService: SenhaService) { }

  ngOnInit() {
    this.getSenhas();
  }
  public getSenhas(): void {
    this.senhaService.getSenhas().subscribe(
      (response:Senha[])=>{this.senhas=response;},
      (error: HttpErrorResponse)=>{
        alert(error.message)
      }
    );
  }

  public proximaSenha(): void {
    this.senhaService.proximaSenha().subscribe(
      (response:Senha)=>{this.senha=response;},
      (error: HttpErrorResponse)=>{
        alert(error.message)
      }
    );
  }

  public gerarSenha(senha:Senha): void {
    this.senhaService.gerarSenha(senha).subscribe(
      (response:Senha)=>{this.senha=response;},
      (error: HttpErrorResponse)=>{
        alert(error.message)
      }
    );
  }
}
