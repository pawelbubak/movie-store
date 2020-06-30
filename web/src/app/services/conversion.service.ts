import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {CurrencyExchange} from '../models/currency-exchange';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConversionService {
  private readonly host: string;

  constructor(private http: HttpClient) {
    this.host = environment.host;
  }

  convert(totalPrice: number): Observable<CurrencyExchange> {
    return this.http.get<CurrencyExchange>(`${this.host}/api/conversion?value=${totalPrice}&fromCurrencyCode=PLN&toCurrencyCode=EUR`);
  }
}
