import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthenticationResponse} from "../../../libs/openapi/out";

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor {
  constructor() {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // @ts-ignore
    if (this.getUserFromLocalStorage() == undefined) {
      return next.handle(req);
    }

    const authRequest = req.clone(
      {
        headers: new HttpHeaders({
          Authorization: 'Bearer ' + this.getUserFromLocalStorage()?.token
        })
      }
    );
    return next.handle(authRequest);

  }

  getUserFromLocalStorage(): AuthenticationResponse | undefined {
    try {
      const item = localStorage.getItem('authenticationResponse');
      if (!item) {
        return undefined;
      }
      return JSON.parse(item);
    } catch (error) {
      console.error(error);
      return undefined;
    }
  }
}
