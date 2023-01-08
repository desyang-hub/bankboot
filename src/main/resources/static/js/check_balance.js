$(function(){
    if(verify(1) == false) return;
    jQuery.extend({ // Jquery 导出函数
    getBlance: function(){
        let balance;
          let settings = {
            "url": `${address}/account`,
            "method": "POST",
            "timeout": 0,
            "headers": {
            "token": sessionStorage.token,
            },
            async: false,
            success: function(response) {
                if(response.code == 1) {
                    Toast.correct(response.msg)
                    balance = response.data
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
        return balance;
       }
    })

    let balance = $.getBlance();
    $("#balance").text(balance)
 })
 