import { Component, OnInit, inject } from '@angular/core';
import {Observable, tap} from 'rxjs';
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

  itemCount!: number

  ngOnInit(): void {
    this.cartSvc.getItemsCount.pipe(
      tap(result => {
        this.itemCount = result
      })
    ).subscribe()
  }

  checkout(): void {
    if(this.itemCount == 0){
      alert("Your cart is empty")
    }
    else{
      this.router.navigate([ '/checkout' ])
    }
    
  }
}
