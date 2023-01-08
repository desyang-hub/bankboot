/*
 * @Author: Anna 2838539481@qq.com
 * @Date: 2022-12-07 08:34:30
 * @LastEditors: Anna 2838539481@qq.com
 * @LastEditTime: 2022-12-09 16:04:18
 * @FilePath: \bankfront\js\saveMoney.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
$(function(){
   verify(1)
    let token = sessionStorage.token
    var selVal;
    var money = 0;
    jQuery.extend({
    'set':function(money){
          var data = {
                "machine": localStorage.machine,
                "balance": money
            }
          var settings = {
             "url": `${address}/account/out`,
             "method": "POST",
             "timeout": 0,
             "headers": {
                "token": token,
               "Content-Type": "application/json"
             },
             "data": JSON.stringify(data),
             success: function (response) {
                 HttpRequest.success(response);
              },
              error: function (response) {
                HttpRequest.error(response);
              }
          };
          
          $.ajax(settings).done()
       }
    }
   ) 
    $(".submit").click(function(){
      selVal = $("#sel").val()
      console.log(selVal)
       if(selVal=="100"){
          money = 100;
          $.set(money);
       }
       else if(selVal=="200"){
          money = 200;
          $.set(money);
       }
       else if(selVal=="500"){
          money = 500;
          $.set(money);
       }
       else if(selVal=="1000"){
          money = 1000;
          $.set(money);
       }
       else if(selVal=="2000"){
          money = 2000;
          $.set(money);
       }
    })
   
 })
 