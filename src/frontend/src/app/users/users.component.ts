import {Component, OnInit, ViewChild} from '@angular/core';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {UserDto, UtilisateurService} from "../../../libs/openapi/out";
import {ColumnMode, DatatableComponent} from "@swimlane/ngx-datatable";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  users: UserDto[];
  temp: UserDto[];
  recordIdToDelete?: number;
  loadingIndicator = true;
  reorderable = true;
  ColumnMode = ColumnMode;
  @ViewChild(DatatableComponent) table: DatatableComponent;

  constructor(
    private modalService: NgbModal,
    private usersService: UtilisateurService
  ) {
  }

  ngOnInit(): void {
    this.loadUsers();
  }

  openConfirmationModal(content: any, recordId?: number) {
    this.recordIdToDelete = recordId;
    this.modalService.open(content, {centered: true}).result.then(
      result => {
        if (result === 'confirm') {
          this.usersService.deleteUser(
            recordId || 0,
            'body',
            false,
            {httpHeaderAccept: 'application/json'}
          ).subscribe(
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

  updateFilter(event: KeyboardEvent) {
    // @ts-ignore
    const val = event.target.value.toLowerCase();

    // filter our data
    const temp = this.temp.filter(function (user) {
      // @ts-ignore
      return user.lastName.toLowerCase().indexOf(val) !== -1 || !val;
    });

    // update the rows
    this.users = temp;
    // Whenever the filter changes, always go back to the first page
    this.table.offset = 0;
  }

  private loadUsers() {
    this.usersService.getAllUsers(
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data) => {
        // cache our list
        this.temp = [...data];

        // push our inital complete list
        this.users = data;

        setTimeout(() => {
          this.loadingIndicator = false;
        }, 1500);
      }, error => {
        console.log(error)
      }
    );
  }

}
