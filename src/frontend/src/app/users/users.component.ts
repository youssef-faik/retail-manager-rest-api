import {Component, OnInit} from '@angular/core';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AuthenticationResponse, UserDto, UserService} from "../../../libs/openapi/out";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  users: UserDto[];
  recordIdToDelete?: number;

  constructor(private modalService: NgbModal, private usersService: UserService) {
  }

  ngOnInit(): void {
    // @ts-ignore
    this.usersService.configuration.credentials = {'Bearer_Authentication': this.getUserDtoFromLocalStorage()?.token};
    this.loadUsers();
  }

  openConfirmationModal(content: any, recordId?: number) {
    this.recordIdToDelete = recordId;
    this.modalService.open(content, {centered: true}).result.then(
      (result) => {
        if (result === 'confirm') {
          this.usersService.deleteUser(recordId || 0, 'body', false, {httpHeaderAccept: 'application/json'})
            .subscribe(
              data => {
                this.loadUsers();
              },
              error => {
                console.log(error);
              }
            )
        }
      },
      (reason) => {
        // Handle modal dismissal (e.g., cancel button clicked)
        console.log(`Modal dismissed with reason: ${reason}`);
      }
    );
  }

  getUserDtoFromLocalStorage(): AuthenticationResponse | undefined {
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

  private loadUsers() {
    this.usersService.getAllUsers('body', false, {httpHeaderAccept: 'application/json'}).subscribe(
      (data) => {
        console.log(data)
        this.users = data;
      }, error => {
        console.log(error)
      })
  }

}
