// 测试获取
import vue from 'vue'
export default function(url,par){
  return new Promise(function(resolve,reject){
    vue.http.post(url,par).then(
      function(res) {
        resolve(res.json());
      },
      function(err) {
        console.log(err);
        reject(err);
      }
    )
  })
}
