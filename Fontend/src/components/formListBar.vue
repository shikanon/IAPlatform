<template>
  <div class="formListBar">
    <button>创建工作流</button>
    <button @click="uploadProgramClick">上传算法</button>
    <button  @click="uploadDataClick">上传数据</button>
    <creatJob v-show="false"></creatJob>
    <uploadProgram :uploadProg="uploadProg" @uploadFileClick="uploadFileClick"></uploadProgram>
    <uploadData :uploadData="uploadData" @uploadFileClick="uploadFileClick"></uploadData>
  </div>
</template>

<script>
  import creatJob from '../components/creatJob.vue'
  import uploadProgram from '../components/uploadProgram.vue'
  import uploadData from '../components/uploadData.vue'
export default {
  name: 'formListBar',
  data () {
    return {
      uploadProg:{
        uploadProgToggle:false,
        uploadProgCategory:[]
      },
      uploadData:{
        uploadDataToggle:false,
        uploadDataCategory:[]
      }
    }
  },
  components: {
    creatJob,uploadProgram,uploadData
  },
  watch:{
    'type':{
      handler:function (val) {
          let _that=this;
          _that.uploadProg.uploadProgCategory=val.progType;
          _that.uploadData.uploadDataCategory=val.dataType
      },
      deep:true
    }
  },
  mounted:function () {

  },
  beforeMount: function () {

  },
  props:['type'],
  methods: {
    uploadProgramClick:function () {
        let _that=this;
        _that.uploadProg.uploadProgToggle=true;
    },
    uploadFileClick:function () {
      this.$emit('uploadFileClick')
    },
    uploadDataClick:function () {
      let _that=this;
      _that.uploadData.uploadDataToggle=true;
    }
  }
}
</script>

<style scoped>
.formListBar{
  background: #e1e9f2;
  height:30px;
  position: relative;
  z-index: 100;
  line-height: 30px;
}
.formListBar button{
  margin: 0 15px;
  border:0;
  background: transparent;
  outline: none;
}
.formListBar button:hover{
  color:#509ff0;
}
.formListBar .barMask{
  position:fixed;
  left:0;
  top:0;
  width:100%;
  height:100%;
  background: rgba(0,0,0,0.7);

}

</style>
