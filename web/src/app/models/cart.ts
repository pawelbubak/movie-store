import {CartItem} from './cart-item';

export class Cart {
  constructor(
    public items: Array<CartItem>,
    public totalPrice: number) {
  }
}
