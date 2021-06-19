import { Component, OnInit } from '@angular/core';
import {Pessoa} from './Pessoa';
import {PessoaService} from './pessoa.service';
import {HttpErrorResponse} from "@angular/common/http";
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-pessoa',
  templateUrl: './pessoa.component.html',
  styleUrls: ['./pessoa.component.css']
})
export class PessoaComponent implements OnInit {
  public pessoas: Pessoa[] = [];
  public editPessoa: Pessoa | null = null;
  public deletePessoa: Pessoa | null = null;

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

  public onAddPessoa(addForm:NgForm):void{
    document.getElementById('add-pessoa-form')?.click();
    this.pessoaService.gerarPessoa(addForm.value).subscribe(
      (response:Pessoa)=>{
        window.location.reload();
      },
      (error:HttpErrorResponse)=>{
        alert(error.message);
      }
    )
  }


  public onOpenModal(pessoa: Pessoa | null, mode: string): void{
    const container = document.getElementById('main-container');
    const btn = document.createElement('button');
    btn.type = 'button';
    btn.style.display= 'none';
    btn.setAttribute('data-toggle','modal');
    if(mode==='add'){
      btn.setAttribute('data-target','#addPessoaModal');
    }
    if(mode==='edit'){
      btn.setAttribute('data-target','#editPessoaModal');
      this.editPessoa = pessoa;
    }
    //Tratar o proxima senha aqui?
    if(mode==='delete'){
      btn.setAttribute('data-target','#deleteSenhaModal');
      this.deletePessoa = pessoa;
    }
    container?.appendChild(btn);
    btn.click();
  }
}
