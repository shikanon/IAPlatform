<template>
  <div class="operation">
    <ul>
      <li><button href="javascript:;" class="blue-button"  id="startIdClick"
                  @click="startClick()"
                  :disabled="btnStatus.startBtn">运行</button></li>
      <li><button href="javascript:;" class="red-button"
                  @click="clearClick()"
                  :disabled="btnStatus.clearBtn"
      >清除</button></li>
      <li><button href="javascript:;" class="blue-button"
                  @click="cloneClick()"
                  :disabled="btnStatus.cloneBtn"
      >复制</button></li>
      <li><button href="javascript:;" class="red-button"
                  :disabled="btnStatus.stopBtn"
                  @click="StopClick()"
      >停止</button></li>
      <li><button href="javascript:;" class="green-button"
                  :disabled="btnStatus.refreshBtn"
                  @click="refreshClick()"
      >刷新</button></li>
    </ul>
  </div>
</template>

<script>
    export default {
        name:"operation",
        data(){
          return{

          }
        },
        props:['btnStatus'],
        methods:{
          startClick:function () {
            this.$emit('startClick')
          },
          clearClick:function () {
            this.$emit('clearClick')
          },
          refreshClick:function () {
            this.$emit('refreshClick')
          },
          cloneClick:function () {
            this.$emit('cloneClick')
          },
          StopClick:function () {
            this.$emit('stopClick')
          },
        }
    }
</script>

<style scoped>

</style>
