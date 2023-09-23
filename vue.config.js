const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {                //webpack-dev-server配置
    host: 'localhost',
    port: 80,            //配置本项目运行端口
    https: true,
    proxy: {                //配置代理服务器来解决跨域问题
      '/api': {
        // target: 'https://time.geekbang.org',      //配置要替换的后台接口地址
        target: 'https://geektime.starries.workers.dev',      //配置要替换的后台接口地址
        changOrigin: true,                      //配置允许改变Origin
        pathRewrite: {
          '^/api': ''
        }
      },
    }
  },
})
