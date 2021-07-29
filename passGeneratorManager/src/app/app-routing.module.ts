import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartorioComponent } from './cartorio/cartorio.component';
import { DisplaySenhaComponent } from './display-senha/display-senha.component';
import { LoginComponent } from './login/login.component';
import { PessoaComponent } from "./pessoa/pessoa.component";
import { ProtocoloComponent } from './protocolo/protocolo.component';
import { SenhaComponent } from './senha/senha.component';
import { WrongRouteComponent } from './wrong-route/wrong-route.component';

const routes: Routes = [
  {
    path: 'pessoa',
    component: PessoaComponent
  },
  {
    path: 'login',
    component: LoginComponent
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
  },
  {
    path: "",
    redirectTo: "login",
    pathMatch: "full"
  },
  {
    path: "error",
    component: WrongRouteComponent
  },
  {
    path: "",
    component: LoginComponent
  },
  //Wild Card Route
  { path: '**', pathMatch: 'full', component: WrongRouteComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    enableTracing: true
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
