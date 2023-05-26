import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationRequest, AuthentificationService} from "../../../../../../libs/openapi/out";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  returnUrl: any;
  loginFrom: FormGroup;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authenticationService: AuthentificationService
  ) {
  }

  ngOnInit(): void {
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.loginFrom = new FormGroup({
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'password': new FormControl(null, [Validators.required, Validators.minLength(8)])
    });
  }


  onSubmit() {
    if (!this.loginFrom.valid) {
      return
    }

    let authenticationRequest: AuthenticationRequest = this.loginFrom.value;
    // @ts-ignore
    this.authenticationService.authenticate(authenticationRequest, 'body', false, {httpHeaderAccept: 'application/json'}).subscribe(
      (data) => {
        console.log(data);
        localStorage.setItem('authenticationResponse', JSON.stringify(data))
        localStorage.setItem('isLoggedIn', 'true');
        this.router.navigate([this.returnUrl]);
      }, error => {
        console.log(error);
        this.router.navigate(['/auth/login']);
      }
    );

  }

}
