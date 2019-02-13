export interface Order {
  name: string;
  email: string;
  phone: string;
  total: number;
  confirmedAt: Date;
  id: string;
}


export interface OrderItems {
  quantity: number;
  price: number;
  description: string;
  total?: number;
}


export interface Page {
  size: number;
  totalElements: number;
  totalPages: number;
  number: number;
}

export interface OrderResponse {
  list: Order[];
  page: Page;
}
