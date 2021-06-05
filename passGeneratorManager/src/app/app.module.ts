import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PessoaComponent } from './pessoa/pessoa.component';
import { PessoaService } from "./pessoa/pessoa.service";
import { HttpClient, HttpClientModule } from "@angular/common/http";
import { ProtocoloComponent } from './protocolo/protocolo.component';
import { CartorioComponent } from './cartorio/cartorio.component';
import { SenhaComponent } from './senha/senha.component';

@NgModule({
  declarations: [
    AppComponent,
    PessoaComponent,
    ProtocoloComponent,
    CartorioComponent,
    SenhaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
