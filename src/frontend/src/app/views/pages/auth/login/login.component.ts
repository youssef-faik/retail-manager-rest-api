import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationRequest, AuthenticationService} from "../../../../../../libs/openapi/out";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  authenticationRequest: AuthenticationRequest = {email: "yusef@mail.com", password: "secret-password"};
  returnUrl: any;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authenticationService: AuthenticationService
  ) {
  }

  ngOnInit(): void {
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onLoggedIn(e: Event) {
    e.preventDefault();
    console.log(this.authenticationRequest)
    // @ts-ignore
    this.authenticationService.authenticate(this.authenticationRequest, 'body', false, {httpHeaderAccept: 'application/json'}).subscribe(
      (data) => {
        console.log(data)
        localStorage.setItem('authenticationResponse', JSON.stringify(data))
        localStorage.setItem('isLoggedIn', 'true');
        this.router.navigate([this.returnUrl]);
      }, error => {
        console.log(error)
        this.router.navigate(['/auth/login'])
      }
    );

  }

}
