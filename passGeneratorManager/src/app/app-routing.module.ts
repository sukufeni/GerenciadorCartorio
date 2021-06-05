import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PessoaComponent} from "./pessoa/pessoa.component";
import { ProtocoloComponent } from './protocolo/protocolo.component';

const routes: Routes = [
  {
    path: 'pessoa',
    component:PessoaComponent
  },
  {
    path:'protocolo',
    component:ProtocoloComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes,{
    enableTracing:true
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
