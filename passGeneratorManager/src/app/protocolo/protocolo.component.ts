import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Protocolo } from './Protocolo';
import { ProtocoloService } from './protocolo.service';

@Component({
  selector: 'app-protocolo',
  templateUrl: './protocolo.component.html',
  styleUrls: ['./protocolo.component.css']
})
export class ProtocoloComponent implements OnInit {
  public protocolos: Protocolo[]=[];
  constructor(private protocoloService: ProtocoloService) { }

  ngOnInit() {
    this.getProtocolos();
  }

  public getProtocolos():void{
    this.protocoloService.getProtocolos().subscribe(
      (response:Protocolo[])=>{this.protocolos=response;},
      (error: HttpErrorResponse)=>{
        alert(error.message)
      }
    );
  }
}
