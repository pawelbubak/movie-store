import {NgModule} from '@angular/core';
import {MovieListComponent} from './components/movie-list/movie-list.component';
import {RouterModule, Routes} from '@angular/router';
import {SignInComponent} from './components/sign-in/sign-in.component';
import {SignUpComponent} from './components/sign-up/sign-up.component';
import {CartComponent} from './components/cart/cart.component';
import {MovieComponent} from './components/movie/movie.component';
import {AuthGuardService} from './services/auth-guard.service';
import {LoginGuardService} from './services/login-guard.service';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'movies'},
  {path: 'cart', component: CartComponent, canActivate: [AuthGuardService]},
  {path: 'movie/:id', component: MovieComponent},
  {path: 'movies', component: MovieListComponent},
  {path: 'signIn', component: SignInComponent, canActivate: [LoginGuardService]},
  {path: 'signUp', component: SignUpComponent, canActivate: [LoginGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
