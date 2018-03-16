<template>
  <div id="editor" type="text/plain" style="min-width: 610px"></div>
</template>

<script>
  import WangEditor from 'wangeditor';
 export default {
  name: 'editor',
  data () {
    return {
      editor:"",
    }
  },
  mounted:function () {
    this.createEditor();
  },
   destroyed(){
     /*UE.delEditor('editor');*/
   },
  methods: {
    createEditor:function(){
      this.editor = new WangEditor('#editor');
      this.editor.create();
      console.log(this.editor.config)
      this.initEditorConfig();
      this.editor.txt.html('<p>要初始化的内容</p>');
    },
    initEditorConfig(){  // 初始化编辑器配置
      this.editor.config.fontsizes = {  // 字号配置，增加14px
        1: '12px',
        2: '13px',
        3: '14px',
        4: '16px',
        5: '18px',
        6: '24px',
        7: '32px',
        8: '48px'
      };
      this.editor.config.showLinkImg=false

    },
  },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
