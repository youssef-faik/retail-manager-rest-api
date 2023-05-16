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
    link: '/dashboard'
  }, {
    label: 'Customers',
    icon: 'users',
    link: '/dashboard'
  }, {
    label: 'Invoices',
    icon: 'file',
    link: '/dashboard'
  }, {
    label: 'users',
    icon: 'users',
    link: '/users'
  },
];
