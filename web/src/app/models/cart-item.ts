import {Movie} from './movie';

export class CartItem {
  constructor(
    public movie: Movie,
    public quantity: number = 1,
    public totalPrice: number = 0) {
  }
}
