import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Cart} from '../models/cart';
import {environment} from '../../environments/environment';
import {CartItem} from '../models/cart-item';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private readonly host: string;
  private readonly httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) {
    this.host = environment.host;
  }

  fetchCartContent(): Observable<Cart> {
    return this.http.get<Cart>(`${this.host}/api/cart`);
  }

  addToCart(cartItem: CartItem): Observable<CartItem> {
    return this.http.post<CartItem>(`${this.host}/api/cart`, cartItem, this.httpOptions);
  }

  updateCart(cartItem: CartItem) {
    return this.http.put<CartItem>(`${this.host}/api/cart/${cartItem?.movie?.id}`, cartItem, this.httpOptions);
  }

  deleteFromCart(id: number): Observable<void> {
    return this.http.delete<void>(`${this.host}/api/cart/${id}`);
  }
}
