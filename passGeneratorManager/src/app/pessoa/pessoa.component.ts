import { Component, OnInit } from '@angular/core';
import {Pessoa} from './Pessoa';
import {PessoaService} from './pessoa.service';
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-pessoa',
  templateUrl: './pessoa.component.html',
  styleUrls: ['./pessoa.component.css']
})
export class PessoaComponent implements OnInit {
  public pessoas: Pessoa[] = [];

  constructor(private pessoaService: PessoaService) {
  }
  ngOnInit(){
    this.getPessoas();
  }

  public getPessoas():void{
    this.pessoaService.getPessoas().subscribe(
      (response:Pessoa[])=>{this.pessoas=response;},
      (error: HttpErrorResponse)=>{
        alert(error.message)
      }
    );
  }
}
