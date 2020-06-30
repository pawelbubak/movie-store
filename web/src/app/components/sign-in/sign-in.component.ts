import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserCredentials} from '../../models/user-credentials';
import {AuthService} from '../../services/auth.service';
import {TokenService} from '../../services/token.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {
  formValid = true;
  signInForm: FormGroup = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(20)]],
    password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(20)]]
  });

  constructor(private authService: AuthService,
              private fb: FormBuilder,
              private router: Router,
              private tokenService: TokenService) {
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const credentials: UserCredentials = new UserCredentials(
      this.signInForm.get('username')?.value,
      this.signInForm.get('password')?.value);

    this.authService.login(credentials).subscribe(value => {
      this.formValid = true;
      this.tokenService.saveToken(value.accessToken);
      this.tokenService.saveUser(value);
      this.router.navigate(['/movies']);
    }, error => {
      this.formValid = false;
      console.error(error);
    });
  }
}
