import {Component, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ConfigOption, ConfigurationService, ErrorResponse} from "../../../libs/openapi/out";
import {SwalComponent} from "@sweetalert2/ngx-sweetalert2";

@Component({
  selector: 'app-config',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.scss']
})
export class ConfigurationComponent implements OnInit {
  configOptionsFrom: FormGroup;
  configurations: ConfigOption[];

  @ViewChild('errorSwal')
  public readonly swalComponent!: SwalComponent;

  constructor(private configurationService: ConfigurationService) {
  }

  ngOnInit(): void {
    this.configOptionsFrom = new FormGroup({
      'LAST_INVOICE_NUMBER': new FormControl(null, Validators.required),
      'LAST_BL_NUMBER': new FormControl(null, Validators.required)
    });

    this.configurationService.getAllConfigurations(
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data) => {
        this.configurations = data;

        let invoiceNumberOption = this.configurations.filter(option => {
          return option.key === ConfigOption.KeyEnum.InvoiceNumber
        });

        let BlNumberOption = this.configurations.filter(option => {
          return option.key === ConfigOption.KeyEnum.BlNumber
        });

        this.configOptionsFrom.controls['LAST_INVOICE_NUMBER'].setValue(invoiceNumberOption[0].value);
        this.configOptionsFrom.controls['LAST_BL_NUMBER'].setValue(BlNumberOption[0].value);
      }, error => {
        console.log(error)
      });
  }

  onSubmit() {
    if (!this.configOptionsFrom.valid) {
      return
    }

    // this.configurations = data;

    let invoiceNumberOption = this.configurations.filter(option => {
      return option.key === ConfigOption.KeyEnum.InvoiceNumber
    });

    for (const option of this.configurations) {
      if (option.key === ConfigOption.KeyEnum.InvoiceNumber) {
        option.value = this.configOptionsFrom.controls['LAST_INVOICE_NUMBER'].value;
        continue
      }
      if (option.key === ConfigOption.KeyEnum.BlNumber) {
        option.value = this.configOptionsFrom.controls['LAST_BL_NUMBER'].value;

      }
    }

    this.configurationService.setConfigurationValues(
      this.configurations,
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data) => {
        this.swalComponent.title = 'La configuration a été mise à jour avec succès.';
        this.swalComponent.icon = 'success';
        this.swalComponent.fire();
      }, error => {
        this.swalComponent.title = error.error.message;
        this.swalComponent.fire();
      }
    );

  }

}
