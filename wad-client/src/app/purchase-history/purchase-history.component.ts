import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSort, MatTableDataSource} from '@angular/material';
import {UserService} from '../user.service';
import {take} from 'rxjs/operators';

@Component({
  selector: 'app-purchase-history',
  templateUrl: './purchase-history.component.html',
  styleUrls: ['./purchase-history.component.css']
})
export class PurchaseHistoryComponent implements OnInit {

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  dataSource: MatTableDataSource<any>;
  displayedColumns: string[] = ['phone', 'receiverName', 'receiverPhone', 'purchaseDate'];

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getPurchases(this.userService.getCurrentUser().userId).pipe(take(1)).subscribe((purchases: any[]) => {
      this.dataSource = new MatTableDataSource<any>(purchases);
      this.dataSource.sort = this.sort;
    });
  }

}
