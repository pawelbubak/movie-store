import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {UserCredentials} from '../models/user-credentials';
import {Observable} from 'rxjs';
import {Token} from '../models/token';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly host: string;
  private readonly httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) {
    this.host = environment.host;
  }

  login(userCredentials: UserCredentials): Observable<Token> {
    const url = `${this.host}/api/auth/signIn`;
    return this.http.post<Token>(url, {
      username: userCredentials.username,
      password: userCredentials.password
    }, this.httpOptions);
  }

  register(userCredentials: UserCredentials): Observable<any> {
    const url = `${this.host}/api/auth/signUp`;
    return this.http.post(url, {
      username: userCredentials.username,
      email: userCredentials.email,
      password: userCredentials.password
    }, this.httpOptions);
  }
}
