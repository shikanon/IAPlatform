import { MessageBox } from 'element-ui';
import { HOST } from './HOST'
import vue from 'vue'
export default function(ulr,par){
  return new Promise(function(resolve,reject){
    vue.http.options.emulateJSON = true;
    vue.http.get(HOST + ulr, par).then(
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
