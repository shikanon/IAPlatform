// 测试获取
import { MessageBox } from 'element-ui';
import { HOST } from './HOST'
import vue from 'vue'

export default function(url,par,aa){
  return new Promise(function(resolve,reject){
    vue.http.options.emulateJSON = true;
    vue.http.post(HOST + url,par,aa).then(
      function(res) {
        resolve(res.json())
      },
      function(err) {
        console.log(err);
        if(err.status==500){
          MessageBox.alert( '请求失败请检查网络','错误');
        }
        reject(err)
      }
    )
  })
}
