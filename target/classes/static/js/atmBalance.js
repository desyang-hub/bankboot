$(function(){
    if(verify(0) == false) return
    $("#ok").click(function() {
        var selectValue = $("#sel").val();
        var money = $("#money").val();
        if(money == 0 || money % 100 != 0){
            Toast.warning("金额异常");
        }
        else{
            /**
             * 加钞
             */
            var data = {
                "machine": localStorage.machine,
                "balance": money
            }
            let token = sessionStorage.token
            if(selectValue=="1"){
                var settings = {
                    "url": `${address}/salesman/in`,
                    "method": "POST",
                    "timeout": 0,
                    "headers": {
                        "token": token,
                        "Content-Type": "application/json"
                    },
                    "data": JSON.stringify(data),
                    error: function (response) {
                        HttpRequest.error(response)
                    },
                    success: function (response) {
                        HttpRequest.success(response);
                    },
                };
    
                $.ajax(settings).done();
            }
            /**
             * 减钞
             */
            if(selectValue=="2"){
                var settings = {
                    "url": `${address}/salesman/out`,
                    "method": "POST",
                    "timeout": 0,
                    "headers": {
                        "token": token,
                        "Content-Type": "application/json"
                    },
                    "data": JSON.stringify(data),
                    error: function (response) {
                        HttpRequest.error(response);
                    },
                    success: function (response) {
                        HttpRequest.success(response);
                    },
                };
    
                $.ajax(settings).done();
            }
        }
    })
  });