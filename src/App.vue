<template>
  <div id="app" :data-theme="theme.curTheme">
    <div id="alert-box" v-bind:class="{'alert-success': alertMsg.success, 'alert-warning': alertMsg.warning}"
         class="alert w-auto fixed top-1/3 left-1/2 transform -translate-x-1/2 p-6 rounded-md shadow-lg z-10"
         v-show="alertMsg.show">
      <p class="text-xl font-semibold mb-2">{{ alertMsg.title }}</p>
    </div>

    <div class="grid grid-cols-12 grid-rows-12 max-h-screen  gap-4">

      <div class="grid col-span-12 row-span-1 ">
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
          <div class="flex-none form-control mr-5">
            <label class="label cursor-pointer">
              <span class="label-text mr-3">Only Show Auth</span>
              <input type="checkbox" class="toggle" v-model="onlyShowAuth" />
            </label>
          </div>
          <label class="flex-none swap swap-rotate mr-5">
            <input type="checkbox" />
            <svg  @click="toggleTheme" class="swap-on fill-current w-10 h-10" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M5.64,17l-.71.71a1,1,0,0,0,0,1.41,1,1,0,0,0,1.41,0l.71-.71A1,1,0,0,0,5.64,17ZM5,12a1,1,0,0,0-1-1H3a1,1,0,0,0,0,2H4A1,1,0,0,0,5,12Zm7-7a1,1,0,0,0,1-1V3a1,1,0,0,0-2,0V4A1,1,0,0,0,12,5ZM5.64,7.05a1,1,0,0,0,.7.29,1,1,0,0,0,.71-.29,1,1,0,0,0,0-1.41l-.71-.71A1,1,0,0,0,4.93,6.34Zm12,.29a1,1,0,0,0,.7-.29l.71-.71a1,1,0,1,0-1.41-1.41L17,5.64a1,1,0,0,0,0,1.41A1,1,0,0,0,17.66,7.34ZM21,11H20a1,1,0,0,0,0,2h1a1,1,0,0,0,0-2Zm-9,8a1,1,0,0,0-1,1v1a1,1,0,0,0,2,0V20A1,1,0,0,0,12,19ZM18.36,17A1,1,0,0,0,17,18.36l.71.71a1,1,0,0,0,1.41,0,1,1,0,0,0,0-1.41ZM12,6.5A5.5,5.5,0,1,0,17.5,12,5.51,5.51,0,0,0,12,6.5Zm0,9A3.5,3.5,0,1,1,15.5,12,3.5,3.5,0,0,1,12,15.5Z"/></svg>
            <svg  @click="toggleTheme" class="swap-off fill-current w-10 h-10" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M21.64,13a1,1,0,0,0-1.05-.14,8.05,8.05,0,0,1-3.37.73A8.15,8.15,0,0,1,9.08,5.49a8.59,8.59,0,0,1,.25-2A1,1,0,0,0,8,2.36,10.14,10.14,0,1,0,22,14.05,1,1,0,0,0,21.64,13Zm-9.5,6.69A8.14,8.14,0,0,1,7.08,5.22v.27A10.15,10.15,0,0,0,17.22,15.63a9.79,9.79,0,0,0,2.1-.22A8.11,8.11,0,0,1,12.14,19.73Z"/></svg>
          </label>
        </div>
      </div>


      <div class="col-span-2 row-span-1 border-2  grid place-items-center ">
        <p class="text text-center">{{ book.curBookName }}</p>
      </div>
      <div class="col-span-1 row-span-11"></div>


      <div class="col-span-6  row-span-11  overflow-y-auto overflow-x-hidden border-2 ">

        <div v-for="item in commentList" :key="item.score">
          <div class="chat chat-start">
            <div class="chat-image avatar">
              <div class="w-10 rounded-full border-2">
                <img :src="item.user_header" @error="onImageError" />
              </div>
            </div>
            <div class="chat-bubble chat-bubble-success" v-html="item.comment_content">
            </div>
          </div>
          <div class="chat chat-end" v-if="item && (item.replies !== undefined && item.replies !== null)">
            <div class="chat-image avatar">
              <div class="w-10 rounded-full border-2">
                <img src="./assets/right.gif" />
              </div>
            </div>
            <div class="chat-bubble chat-bubble-warning" v-html="item.replies[0].content">
            </div>
          </div>

          <div class="divider"></div>
        </div>


      </div>


      <div class="col-span-1 row-span-10">
      </div>


      <div class="col-span-2 row-span-1 border-2  grid place-items-center ">
        <p class="text text-center">{{ article.curTitle }}</p>
      </div>

      <div class="col-span-2 row-span-10 border-2  ">
        <div class="overflow-y-auto overflow-x-hidden">
          <ul class="menu menu-compact">
            <li v-for="item in bookList" :key="item">
              <a v-bind:class="{'active': item == book.curBookName}" @click="selectCurBook(item)">
                {{ item }}
              </a>
            </li>
          </ul>
        </div>
      </div>

      <div class="col-span-2 row-span-10 overflow-y-auto overflow-x-hidden border-2 ">
        <div class="overflow-y-auto overflow-x-hidden">
          <ul class="menu menu-compact">
            <li v-for="item in articleList" :key="item.id">
              <a v-bind:class="{'active': item.id == article.curId}" @click="selectCurArticle(item)">
                {{ item.article_title }}
              </a>
            </li>
          </ul>
        </div>
      </div>
      <div class="row-span-1">
        <button class="btn btn-primary ml-3" @click="getCommentList">NEXT</button>
      </div>

    </div>

  </div>
