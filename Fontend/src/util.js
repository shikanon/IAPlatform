export default{install(Vue,options){
  Vue.prototype.$topMenuToggle=function () {
      let string=this.$route.path;
      if(string.includes("/topMenu/item/")==true){
        $("#top_Menu").hide()
        $("#topMenuToggle").hide()
      }else{
        $("#top_Menu").show()
        $("#topMenuToggle").show()
      }

  };
  Vue.prototype.$getDate=function () {
    var date=new Date();
    let yy=date.getFullYear();
    let mm=date.getMonth()+1;
    let dd=date.getDate();
    let hh=date.getHours();
    let ms= date.getMinutes()<10?'0'+date.getMinutes():date.getMinutes();
    // let ms=date.getMinutes();
    let ss=date.getSeconds();
    let myday=date.getDay();
    return `${yy}-${mm}-${dd} ${hh}:${ms}:${ss}`
  },
  Vue.prototype.$GetDateStr=function(count) {
    var dd = new Date();
    var MM='';
    var DD=''
    dd.setDate(dd.getDate()+count);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m =dd.getMonth()+1;
    m<=9?MM="0"+m:MM=m;
    var d = dd.getDate();
    d<=9?DD="0"+d:DD=d;
    return y+"-"+MM+"-"+DD;
  };
  Vue.prototype.$getPath = function (obj,fileQuery,transImg) {
      var imgSrc = '', imgArr = [], strSrc = '' ;
      if(window.navigator.userAgent.indexOf("MSIE")>=1){
        if(obj.select){
          obj.select();
          var path=document.selection.createRange().text;
          alert(path) ;
          obj.removeAttribute("src");
          imgSrc = fileQuery.value ;
          imgArr = imgSrc.split('.') ;
          strSrc = imgArr[imgArr.length - 1].toLowerCase() ;
          if(strSrc.localeCompare('jpg') === 0 || strSrc.localeCompare('jpeg') === 0 || strSrc.localeCompare('gif') === 0 || strSrc.localeCompare('png') === 0){
            obj.setAttribute("src",transImg);
            obj.style.filter=
              "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+path+"', sizingMethod='scale');"; // IE通过滤镜的方式实现图片显示
          }else{
            throw new Error('File type Error! please image file upload..');
          }
        }else{
          imgSrc = fileQuery.value ;
          imgArr = imgSrc.split('.') ;
          strSrc = imgArr[imgArr.length - 1].toLowerCase() ;
          if(strSrc.localeCompare('jpg') === 0 || strSrc.localeCompare('jpeg') === 0 || strSrc.localeCompare('gif') === 0 || strSrc.localeCompare('png') === 0){
            obj.src = fileQuery.value ;
          }else{
            throw new Error('File type Error! please image file upload..') ;
          }

        }

      } else{
        var file =fileQuery.files[0];
        var reader = new FileReader();
        reader.onload = function(e){
          imgSrc = fileQuery.value ;
          imgArr = imgSrc.split('.') ;
          strSrc = imgArr[imgArr.length - 1].toLowerCase() ;
          if(strSrc.localeCompare('jpg') === 0 || strSrc.localeCompare('jpeg') === 0 || strSrc.localeCompare('gif') === 0 || strSrc.localeCompare('png') === 0){
            obj.setAttribute("src", e.target.result) ;
          }else{
            throw new Error('File type Error! please image file upload..') ;
          }
        };
        reader.readAsDataURL(file);
      }
    };
  Vue.prototype.$argumentPath=function (obj) {
    let inputOutList = obj.inputOut;
    let inputOutPath =[];
    let _that=this;
    let path='';
    if(!obj.file) {
      let commandLineSplit=obj.commandLine.split('--');
      let splitArr=commandLineSplit[1].split(/\s+/)[1].split(".");
      let idName=splitArr[splitArr.length-1];
      inputOutList.forEach(function (item, i) {
        let pathHead='${appPath}/';

        let idArr=obj.id.split("-");
        let lastId=idArr[idArr.length-1];

        let work_path=pathHead+idName+'-'+lastId+'-'+obj.domID.slice(15)+'/';
        let outsplit=item.split(":");
        path=work_path+escape(outsplit[outsplit.length-1]).replace(/%/g,"y" );
        inputOutPath.push(path)
      });
    }else{
      path=obj.file;
      inputOutPath.push(path)
    }


    return inputOutPath;
  }
  }
}
