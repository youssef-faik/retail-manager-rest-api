export interface ICartItem {
  id: any;
  productId: any;
  barcode: any;
}

export interface IUpdateMessage {
  addedCartItem: ICartItem;
}

export class CartItem implements ICartItem {
  constructor(public id: any, public productId: any, public barcode: any) {
    this.id = id;
    this.productId = productId;
    this.barcode = barcode;
  }
}
