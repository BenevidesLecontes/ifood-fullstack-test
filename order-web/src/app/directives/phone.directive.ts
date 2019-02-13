import {Directive, ElementRef, HostListener} from '@angular/core';
import {FormattedTelPipe} from '../pipes/formated-tel.pipe';

@Directive({
  selector: '[appPhone]'
})
export class PhoneDirective {
  private formattedPipe = new FormattedTelPipe();

  constructor(private element: ElementRef) {
  }

  @HostListener('input', ['$event'])
  onInputChange(event: Event | any) {
    this.tel(event.target.value);
  }

  tel(value: string) {
    value = this.formattedPipe.transform(value);
    if (value) {
      this.element.nativeElement.value = value;
    }
  }

}
