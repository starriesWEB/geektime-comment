const bookPrefix = 'book|'
const CUR_BOOK_NAME = 'curBookName'
const CUR_BOOK_ID = 'curBookId'
const CUR_ARTICLE_TITLE = 'curArticleTitle'
const CUR_ARTICLE_ID = 'curArticleId'

export default class LocalStorageUtil {


  static setBookItem(key, value) {
    localStorage.setItem(bookPrefix + key, value);
  }
  static setCurBookName(value) {
    localStorage.setItem(CUR_BOOK_NAME, value);
  }
  static setCurArticleTitle(value) {
    localStorage.setItem(CUR_ARTICLE_TITLE, value);
  }
  static setCurBookId(value) {
    localStorage.setItem(CUR_BOOK_ID, value);
  }
  static setCurArticleId(value) {
    localStorage.setItem(CUR_ARTICLE_ID, value);
  }

  static getCurBookNameFromConst() {
    let item = localStorage.getItem(CUR_BOOK_NAME);
    return item ? item : null
  }
  static getCurArticleTitleFromConst() {
    let item = localStorage.getItem(CUR_ARTICLE_TITLE);
    return item ? item : null
  }
  static getCurBookIdFromConst() {
    let item = localStorage.getItem(CUR_BOOK_ID);
    return item ? item : null
  }


  static getCurArticleIdFromConst() {
    let item = localStorage.getItem(CUR_ARTICLE_ID);
    return item ? item : null
  }

  static getCurBookId(key) {
    let item = localStorage.getItem(bookPrefix + key);
    return item ? item : null
  }


  static removeItem(key) {
    localStorage.removeItem(key);
  }

  static clear() {
    localStorage.clear();
  }

  static getAllBookKey() {
    let keys = []
    let idx = 0;
    for (let i = 0; i < localStorage.length; i++) {
      let key = localStorage.key(i)
      if (key.startsWith(bookPrefix)) {
        keys[idx++] = key.replace(bookPrefix, '');
      }
    }
    return keys;
  }

  static getAllBookItem() {
    let items = []
    for (let i = 0; i < localStorage.length; i++) {
      let item = localStorage.getItem(localStorage.key(i))
      if (item.startsWith(bookPrefix)) {
        items[i] = item.replace(bookPrefix, '');
      }
    }
    return items;
  }
}
