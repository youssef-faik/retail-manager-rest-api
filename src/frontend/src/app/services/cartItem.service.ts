import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subject} from 'rxjs';
import {CartItem, IUpdateMessage} from "./cartItem.model";

const SERVER_URL = 'http://localhost:8080/api/v1/cart';
// Declare SockJS and Stomp
// @ts-ignore
declare var SockJS;
// @ts-ignore
declare var Stomp;

@Injectable({
  providedIn: 'root',
})
export class CartItemService {
  public updateEvents: Subject<IUpdateMessage>;
  public cartId = '';
  private resourceUrl = `http://localhost:8080/api/v1/cart-item`;
  private stompClient;

  constructor(private http: HttpClient) {
    this.updateEvents = new Subject<IUpdateMessage>();

    const ws = new SockJS(SERVER_URL);

    this.stompClient = Stomp.over(ws);
    this.stompClient.connect({}, () => {
      // @ts-ignore
      this.stompClient.subscribe('/cart/' + this.cartId, (message) => {
        if (message.body) {
          this.updateEvents.next(JSON.parse(message.body));
        }
      });
    });

  }

  getCartItem(id: any): Observable<CartItem> {
    return this.http.get<CartItem>(`${this.resourceUrl}/${id}`);
  }

}
