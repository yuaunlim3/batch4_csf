import { Component, OnInit, inject } from '@angular/core';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import { CartStore } from './cart.store';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  // NOTE: you are free to modify this component

  private router = inject(Router)

  private cartSvc = inject(CartStore)

  itemCount:number = 0

  ngOnInit(): void {
    this.cartSvc.count$.subscribe(result => this.itemCount = result)
  }

  checkout(): void {
    this.router.navigate([ '/checkout' ])
  }
}
