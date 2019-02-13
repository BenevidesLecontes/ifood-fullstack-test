import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'formattedTel'
})
export class FormattedTelPipe implements PipeTransform {

  transform(value): string {
    if (value && value !== '') {
      try {
        let tel;
        const receivedValue = (value) ? value.toString().replace(/[^\d]+/g, '') : value;
        if (receivedValue.length === 11) {
          tel = `(${receivedValue.substring(0, 2)}) ${receivedValue.substring(
            2,
            5
          )}-${receivedValue.substring(5, receivedValue.length)}`;
        } else if (receivedValue.length <= 4) {
          tel = receivedValue.substring(0, 4);
        } else {
          tel = `(${receivedValue.substring(0, 2)}) ${receivedValue.substring(
            2,
            4
          )}-${receivedValue.substring(4, receivedValue.length)}`;
        }
        return tel;
      } catch (e) {
        return value;
      }
    }
  }

}
