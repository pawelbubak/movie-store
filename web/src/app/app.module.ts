import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MovieListComponent} from './components/movie-list/movie-list.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AngularMaterialModule} from './modules/material.module';
import {MovieService} from './services/movie.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {registerLocaleData} from '@angular/common';
import localePl from '@angular/common/locales/pl';
import {SignInComponent} from './components/sign-in/sign-in.component';
import {SignUpComponent} from './components/sign-up/sign-up.component';
import {AuthInterceptor} from './interceptors/auth.interceptor';
import {JwtModule} from '@auth0/angular-jwt';
import { CartComponent } from './components/cart/cart.component';
import { MovieComponent } from './components/movie/movie.component';

registerLocaleData(localePl, 'pl');

@NgModule({
  declarations: [
    AppComponent,
    MovieListComponent,
    SignInComponent,
    SignUpComponent,
    CartComponent,
    MovieComponent
  ],
  imports: [
    AngularMaterialModule,
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem('auth-token');
        }
      }
    }),
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    MovieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
