
# 前端说明

## 部署

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report
```

## 文件配置

/Fontend/config/index.js里面
```
proxyTable: {
      '/test': {// api是代理根地址
       target:'http://183.3.139.134:8686',
        changeOrigin: true,
        pathRewrite: {
          '^/test': ''// api在代理时转义成空字符
        }
```

## 技术栈

*vue+vue-router（核心框架）*

*axios（请求）*

*vue-elementui（UI框架）*

*Echart（图表框架）*

*jsplumb(流程图框架)*

## vuejs路由

路径配置放在 /Fontend/src/routers/index.js 里面。
路由结构放在 /Fontend/src/routers/topMenu_route.js 里面。

## 模块介绍

组件放在 /Fontend/src/components 里面。

```
bigMenuBar.vue   creatJob.vue        formListBar.vue  leftBar.vue        operation.vue  uploadData.vue
charts.vue       editor.vue          intellBar.vue    maskNodeChart.vue  search.vue     uploadProgram.vue
chooseStyle.vue  explainleftBar.vue  labModel.vue     nodeMask.vue       titles.vue
```

## 页面介绍

核心页面放在 /Fontend/src/view 里面。

