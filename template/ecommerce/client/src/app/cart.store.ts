
// TODO Task 2

import { Injectable } from "@angular/core";
import { firstValueFrom, of, Subject } from "rxjs";
import { Cart, LineItem, Product } from "./models";

// Use the following class to implement your store
@Injectable()
export class CartStore {

    cart: Cart = {
        lineItems: []
    }

    total:number =0

    count:number = 0
    count$ = new Subject<number>()

    addItem(item: LineItem) {

        const user = this.cart.lineItems.findIndex((x) => x.prodId === item.prodId)

        if (user >= 0) {
            this.update(item,user)
        } else {
            this.cart.lineItems.push(item)
        }
        this.getCount()
    }

    getTotal():number{
        this.total = 0 
        this.cart.lineItems.forEach((item) => this.total = this.total + item.price)
        return this.total
    }
    
    reset(){
        this.cart  = {
            lineItems: []
        }
        this.getCount()
    
    }

    private update(item:LineItem, index:number){
        this.cart.lineItems[index].quantity = item.quantity + this.cart.lineItems[index].quantity
        this.cart.lineItems[index].price = item.price * this.cart.lineItems[index].quantity
    }

    private getCount(){
        this.count = 0
        this.cart.lineItems.forEach((item) => this.count = this.count + item.quantity)
        this.count$.next(this.count)
    }



}
