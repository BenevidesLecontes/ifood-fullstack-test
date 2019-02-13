import { FormControl, FormGroup } from '@angular/forms';

export function markFormGroup(
  formGroup: FormGroup,
  option: 'markAsTouched' | 'markAsDirty' = 'markAsTouched'
) {
  if (formGroup.controls) {
    Object.keys(formGroup.controls).forEach(key => {
      const control = formGroup.controls[key];

      if (control instanceof FormControl) {
        control[option]();
        control.updateValueAndValidity();
      } else if (control instanceof FormGroup) {
        markFormGroup(control);
      }
    });
  }
}