</template>

<script>

import localStorageUtil from './utils/localStorageUtil'
import {getCommentApi, getArticleApi} from './api/comment'

export default {
  name: 'App',
  created() {
    this.getAllBookName()
    this.book.curBookName = localStorageUtil.getCurBookNameFromConst()
    this.book.curBookId = localStorageUtil.getCurBookIdFromConst()
    this.articleParams.cid = this.book.curBookId
    this.getArticleList(true)
    this.onlyShowAuth = localStorageUtil.getOnlyShowAuthFromConst()
  },
  data() {
    return {
      defaultUrl: require("./assets/left.png"),
      theme: {
        isDark: true,
        curTheme: 'halloween',
      },
      onlyShowAuth: true,
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
      articleList: [
        {
          id: 0,
          article_title: ''
        }
      ],
      article: {
        curId: 0,
        curTitle: ''
      },
      commentParams: {
        aid: 0,
        prev: 0
      },
      commentList: [
        {
          score: "",
          comment_content: "",
          user_header: "",
          replies: [
            {
              content: "",
              user_name: "",
            }
          ]
        }
      ],
      comment: {
        score: ""
      }
    }
  },
  watch: {
    onlyShowAuth: function (newVal) {
      localStorageUtil.setOnlyShowAuth(newVal)
    }
  },
  methods: {
    onImageError(event) {
      event.target.src = this.defaultUrl
    },
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
      this.article = {}
      this.articleList = []
      this.commentList = []

      localStorageUtil.removeCurArticleId()
      localStorageUtil.removeCurArticleTitle()

      this.book.curBookName = bookName
      localStorageUtil.setCurBookName(bookName)
      this.book.curBookId = localStorageUtil.getCurBookId(bookName)
      localStorageUtil.setCurBookId(this.book.curBookId)

      this.articleParams.cid = this.book.curBookId
      this.getArticleList(false)
    },
    getArticleList(initFlag) {
      getArticleApi(this.articleParams).then(resp => {
        this.articleList = resp.data.data.list
        if (initFlag) {
          let id = localStorageUtil.getCurArticleIdFromConst();
          let title = localStorageUtil.getCurArticleTitleFromConst();
          if (id == null) {
            this.article.curId = this.articleList[0].id
          } else {
            this.article.curId = id
          }
          if (title == null) {
            this.article.curTitle = this.articleList[0].article_title
          } else {
            this.article.curTitle = title
          }
        } else {
          this.article.curId = this.articleList[0].id
          this.article.curTitle = this.articleList[0].article_title
        }

        this.commentParams.aid = this.article.curId
        this.commentParams.prev = 0
        this.getCommentList()
      })
    },
    selectCurArticle(item) {
      this.article = {}
      this.commentList = []

      this.article.curTitle = item.article_title
      this.article.curId = item.id
      localStorageUtil.setCurArticleTitle(item.article_title)
      localStorageUtil.setCurArticleId(item.id)

      this.commentParams.aid = this.article.curId
      this.commentParams.prev = 0
      this.getCommentList()
    },
    getCommentList() {
      getCommentApi(this.commentParams).then(resp => {
        this.commentList = resp.data.data.list
        this.commentParams.prev = this.commentList[this.commentList.length - 1].score
        if (this.onlyShowAuth) {
          this.commentList = this.commentList.filter(item => item.replies !== undefined && item.replies.length !== 0)
        }
        if (this.commentList.length === 0) {
          this.openAlert("当前页无评论")
        }
      })
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
