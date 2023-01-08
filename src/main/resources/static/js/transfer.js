$(function(){
    if(verify(1) == false) return;
    let selVal;
    let money = 0;
    let target
    jQuery.extend({ // Jquery 导出函数
    transfer: function(target, money){
          var settings = {
            "url": `${address}/account`,
            "method": "PUT",
            "timeout": 0,
            "headers": {
               "token": sessionStorage.token,
               "Content-Type": "application/json"
            },
            "data": JSON.stringify({
               "targetAccount": target,
               "balance": money,
               "machine": localStorage.machine
            }),
            success: function(response) {
                if(response.code == 1) {
                    Toast.correct(response.msg)
                }
                else {
                    Toast.warning(response.msg)
                }
            },
            error: function(response) {
                Transt.error("请求错误")
            }
         };
        $.ajax(settings).done()
       }
    }
   ) 
    $(".submit").click(function(){
        selVal = $("#sel").val();
        target = $("#target").val();
        if(selVal=="100"){
            money = 100;
        }
        else if(selVal=="200"){
            money = 200;
        }
        else if(selVal=="500"){
            money = 500;
        }
        else if(selVal=="1000"){
            money = 1000;
        }
        else if(selVal=="2000"){
            money = 2000;
        }
        if(target == '') {
            Toast.warning("目标账户异常")

        }
        else {
            $.transfer(target, money)
        }
    })
   
 })
 