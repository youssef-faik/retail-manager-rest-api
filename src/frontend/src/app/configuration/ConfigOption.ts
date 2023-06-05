export interface ConfigOption {
  key?: ConfigOption.KeyEnum;
  value?: string;
}

export namespace ConfigOption {
  export type KeyEnum = 'NEXT_INVOICE_NUMBER';
  export const KeyEnum = {
    InvoiceNumber: 'LAST_INVOICE_NUMBER' as KeyEnum
  };
}
