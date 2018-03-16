import { HOST } from './HOST'
import vue from 'vue'
export default function(ulr,accountNumber,verification,pwd){
  return new Promise(function(resolve,reject){
    vue.http.get(HOST + ulr+"?accountNumber="+accountNumber+"&verification="+verification+"&pwd="+pwd).then(
      function(res) {
        resolve(res.json())
      },
      function(err) {
        console.log(err);
        reject(err)
      }
    )
  })

}
