import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ChangePasswordRequest, UserService} from "../../../libs/openapi/out";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {
  changePasswordFrom: FormGroup;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private userService: UserService
  ) {
  }

  ngOnInit(): void {
    this.changePasswordFrom = new FormGroup({
      'oldPassword': new FormControl(null, [Validators.required, Validators.minLength(8)]),
      'newPassword': new FormControl(null, [Validators.required, Validators.minLength(8)]),
      'confirmNewPassword': new FormControl(null, [Validators.required, Validators.minLength(8)])
    });
  }

  onSubmit() {
    if (!this.changePasswordFrom.valid) {
      return
    }

    let changePasswordRequest: ChangePasswordRequest = {
      oldPassword: this.changePasswordFrom.controls['oldPassword'].value,
      newPassword: this.changePasswordFrom.controls['newPassword'].value
    };

    this.userService.changePassword(
      changePasswordRequest,
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data) => {
        this.router.navigate(['/']);
      }, error => {
        console.log(error);
      }
    );

  }

}
