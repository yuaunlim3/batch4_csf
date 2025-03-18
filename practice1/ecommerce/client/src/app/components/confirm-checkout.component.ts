import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { LineItem, Order } from '../models';
import { Observable, tap } from 'rxjs';
import { CartStore } from '../cart.store';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-confirm-checkout',
  templateUrl: './confirm-checkout.component.html',
  styleUrl: './confirm-checkout.component.css'
})
export class ConfirmCheckoutComponent implements OnInit{

  // TODO Task 3

  protected lineItems$!:Observable<LineItem[]>
  protected lineItems:LineItem[] = []
  private cartSvc = inject(CartStore)
  private fb = inject(FormBuilder)
  protected form!:FormGroup
  protected total:number = 0
  private productSvc = inject(ProductService)




  ngOnInit(): void {
    this.lineItems$ = this.cartSvc.getItems
    this.lineItems$.pipe(
      tap(result => this.lineItems = result)
    ).subscribe()
    this.form = this.createForm()

    this.cartSvc.getTotalSpent.pipe(
      tap(result => this.total = result)
    ).subscribe()
  }

  createForm():FormGroup{
    return this.fb.group({
      name:this.fb.control<string>("",Validators.required),
      address:this.fb.control<string>("",[Validators.required,Validators.minLength(3)]),
      priority:this.fb.control<boolean>(false),
      comments:this.fb.control<string>("")

    })
  }

  isValid():boolean{
    return this.form.valid
  }

  submit(){
    const value = this.form.value
    const order:Order = {
      ...value,
      cart:this.lineItems
    }
    this.productSvc.checkout(order).then(result =>{
      alert(`Your order id is ${result.orderId}`)
    })
  }


}
