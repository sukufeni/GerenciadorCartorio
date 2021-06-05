import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Cartorio } from './Cartorio';
import { CartorioService } from './cartorio.service';

@Component({
  selector: 'app-cartorio',
  templateUrl: './cartorio.component.html',
  styleUrls: ['./cartorio.component.css']
})
export class CartorioComponent implements OnInit {
  public cartorios: Cartorio[] = [];
  constructor(private cartorioService: CartorioService) { }

  ngOnInit() {
    this.getcartorios();
  }
  private getcartorios(): void {
    this.cartorioService.getCartorios().subscribe(
      (response: Cartorio[]) => { this.cartorios = response; },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }
}
