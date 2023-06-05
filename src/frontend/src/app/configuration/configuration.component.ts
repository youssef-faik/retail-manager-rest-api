import {Component, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ConfigurationService} from "../../../libs/openapi/out";
import {SwalComponent} from "@sweetalert2/ngx-sweetalert2";

@Component({
  selector: 'app-config',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.scss']
})
export class ConfigurationComponent implements OnInit {
  configOptionsFrom: FormGroup;
  NEXT_INVOICE_NUMBER: any;

  @ViewChild('errorSwal')
  public readonly swalComponent!: SwalComponent;

  constructor(private configurationService: ConfigurationService) {
  }

  ngOnInit(): void {
    this.configOptionsFrom = new FormGroup({
      'NEXT_INVOICE_NUMBER': new FormControl(null, Validators.required),
    });

    this.configurationService.getAllConfigurations(
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data) => {
        // @ts-ignore
        this.configOptionsFrom.controls['NEXT_INVOICE_NUMBER'].setValue(data['NEXT_INVOICE_NUMBER']);
        this.NEXT_INVOICE_NUMBER = data['NEXT_INVOICE_NUMBER'];
      }, error => {
        console.log(error)
      });
  }

  onSubmit() {
    if (!this.configOptionsFrom.valid) {
      return
    }

    this.configurationService.setConfigurationValues(
      {'NEXT_INVOICE_NUMBER': this.configOptionsFrom.controls['NEXT_INVOICE_NUMBER'].value},
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      () => {
        this.swalComponent.title = 'La configuration a été mise à jour avec succès.';
        this.swalComponent.icon = 'success';
        this.swalComponent.fire();
      }, error => {
        this.swalComponent.title = error.error.message;
        this.swalComponent.icon = 'error';
        this.swalComponent.fire();

        this.configOptionsFrom.controls['NEXT_INVOICE_NUMBER'].setValue(this.NEXT_INVOICE_NUMBER);
      }
    );

  }

}
