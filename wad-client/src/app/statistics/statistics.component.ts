import {Component, OnInit} from '@angular/core';
import {StatisticsService} from './statistics.service';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {

  public readonly CHART_TYPE_BAR = 'bar';

  public chartDatasets: Array<any>;
  public chartLabels: Array<any>;
  public chartColors: Array<any>;
  public chartOptions: any;

  constructor(private statisticsService: StatisticsService) {
  }

  ngOnInit(): void {
    this.statisticsService.getMonthlySales().subscribe((sales: [{ xAxis, yAxis }]) => {
      this.chartDatasets = [
        {
          data: sales.map(sale => sale.yAxis),
          label: 'RON'
        }
      ];

      this.chartLabels = sales.map(sale => sale.xAxis);

      this.chartColors = [
        {
          backgroundColor: 'rgba(54, 162, 235, 0.2)',
          borderColor: 'rgba(54, 162, 235, 1)',
          borderWidth: 2,
        }
      ];

      this.chartOptions = {
        responsive: true
      };
    });
  }

  public chartClicked(e: any): void { }

  public chartHovered(e: any): void { }

}
