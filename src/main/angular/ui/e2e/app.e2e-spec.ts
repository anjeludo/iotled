import { UiPage } from './app.po';

describe('ui App', () => {
  let page: UiPage;

  beforeEach(() => {
    page = new UiPage();
  });

  it('should display welcome message', done => {
    page.navigateTo();
    page.getParagraphText()
      .then(msg => expect(msg).toEqual('Welcome to app!!'))
      .then(done, done.fail);
  });
});
