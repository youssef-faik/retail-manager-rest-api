import {Component, OnInit} from '@angular/core';

import {NgbCalendar, NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';
import {ChartDataDto, TableauDeBordService} from "../../../libs/openapi/out";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  preserveWhitespaces: true
})
export class DashboardComponent implements OnInit {

  /**
   * Apex chart
   */
  public customersChartOptions: any = {};
  public ordersChartOptions: any = {};
  public monthlySalesChartOptions: any = {};
  public isSalesDataLoaded = false;
  public isOrdersDataLoaded = false;
  public isCustomersDataLoaded = false;
  public salesData: Array<number> | undefined;
  public ordersData: Array<number> | undefined;
  public customersData: Array<number> | undefined;

  // colors and font variables for apex chart
  obj = {
    primary: "#6571ff",
    secondary: "#7987a1",
    success: "#05a34a",
    info: "#66d1d1",
    warning: "#fbbc06",
    danger: "#ff3366",
    light: "#e9ecef",
    dark: "#060c17",
    muted: "#7987a1",
    gridBorder: "rgba(77, 138, 240, .15)",
    bodyColor: "#000",
    cardBg: "#fff",
    fontFamily: "'Roboto', Helvetica, sans-serif"
  }

  /**
   * NgbDatepicker
   */
  currentDate: NgbDateStruct;

  constructor(
    private calendar: NgbCalendar,
    private dashboardService: TableauDeBordService
  ) {
  }

  ngOnInit(): void {
    this.currentDate = this.calendar.getToday();

    this.dashboardService.getSales().subscribe(
      data => {
        this.monthlySalesChartOptions = getMonthlySalesChartOptions(this.obj, data);
        this.isSalesDataLoaded = true;
        this.salesData = data.data
      },
      error => {
      }
    );

    this.dashboardService.getOrders().subscribe(
      data => {
        this.ordersChartOptions = getOrdersChartOptions(this.obj, data);
        this.isOrdersDataLoaded = true;
        this.ordersData = data.data;
      },
      error => {
      }
    );

    this.dashboardService.getCustomers().subscribe(
      data => {
        this.customersChartOptions = getCustomersChartOptions(this.obj, data);
        this.isCustomersDataLoaded = true;
        this.customersData = data.data;
      },
      error => {
      }
    );
  }


  getTotal(numbers: number[] | undefined): number {
    let total: number = 0;
    // @ts-ignore
    for (const number of numbers) {
      total += number;
    }
    return total
  }
}


/**
 * Customerse chart options
 */
function getCustomersChartOptions(obj: any, ordersData: ChartDataDto) {
  return {
    series: [{
      name: '',
      data: ordersData.data
    }],
    chart: {
      animations: {
        enabled: false
      },
      type: "line",
      height: 240,
      parentHeightOffset: 0,
      foreColor: obj.bodyColor,
      background: obj.cardBg,
      toolbar: {
        show: false
      }
    },
    colors: [obj.primary],
    plotOptions: {
      line: {

        dataLabels: {
          show: false
        }
      }
    }, grid: {
      padding: {
        bottom: -4
      },
      borderColor: obj.gridBorder,
      xaxis: {
        lines: {
          show: false
        }
      },

    },
    xaxis: {
      type: 'datetime',
      categories: ordersData.dates,
      axisBorder: {
        color: obj.gridBorder,
      },
      axisTicks: {
        color: obj.gridBorder,
      },
    },
    yaxis: {
      title: {
        text: 'Nombre de clients',
        style: {
          size: 9,
          color: obj.muted
        }
      },
      labels: {

        offsetX: 0,
      },
    },
    legend: {
      show: true,
      position: "top",
      horizontalAlign: 'center',
      fontFamily: obj.fontFamily,
      itemMargin: {
        horizontal: 8,
        vertical: 0
      },
    },
    stroke: {
      width: 2,
      curve: "smooth"
    },
    markers: {
      size: 0
    },
  }
}


/**
 * Orders chart options
 */
function getOrdersChartOptions(obj: any, ordersData: ChartDataDto) {
  return {
    series: [{
      name: 'Commandes',
      data: ordersData.data
    }],
    chart: {
      animations: {
        enabled: false
      },
      type: "bar",
      height: 240,
      parentHeightOffset: 0,
      foreColor: obj.bodyColor,
      background: obj.cardBg,
      toolbar: {
        show: false
      }
    },
    colors: [obj.primary],
    plotOptions: {
      bar: {
        borderRadius: 2,
        columnWidth: "60%",
        dataLabels: {
          position: 'top',
          orientation: 'vertical',
        }
      }
    },
    grid: {
      padding: {
        bottom: -4
      },
      borderColor: obj.gridBorder,
      xaxis: {
        lines: {
          show: false
        }
      },

    },
    xaxis: {
      type: 'datetime',
      categories: ordersData.dates,
      axisBorder: {
        color: obj.gridBorder,
      },
      axisTicks: {
        color: obj.gridBorder,
      },
    },
    yaxis: {
      title: {
        text: 'Nombre de commandes',
        style: {
          size: 9,
          color: obj.muted
        }
      },
      labels: {
        offsetX: 0,
      },
    },
    legend: {
      show: true,
      position: "top",
      horizontalAlign: 'center',
      fontFamily: obj.fontFamily,
      itemMargin: {
        horizontal: 8,
        vertical: 0
      },
    },
    stroke: {
      width: 0
    },
    dataLabels: {
      enabled: true,
      style: {
        fontSize: '10px',
        fontFamily: obj.fontFamily,
      },
      offsetY: -27
    }
  }
}

/**
 * Monthly sales chart options
 */
function getMonthlySalesChartOptions(obj: any, salesData: ChartDataDto) {
  return {
    series: [{
      name: 'Ventes',
      data: salesData.data
    }],
    chart: {
      animations: {
        enabled: false
      },
      type: 'bar',
      height: '318',
      parentHeightOffset: 0,
      foreColor: obj.bodyColor,
      background: obj.cardBg,
      toolbar: {
        show: false
      },
    },
    colors: [obj.primary],
    fill: {
      opacity: .9
    },
    grid: {
      padding: {
        bottom: -4
      },
      borderColor: obj.gridBorder,
      xaxis: {
        lines: {
          show: false
        }
      }
    },
    xaxis: {
      type: 'datetime',
      categories: salesData.dates,
      axisBorder: {
        color: obj.gridBorder,
      },
      axisTicks: {
        color: obj.gridBorder,
      },
    },
    yaxis: {
      title: {
        text: 'Total des ventes en MAD',
        style: {
          size: 9,
          color: obj.muted
        }
      },
      labels: {
        offsetX: 0,
      },
    },
    legend: {
      show: true,
      position: "top",
      horizontalAlign: 'center',
      fontFamily: obj.fontFamily,
      itemMargin: {
        horizontal: 8,
        vertical: 0
      },
    },
    stroke: {
      width: 0
    },
    dataLabels: {
      enabled: true,
      style: {
        fontSize: '10px',
        fontFamily: obj.fontFamily,
      },
      offsetY: -27
    },
    plotOptions: {
      bar: {
        columnWidth: "50%",
        borderRadius: 2,
        dataLabels: {
          position: 'top',
          orientation: 'vertical',
        }
      },
    }
  }
}
