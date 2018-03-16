<template>
  <div id="titles">
    <div class="back">
      <span v-text="titleContent"></span>
    </div>
  </div>

</template>

<script>
    export default {
      name: 'titles',
      data () {
        return {

        }
      },
      props: ['titleContent'],
      methods:{

      }
    }
</script>

<style scoped>
  #titles .back{
    height:62px;
    line-height: 52px;
    position: relative;
    font-size: 16px;
    color:#fff;
    text-align: center;
    padding:0;
    margin-top:8px;
    background: url(../assets/img/image/head_bg1.png) transparent no-repeat center center;
    background-size: cover;
  }
  /*#titles .leftBarBtn {
    position: absolute;
    top: 0;
    left: 10px;
    background: transparent;
    border: 0;
    padding: 5px 10px;
    outline: none;
  }*/
</style>
