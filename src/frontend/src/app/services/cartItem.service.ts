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
  public oldCartId = '';
  private resourceUrl = `http://localhost:8080/api/v1/cart-item`;
  private stompClient: any;
  private ws: any;

  constructor(private http: HttpClient) {
    this.updateEvents = new Subject<IUpdateMessage>();

    this.ws = new SockJS(SERVER_URL);
    this.stompClient = Stomp.over(this.ws);
  }

  resetStompClient() {
    console.log(this.stompClient)
    if (this.stompClient.connected && this.oldCartId != this.cartId) {
      this.stompClient.unsubscribe('/cart/' + this.oldCartId);
      this.oldCartId = this.cartId;

      // @ts-ignore
      this.stompClient.subscribe('/cart/' + this.cartId, (message) => {
        if (message.body) {
          this.updateEvents.next(JSON.parse(message.body));
        }
      });
      return;
    }

    // first connection
    this.stompClient.connect({}, () => {
      this.oldCartId = this.cartId;

      // @ts-ignore
      this.stompClient.subscribe('/cart/' + this.cartId, (message) => {
        if (message.body) {
          this.updateEvents.next(JSON.parse(message.body));
        }
      });
    });
    console.log(this.stompClient)

  }

  getCartItem(id: any): Observable<CartItem> {
    return this.http.get<CartItem>(`${this.resourceUrl}/${id}`);
  }

}
