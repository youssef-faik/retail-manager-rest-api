import {MenuItem} from './menu.model';

export const MENU: MenuItem[] = [
  {
    label: 'Main',
    isTitle: true
  },
  {
    label: 'Dashboard',
    icon: 'grid',
    link: '/dashboard'
  },
  {
    label: 'Management',
    isTitle: true
  },
  {
    label: 'Products',
    icon: 'box',
    link: '/products'
  }, {
    label: 'Customers',
    icon: 'layers',
    link: '/customers'
  }, {
    label: 'Invoices',
    icon: 'file-text',
    link: '/invoices'
  }, {
    label: 'users',
    icon: 'users',
    link: '/users'
  },
];
