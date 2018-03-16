<template>
  <div class="charts" ref="chart"></div>
</template>

<script>
  // 图表组件
  import echarts from 'echarts'
  export default {
    name: 'charts',
    props: {
      option: { //配置
        type: Object,
        default: function() {
          return {
            title: {
              text: 'ECharts 入门示例'
            },
            tooltip: {},
            xAxis: {
              data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
            },
            yAxis: {},
            series: [{
              name: '销量',
              type: 'bar',
              data: [5, 20, 36, 10, 10, 20]
            }]
          }
        }
      }
    },
    data() {
      return {
        name: 'chart',
        chartoption: {},
        screenWidth:'',
        myChart:null
      }
    },
    watch: {
      option: function(value) {
        let _that=this;
        if (this.myChart != null && this.myChart != "" && this.myChart != undefined) {
          this.myChart.dispose();
        }
        if(this.$refs.chart.id=='nodeChart'){
          this.$refs.chart.style.height='500px';
          this.$refs.chart.style.width='900px';
        }
        this.$refs.chart.style.display="block";
        this.myChart = echarts.init(this.$refs.chart);
        this.myChart.setOption(value, true);
        setTimeout(function (){
          window.onresize = function () {
            _that.myChart.resize()
          }
        },500);
      }

    },
    methods: {


    },
    mounted:function(){
    },
    updated() {
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .charts {
    width: 100%;
    height: 100%;
  }
</style>
