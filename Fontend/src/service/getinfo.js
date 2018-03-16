// 测试获取
import { HOST } from './HOST'
import vue from 'vue'
vue.http.options.emulateJSON = true;
export default function(){
  return new Promise(function(resolve,reject){
    vue.http.post(HOST + 'validate/validate_code/').then(
      function(res) {
        resolve(res)
      },
      function(err) {
        console.log(err);
        reject(err)
      }
    )
  })

}
