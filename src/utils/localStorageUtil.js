const book_prefix = 'book|'
const cur_book_name = 'curBookName'
const cur_book_id = 'curBookId'
const cur_article_title = 'curArticleTitle'
const cur_article_id = 'curArticleId'
const only_show_auth = 'onlyShowAuth'

export default class LocalStorageUtil {


  static setOnlyShowAuth(value) {
    localStorage.setItem(only_show_auth, value);
  }

  static getOnlyShowAuthFromConst() {
    return localStorage.getItem(only_show_auth)
  }

  static setBookItem(key, value) {
    localStorage.setItem(book_prefix + key, value);
  }
  static setCurBookName(value) {
    localStorage.setItem(cur_book_name, value);
  }
  static setCurArticleTitle(value) {
    localStorage.setItem(cur_article_title, value);
  }
  static setCurBookId(value) {
    localStorage.setItem(cur_book_id, value);
  }
  static setCurArticleId(value) {
    localStorage.setItem(cur_article_id, value);
  }

  static getCurBookNameFromConst() {
    let item = localStorage.getItem(cur_book_name);
    return item ? item : null
  }
  static getCurArticleTitleFromConst() {
    let item = localStorage.getItem(cur_article_title);
    return item ? item : null
  }
  static getCurBookIdFromConst() {
    let item = localStorage.getItem(cur_book_id);
    return item ? item : null
  }


  static getCurArticleIdFromConst() {
    let item = localStorage.getItem(cur_article_id);
    return item ? item : null
  }

  static getCurBookId(key) {
    let item = localStorage.getItem(book_prefix + key);
    return item ? item : null
  }


  static removeCurArticleId() {
    localStorage.removeItem(cur_article_id);
  }
  static removeCurArticleTitle() {
    localStorage.removeItem(cur_article_title);
  }

  static getAllBookKey() {
    let keys = []
    let idx = 0;
    for (let i = 0; i < localStorage.length; i++) {
      let key = localStorage.key(i)
      if (key.startsWith(book_prefix)) {
        keys[idx++] = key.replace(book_prefix, '');
      }
    }
    return keys;
  }

}
