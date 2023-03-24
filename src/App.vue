<template>
  <div id="app">
    <div class="grid grid-cols-12 grid-rows-12 max-h-screen  gap-4">

      <div class="grid col-span-12 row-span-1 border-2   border-green-300">
        
          <div class="navbar bg-base-100">
            <div class="flex-auto">
              <a class="btn btn-ghost normal-case text-xl">daisyUI</a>
            </div>
            <div class="flex-auto">
              <div class="form-control mt-2">
                <div class="input-group">
                  <input type="text" placeholder="bookId..." class="input input-bordered" />
                  <input type="text" placeholder="bookName..." class="input input-bordered" />
                  <button class="btn btn-info">Add</button>
                </div>
              </div>
            </div>
            <div class="flex-none">
              <ul class="menu menu-horizontal px-1">
                <li><a>Item 1</a></li>
                <button data-act-class="shadow-outline" data-set-theme="light" class="bg-green-700 focus:outline-none m-1 rounded p-2">light</button>
                <select data-choose-theme >
                    <option value="light">Default</option>
                    <option value="masum">masum</option>
                    <option value="light">light</option>
                </select>
                <li tabindex="0">
                  <a>Theme</a>
                  <ul tabindex="0" class="shadow menu dropdown-content bg-base-100 rounded-box " data-choose-theme="">
                    <li><a data-set-theme="masum">Masum</a></li>
                    <li><a data-set-theme="emerald">emerald</a></li>
                    <li><a data-set-theme="light">light</a></li>
                    <li><a data-set-theme="dark">dark</a></li>
                    <li><a data-set-theme="cupcake">cupcake</a></li>
                    <li><a data-set-theme="bumblebee">bumblebee</a></li>
                    <li><a data-set-theme="aqua">aqua</a></li>
                    <li><a data-set-theme="corporate">corporate</a></li>
                    <li><a data-set-theme="synthwave">synthwave</a></li>
                    <li><a data-set-theme="retro">retro</a></li>
                    <li><a data-set-theme="cyberpunk">cyberpunk</a></li>
                    <li><a data-set-theme="valentine">valentine</a></li>
                    <li><a data-set-theme="halloween">halloween</a></li>
                    <li><a data-set-theme="garden">garden</a></li>
                    <li><a data-set-theme="forest">forest</a></li>
                    <li><a data-set-theme="lofi">lofi</a></li>
                    <li><a data-set-theme="pastel">pastel</a></li>
                    <li><a data-set-theme="fantasy">fantasy</a></li>
                    <li><a data-set-theme="wireframe">wireframe</a></li>
                    <li><a data-set-theme="black">black</a></li>
                    <li><a data-set-theme="luxury">luxury</a></li>
                    <li><a data-set-theme="dracula">dracula</a></li>
                    <li><a data-set-theme="cmyk">cmyk</a></li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
      </div>

      <div class="col-span-2 row-span-1 border-2  grid place-items-center ">
        <p class="text-2xl text-center">Redis设计与实现</p>
      </div>
      <div class="col-span-1 row-span-11"></div>


      <div class="col-span-6  row-span-11  overflow-y-auto overflow-x-hidden border-2 border-yellow-300">
        <p class="item-center">
          testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest
        </p>
        <p class="item-center">test</p>
        <p class="item-center">test</p>
        <p class="item-center">test</p>
        <p class="item-center">test</p>
      </div>


      <div class="col-span-1 row-span-11"></div>



      <div class="col-span-2 row-span-1 border-2  grid place-items-center ">
        <p class="text-2xl text-center">Redis设计与实现</p>
      </div>

      <div class="col-span-2 row-span-10 border-2  border-green-300">
        <div class="overflow-y-auto overflow-x-hidden">
          <ul class="menu bg-base-100 ">
            <li><a>Item 1</a></li>
            <li class="bordered"><a>I have border</a></li>
            <li><a>Item 3</a></li>
            <li><a>Item 3</a></li>
            <li><a>Item 3</a></li>
          </ul>
        </div>
      </div>

      <div class="col-span-2 row-span-10 overflow-y-auto overflow-x-hidden border-2 border-red-300">
        <ul class="menu bg-base-100 ">
          <li><a>Item 1</a></li>
          <li class="bordered"><a>I have border</a></li>
          <li><a>Item 3</a></li>
        </ul>
      </div>
    </div>

  </div>
</template>

<script>
import themeChange from 'theme-change'

export default {
  name: 'App',
  components: {},
  mounted: function () {
    themeChange(false)
  },
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
