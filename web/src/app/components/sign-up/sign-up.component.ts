import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserCredentials} from '../../models/user-credentials';
import {AuthService} from '../../services/auth.service';
import {TokenService} from '../../services/token.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {
  formValid = true;
  formValidationMessage: string;
  signUpForm: FormGroup = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(20)]],
    email: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(50), Validators.email]],
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
      this.signUpForm.get('username')?.value,
      this.signUpForm.get('password')?.value,
      this.signUpForm.get('email')?.value);

    this.authService.register(credentials).subscribe(() => {
      this.formValid = true;
      this.authService.login(credentials).subscribe(value => {
        this.tokenService.saveToken(value.accessToken);
        this.tokenService.saveUser(value);
        this.router.navigate(['/movies']);
      }, loginError => {
        console.error(loginError);
        this.router.navigate(['/signIn']);
      });
    }, error => {
      this.formValid = false;
      this.formValidationMessage = error?.error?.message;
      console.error(error);
    });
  }
}
