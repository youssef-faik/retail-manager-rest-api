import {Directive, ViewContainerRef} from '@angular/core';

@Directive({
  selector: '[viewerHost]',
})
export class ViewerDirective {
  constructor(public viewContainerRef: ViewContainerRef) {
  }
}
