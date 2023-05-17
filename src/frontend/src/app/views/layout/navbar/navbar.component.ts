import {Component, Inject, OnInit, Renderer2} from '@angular/core';
import {DOCUMENT} from '@angular/common';
import {Router} from '@angular/router';
import {UserDto} from "../../../../../libs/openapi/out";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  userDto: UserDto | undefined;

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private renderer: Renderer2,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.userDto = this.getUserDtoFromLocalStorage();
  }

  /**
   * Sidebar toggle on hamburger button click
   */
  toggleSidebar(e: Event) {
    e.preventDefault();
    this.document.body.classList.toggle('sidebar-open');
  }

  /**
   * Logout
   */
  onLogout(e: Event) {
    e.preventDefault();
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('authenticationResponse');

    if (!localStorage.getItem('isLoggedIn')) {
      this.router.navigate(['/auth/login']);
    }
  }

  private getUserDtoFromLocalStorage(): UserDto | undefined {
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
