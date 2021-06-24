import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartorioComponent } from './cartorio/cartorio.component';
import { DisplaySenhaComponent } from './display-senha/display-senha.component';
import { PessoaComponent } from "./pessoa/pessoa.component";
import { ProtocoloComponent } from './protocolo/protocolo.component';
import { SenhaComponent } from './senha/senha.component';

const routes: Routes = [
  {
    path: 'pessoa',
    component: PessoaComponent
  },
  {
    path: 'protocolo',
    component: ProtocoloComponent
  },
  {
    path: "cartorio",
    component: CartorioComponent
  },
  {
    path: 'senha',
    component: SenhaComponent
  },
  {
    path: 'display-senha',
    component: DisplaySenhaComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    enableTracing: true
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
