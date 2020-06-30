import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenService} from '../services/token.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  private readonly AUTH_OPTION: string = 'Authorization';
  private readonly AUTH_PREFIX: string = 'Bearer ';

  constructor(private tokenService: TokenService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.tokenService.getToken();
    let tokenReq = req;
    if (token != null) {
      tokenReq = req.clone({
        headers: req.headers.set(this.AUTH_OPTION, this.AUTH_PREFIX + token)
      });
    }
    return next.handle(tokenReq);
  }
}
