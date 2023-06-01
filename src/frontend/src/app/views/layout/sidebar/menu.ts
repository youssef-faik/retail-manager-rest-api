import {MenuItem} from './menu.model';

export const MENU: MenuItem[] = [
  {
    label: 'Accueil',
    isTitle: true
  },
  {
    label: 'Tableau de bord',
    icon: 'grid',
    link: '/dashboard'
  },
  {
    label: 'Gestion',
    isTitle: true
  },
  {
    label: 'Produits',
    icon: 'box',
    link: '/products'
  },
  {
    label: 'Clients',
    icon: 'layers',
    link: '/customers'
  },
  {
    label: 'Factures',
    icon: 'file-text',
    link: '/invoices'
  },
  {
    label: 'Utilisateurs',
    icon: 'users',
    link: '/users'
  },
];
