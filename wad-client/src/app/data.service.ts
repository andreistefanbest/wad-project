import { Injectable } from '@angular/core';
import {InMemoryDbService} from 'angular-in-memory-web-api';

@Injectable({
  providedIn: 'root'
})
export class DataService implements InMemoryDbService {

  constructor() { }
  createDb() {
    return {
      products: [
        {
          id: 1, name: 'Chocolate'
        },
        {
          id: 2, name: 'Jam'
        },
        {
          id: 3, name: 'Pie'
        }
      ]
    };
  }
}
