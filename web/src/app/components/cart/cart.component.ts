import {Component, OnInit} from '@angular/core';
import {CartService} from '../../services/cart.service';
import {TokenService} from '../../services/token.service';
import {Observable} from 'rxjs';
import {Cart} from '../../models/cart';
import {CartItem} from '../../models/cart-item';
import {map, switchMap} from 'rxjs/operators';
import {ConversionService} from '../../services/conversion.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  cartContent$: Observable<Cart>;
  convertedPrice$: Observable<number>;

  constructor(private cartService: CartService,
              private conversionService: ConversionService,
              private tokenService: TokenService) {
  }

  ngOnInit(): void {
    if (this.isAuthenticated()) {
      this.fetchCartContent();
    }
  }

  isAuthenticated(): boolean {
    return this.tokenService.isAuthenticated();
  }

  addToCart(cartItem: CartItem): void {
    cartItem.quantity += 1;
    this.cartService.updateCart(cartItem).subscribe(() => {
      location.reload();
    });
  }

  removeFromCart(cartItem: CartItem): void {
    if (cartItem.quantity === 1) {
      this.cartService.deleteFromCart(cartItem?.movie?.id).subscribe(() => {
        location.reload();
      });
    } else {
      cartItem.quantity -= 1;
      this.cartService.updateCart(cartItem).subscribe(() => {
        location.reload();
      });
    }
  }

  deleteFromCart(cartItem: CartItem) {
    this.cartService.deleteFromCart(cartItem?.movie?.id).subscribe(() => {
      location.reload();
    });
  }

  private fetchCartContent(): void {
    this.cartContent$ = this.cartService.fetchCartContent();
    this.convertedPrice$ = this.cartContent$
      .pipe(switchMap(value => this.conversionService.convert(value.totalPrice)
        .pipe(map(value1 => value1.convertedValue))));
  }
}
