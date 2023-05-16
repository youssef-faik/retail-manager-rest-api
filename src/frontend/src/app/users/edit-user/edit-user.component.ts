import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationResponse, UserDto, UserService, UserUpdateDto} from "../../../../libs/openapi/out";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.scss']
})
export class EditUserComponent implements OnInit {
  editUserFrom!: FormGroup;
  id!: number;
  roles = Object.values(UserDto.RoleEnum);
  userToUpdate: UserDto;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ) {
  }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.editUserFrom = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      role: new FormControl(this.roles[0], Validators.required)
    });

    // @ts-ignore
    this.userService.configuration.credentials = {'Bearer_Authentication': this.getUserDtoFromLocalStorage()?.token};
    this.userService.getUser(this.id, 'body', false, {httpHeaderAccept: 'application/json'})
      .subscribe(
        (data) => {
          this.userToUpdate = data;
          this.editUserFrom.controls['firstName'].setValue(this.userToUpdate.firstName);
          this.editUserFrom.controls['lastName'].setValue(this.userToUpdate.lastName);
          this.editUserFrom.controls['email'].setValue(this.userToUpdate.email);
          this.editUserFrom.controls['role'].setValue(this.userToUpdate.role);
          console.log(this.userToUpdate)
        }, error => {
          console.log(error)
        });
  }

  onSubmit() {
    if (!this.editUserFrom.valid) {
      return
    }

    console.log(this.editUserFrom.value);
    let userUpdateDto: // @ts-ignore
      UserUpdateDto = this.editUserFrom.value;
    // @ts-ignore
    this.userService.configuration.credentials = {'Bearer_Authentication': this.getUserDtoFromLocalStorage()?.token};
    this.userService.updateUser(this.id, userUpdateDto, 'body', false, {httpHeaderAccept: 'application/json'})
      .subscribe(
        data => {
          console.log(this.route)

          this.router.navigate(['../..'], {
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
