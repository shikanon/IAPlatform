<template>
  <div class="rightMenuBar labModel">
    <form action="#">
      <ul>
        <li>
          <span>项目名称</span>
          <el-input v-model="ruleForm.jobName" placeholder="请输入"></el-input>
        </li>
        <li>
          <span>项目所有者</span>
          <el-input v-model="ruleForm.account" placeholder="请输入"></el-input>

        </li>
        <li>
          <span>项目ID</span>
          <el-input v-model="ruleForm.jobId" placeholder="请输入"></el-input>
        </li>
        <li>
          <span>项目进度</span>
          <el-input v-model="ruleForm.itemProgress" placeholder="请输入"></el-input>
        </li>
        <li>
          <span>开始时间</span>
          <el-date-picker v-model="ruleForm.stratTime" type="date" placeholder="选择日期" :picker-options="pickerOptions0"></el-date-picker>
        </li>
        <!--
        <li>
          <span>结束时间</span>
          <el-date-picker v-model="ruleForm.endTime" type="date" placeholder="选择日期" :picker-options="pickerOptions0"></el-date-picker>
        </li>
        <li>
          <span>使用时间</span>
          <el-date-picker v-model="ruleForm.useTime" type="date" placeholder="选择日期" :picker-options="pickerOptions0"></el-date-picker>
        </li>
        -->
        <li>
          <span>描述</span>
          <el-input type="textarea" v-model="ruleForm.description" placeholder="请输入"></el-input>
        </li>
      </ul>
    </form>
      <!--v-show="i==nodeInfoList.length-1"-->
    <!-- <nodeMask  :nodeInfo="nodeInfo"></nodeMask>-->

  </div>
</template>

<script>
  import nodeMask from './nodeMask.vue';
    export default {
      name:'labModel',
      data(){
        return{
          pickerOptions0: {
            disabledDate(time) {
              return time.getTime() < Date.now() - 8.64e7;
            }
          }

        }
      },
      mounted:function () {

      },
      components: {
        nodeMask
      },
      props:['ruleForm','nodeInfo']
    }
</script>

<style scoped>

</style>
