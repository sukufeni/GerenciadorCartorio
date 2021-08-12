import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PessoaComponent } from './pessoa/pessoa.component';
import { FormsModule } from '@angular/forms';
import { PessoaService } from "./pessoa/pessoa.service";
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { ProtocoloComponent } from './protocolo/protocolo.component';
import { CartorioComponent } from './cartorio/cartorio.component';
import { SenhaComponent } from './senha/senha.component';
import { DisplaySenhaComponent } from './display-senha/display-senha.component';
import { LoginComponent } from './login/login.component';
// import { HttpInterceptorService } from './http-interceptor-request.service';
import { WrongRouteComponent } from './wrong-route/wrong-route.component';

@NgModule({
  declarations: [
    AppComponent,
    PessoaComponent,
    ProtocoloComponent,
    CartorioComponent,
    SenhaComponent,
    DisplaySenhaComponent,
    LoginComponent,
    WrongRouteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  // providers: [
  //   {
  //     provide:HTTP_INTERCEPTORS,
  //     useClass:HttpInterceptorService,
  //     multi:true
  //   }
  // ]
  bootstrap: [AppComponent]
})
export class AppModule { }
