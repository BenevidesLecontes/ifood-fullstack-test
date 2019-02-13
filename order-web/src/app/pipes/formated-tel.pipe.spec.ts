import { FormattedTelPipe } from './formated-tel.pipe';

describe('FormattedTelPipe', () => {
  it('create an instance', () => {
    const pipe = new FormattedTelPipe();
    expect(pipe).toBeTruthy();
  });
});
