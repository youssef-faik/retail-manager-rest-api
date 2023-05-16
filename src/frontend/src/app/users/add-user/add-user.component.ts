import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationResponse, UserCreateDto, UserDto, UserService} from "../../../../libs/openapi/out";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {
  roles = Object.values(UserDto.RoleEnum);
  addUserFrom: FormGroup;

  constructor(
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.addUserFrom = new FormGroup({
      'firstName': new FormControl(null, Validators.required),
      'lastName': new FormControl(null, Validators.required),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'password': new FormControl(null, [Validators.required, Validators.minLength(8)]),
      'role': new FormControl(this.roles[2], Validators.required)
    });
  }

  onSubmit() {
    if (!this.addUserFrom.valid) {
      return
    }

    console.log(this.addUserFrom.value);
    let userCreateDto: // @ts-ignore
      UserCreateDto = this.addUserFrom.value;
    // @ts-ignore
    this.userService.configuration.credentials = {'Bearer_Authentication': this.getUserDtoFromLocalStorage()?.token};
    this.userService.createUser(userCreateDto)
      .subscribe(
        data => {
          this.router.navigate(['../'], {
            relativeTo: this.route,
            replaceUrl: true,
          });
        }
        , error => {
          console.log(error)
        })
  }


  private getUserDtoFromLocalStorage(): AuthenticationResponse | undefined {
    try {
      const lsValue = localStorage.getItem('authenticationResponse');
      if (!lsValue) {
        return undefined;
      }

      return JSON.parse(lsValue);
    } catch (error) {
      console.error(error);
      return undefined;
    }
  }
}
