export interface ICartItem {
  id: any;
  productId: any;
}

export interface IUpdateMessage {
  addedCartItem: ICartItem;
}

export class CartItem implements ICartItem {
  constructor(public id: any, public productId: any) {
    this.id = id;
    this.productId = productId;
  }
}
