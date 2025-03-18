
// TODO Task 2
// Use the following class to implement your store

import { ComponentStore } from "@ngrx/component-store";
import { Cart, LineItem } from "./models";
import { Injectable } from "@angular/core";

const INIT:Cart = {
    lineItems:[]
}

@Injectable()
export class CartStore extends ComponentStore<Cart>{
    constructor(){
        super(INIT)
    }

    readonly addItem = this.updater<LineItem>(
        (cart:Cart, newItem:LineItem) =>{
            const updateCart:LineItem[] = cart.lineItems.map(lineitem =>{
                if(lineitem.name == newItem.name){
                    return {...lineitem, quantity:lineitem.quantity + newItem.quantity, price:lineitem.price + newItem.price}
                }
                console.info(lineitem)
                return lineitem;
            })

            if (!cart.lineItems.some(item => item.name === newItem.name)) {
                updateCart.push(newItem);
            }

            return {
                ...cart,
                lineItems:updateCart
            } as Cart


        }
    )




    readonly getItems = this.select<LineItem[]>(
        
        (cart:Cart) => cart.lineItems
    )

    readonly getItemsCount = this.select<number>(
        (cart:Cart) => {
            let count:number = 0
            if(cart.lineItems.length >0){
                cart.lineItems.map(v => count = count + v.quantity)
            }

            return count
        
        }
    )

    readonly getTotalSpent = this.select<number>(
        (cart:Cart) => {
            let total:number = 0
            if(cart.lineItems.length >0){
                cart.lineItems.map(v => total = total + v.price)
            }

            return total
        
        }
    )

}
