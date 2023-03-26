<template>
  <div id="app" :data-theme="theme.curTheme">

    <div id="alert-box" v-bind:class="{'alert-success': alertMsg.success, 'alert-warning': alertMsg.warning}"
         class="alert w-auto fixed top-1/3 left-1/2 transform -translate-x-1/2 p-6 rounded-md shadow-lg z-10"
         v-show="alertMsg.show">
      <p class="text-xl font-semibold mb-2">{{ alertMsg.title }}</p>
    </div>

    <div class="grid grid-cols-12 grid-rows-12 max-h-screen  gap-4">

      <div class="grid col-span-12 row-span-1 border-2   border-green-300">
        <div class="navbar bg-base-100">
          <div class="flex-auto">
            <a class="btn btn-ghost normal-case text-xl">daisyUI</a>
          </div>
          <div class="flex-auto">
            <div class="form-control mt-2">
              <div class="input-group">
                <input type="text" placeholder="bookId..." class="input input-bordered  input-primary"
                       v-model="book.bookId"/>
                <input type="text" placeholder="bookName..." class="input input-bordered  input-primary"
                       v-model="book.bookName"/>
                <button class="btn btn-primary" @click="addBook">Add</button>
              </div>
            </div>
          </div>
          <div class="flex-none">
            <ul class="menu menu-horizontal px-1">
              <button class="btn btn-accent" @click="toggleTheme">Toggle Theme</button>
            </ul>
          </div>
        </div>
      </div>


      <div class="col-span-2 row-span-1 border-2  grid place-items-center ">
        <p class="text text-center">{{ book.curBookName }}</p>
      </div>
      <div class="col-span-1 row-span-11"></div>


      <div class="col-span-6  row-span-11  overflow-y-auto overflow-x-hidden border-2 border-yellow-300">

        <div class="chat chat-start">
          <div class="chat-image avatar">
            <div class="w-10 rounded-full border-2">
              <img src="./assets/logo.png" />
            </div>
          </div>
          <div class="chat-bubble chat-bubble-success">It's over Anakin,I have the high ground.It's over Anakin,I have the high ground.It's
            over Anakin,I have the high ground.
          </div>
        </div>
        <div class="chat chat-end">
          <div class="chat-image avatar">
            <div class="w-10 rounded-full border-2">
              <img src="./assets/logo.png" />
            </div>
          </div>
          <div class="chat-bubble chat-bubble-warning">You underestimate my power!</div>
        </div>

        <div class="divider"></div>

        <div class="chat chat-start">
          <div class="chat-image avatar">
            <div class="w-10 rounded-full border-2">
              <img src="./assets/logo.png" />
            </div>
          </div>
          <div class="chat-bubble chat-bubble-success">It's over Anakin,I have the high ground.It's over Anakin,I have the high ground.It's
            over Anakin,I have the high ground.
          </div>
        </div>
        <div class="chat chat-end">
          <div class="chat-image avatar">
            <div class="w-10 rounded-full border-2">
              <img src="./assets/logo.png" />
            </div>
          </div>
          <div class="chat-bubble chat-bubble-warning">You underestimate my power!</div>
        </div>
      </div>


      <div class="col-span-1 row-span-11"></div>


      <div class="col-span-2 row-span-1 border-2  grid place-items-center ">
        <p class="text text-center">{{ article.curTitle }}</p>
      </div>

      <div class="col-span-2 row-span-10 border-2  border-green-300">
        <div class="overflow-y-auto overflow-x-hidden">
          <ul class="menu menu-compact">
            <li v-for="item in bookList" :key="item"><a v-bind:class="{'active': item === book.curBookName}" @click="selectCurBook(item)">{{ item }}</a></li>
          </ul>
        </div>
      </div>

      <div class="col-span-2 row-span-10 overflow-y-auto overflow-x-hidden border-2 border-red-300">
        <div class="overflow-y-auto overflow-x-hidden">
          <ul class="menu menu-compact">
            <li v-for="item in articleList" :key="item.id"><a v-bind:class="{'active': item.id === article.curId}" @click="selectCurArticle(item)">{{ item.article_title }}</a></li>
          </ul>
        </div>
      </div>
    </div>

  </div>
</template>

<script>

import localStorageUtil from './utils/localStorageUtil'
import {getCommentApi, getArticleApi} from './api/comment'

export default {
  name: 'App',
  mounted() {
    this.getAllBookName()
    this.book.curBookName = localStorageUtil.getCurBookNameFromConst()
    this.book.curBookId = localStorageUtil.getCurBookIdFromConst()
    this.articleParams.cid = this.book.curBookId
    this.getArticleList()
    getCommentApi()
  },
  data() {
    return {
      theme: {
        isDark: true,
        curTheme: 'halloween',
      },
      book: {
        bookId: '',
        bookName: '',
        curBookId: 0,
        curBookName: ''
      },
      bookList: [],
      alertMsg: {
        show: false,
        timer: null,
        title: '',
        success: false,
        warning: false
      },
      articleParams: {
        cid: 0,
        size: 100,
        prev: 0,
        order: "earliest",
        sample: false,
      },
      articleList: [],
      article: {
        curId: 0,
        curTitle: ''
      }
    }
  },
  methods: {
    toggleTheme() {
      this.theme.isDark = !this.theme.isDark
      if (this.theme.isDark) {
        this.theme.curTheme = 'halloween'
      } else {
        this.theme.curTheme = 'bumblebee'
      }
    },

    addBook() {
      if ((typeof this.book.bookId === 'undefined') || (typeof this.book.bookName === 'undefined') || (this.book.bookId.trim().length === 0) || (this.book.bookName.trim().length === 0)) {
        this.alertMsg.warning = true
        this.alertMsg.success = false
        this.openAlert("添加失败,空文本")
        return
      }
      localStorageUtil.setBookItem(this.book.bookName, this.book.bookId)
      this.book = {}
      this.getAllBookName()
      this.alertMsg.warning = false
      this.alertMsg.success = true
      this.openAlert("添加成功")
    },
    getAllBookName() {
      this.bookList = localStorageUtil.getAllBookKey()
    },
    openAlert(title) {
      this.alertMsg.title = title
      this.alertMsg.show = true
      this.alertMsg.timer = setTimeout(() => {
        this.closeAlert()
      }, 1500)
    },
    closeAlert() {
      clearTimeout(this.alertMsg.timer)
      this.alertMsg.show = false
      this.alertMsg.title = ''
      this.alertMsg.message = ''
    },
    selectCurBook(bookName) {
      this.article.curTitle = ''
      this.article.curId = ''
      this.articleList = []

      this.book.curBookName = bookName
      localStorageUtil.setCurBookName(bookName)
      this.book.curBookId = localStorageUtil.getCurBookId(bookName)
      localStorageUtil.setCurBookId(this.book.curBookId)

      this.articleParams.cid = this.book.curBookId
      this.getArticleList()
    },
    getArticleList() {
      getArticleApi(this.articleParams).then(req => {
        this.articleList = req.data.data.list
      })
    },
    selectCurArticle(item) {
      this.article.curTitle = item.article_title
      localStorageUtil.setCurArticleTitle(item.article_title)
      this.article.curId = item.id
      localStorageUtil.setCurArticleId(item.id)
    }

  }
}
</script>

<style>
#app {
  /*font-family: Avenir, Helvetica, Arial, sans-serif;*/
  /*-webkit-font-smoothing: antialiased;*/
  /*-moz-osx-font-smoothing: grayscale;*/
  /*text-align: center;*/
  /*color: #2c3e50;*/
  /*margin-top: 60px;*/
}</style>
