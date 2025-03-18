import { Component, inject, OnInit } from '@angular/core';
import { CartStore } from '../cart.store';
import { Cart, Order } from '../models';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-confirm-checkout',
  templateUrl: './confirm-checkout.component.html',
  styleUrl: './confirm-checkout.component.css'
})
export class ConfirmCheckoutComponent implements OnInit{

  // TODO Task 3

  private cartSvc = inject(CartStore)
  private orderSvc = inject(ProductService)
  private router = inject(Router)

  private fb =inject(FormBuilder)
  form!:FormGroup

  cart: Cart = {
    lineItems: []
  }

  total:number = 0

  ngOnInit(): void {
    this.cart.lineItems = this.cartSvc.cart.lineItems
    this.total = this.cartSvc.getTotal()
    this.form = this.createForm()
    if(this.cart.lineItems.length == 0){
      alert('Your Cart is empty')
    }
  }

  submit(){
    const value:Order = this.form.value
    value.cart = this.cart
    this.orderSvc.checkout(value).then(result => {

      if(result.orderId){
        alert(`Your order id is ${result.orderId}`)
      }
      if(result.message){
        alert(`Error: ${result.message}`)
      }
      this.cartSvc.reset()

      this.router.navigate([''])      
    })
  }

  isValid():boolean{
    return this.form.invalid || this.cart.lineItems.length == 0
  }



  private createForm():FormGroup{
    return this.fb.group({
      name: this.fb.control<string>('',Validators.required),
      address:this.fb.control<string>('',[Validators.required,Validators.minLength(3)]),
      priority: this.fb.control<boolean>(false),
      comments: this.fb.control<string>(''),
      cart: this.fb.control<Cart>({lineItems:[]})
    }
    )
  }

}
