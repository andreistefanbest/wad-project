import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {UserService} from '../user.service';
import {PurchaseService} from '../phones/buy-phone/purchase.service';
import {PurchaseHistoryDTO} from './PurchaseHistoryDTO';

@Component({
  selector: 'app-purchase-history',
  templateUrl: './purchase-history.component.html',
  styleUrls: ['./purchase-history.component.css']
})
export class PurchaseHistoryComponent implements OnInit {

  @ViewChild(MatSort) sort: MatSort;
  dataSource: MatTableDataSource<PurchaseHistoryDTO>;
  displayedColumns: string[] = ['phone', 'receiverName', 'receiverPhone', 'purchaseDate'];

  constructor(private purchaseService: PurchaseService,
              private userService: UserService) {
  }

  ngOnInit() {
    this.purchaseService.getPurchaseHistory(this.userService.getCurrentUser().userId).subscribe((purchaseHistory: PurchaseHistoryDTO[]) => {
      this.dataSource = new MatTableDataSource<PurchaseHistoryDTO>(purchaseHistory);
      this.dataSource.sort = this.sort;
    });
  }

}
