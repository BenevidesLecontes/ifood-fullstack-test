import {AfterViewInit, Directive, ElementRef, HostListener, Renderer2} from '@angular/core';

@Directive({
  selector: '[appDateFormat]'
})
export class DateFormatDirective {

  private keyPressEvent: any;

  constructor(private el: ElementRef, private renderer: Renderer2) {

  }

  @HostListener('input', ['$event'])
  onInputChange(e: Event | any) {
    if (!this.keyPressEvent || this
      .keyPressEvent !== 'Backspace') {
      const numChars = e.target.value.length;
      if (numChars === 2 || numChars === 5) {
        let thisVal = e.target.value;
        thisVal += '/';
        e.target.value = thisVal;
      }
    }
  }

  @HostListener('document:keydown', ['$event'])
  onKeydown(event: Event | any) {
    this.keyPressEvent = event.key;
  }

}
