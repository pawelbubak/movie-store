import {Component, OnInit} from '@angular/core';
import {CartService} from './services/cart.service';
import {Observable} from 'rxjs';
import {Cart} from './models/cart';
import {map} from 'rxjs/operators';
import {TokenService} from './services/token.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'movie-store';

  cartItemsQuantity$: Observable<number>;
  cartContent$: Observable<Cart>;

  constructor(private cartService: CartService, private tokenService: TokenService) {
  }

  ngOnInit(): void {
    if (this.isAuthenticated()) {
      this.fetchCartContent();
    }
  }

  getCurrentUser(): string {
    return this.tokenService.getUser()?.username;
  }

  isAuthenticated(): boolean {
    return this.tokenService.isAuthenticated();
  }

  signOut() {
    this.tokenService.signOut();
    location.reload();
  }

  private fetchCartContent(): void {
    this.cartContent$ = this.cartService.fetchCartContent();
    this.cartItemsQuantity$ = this.cartContent$.pipe(map(value => {
      return value?.items?.reduce((sum, cartItem) => sum + cartItem.quantity, 0) || 0;
    }));
  }
}
