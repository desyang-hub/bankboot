$(function(){
    if(verify(0) == false) return;
    let token = sessionStorage.token
    var settings = {
        "url": `${address}/salesman/transact`,
        "method": "POST",
        "timeout": 0,
        "headers": {
            "token": token,
            "Content-Type": "application/json"
        },
        success:function(response){
            if(response.code==0) {
                Toast.warning(response.msg)
                return ;
            }
            else {
                var data =response.data
                $("#transact").append(
                    "<tr>" +"<td >交易ID(tradId)</td>" +"<td>账号(account)</td>"+'<td >机器码(machine)</td>'+'<td >交易类型(tradType)</td>'+'<td >金额(balance)</td>' +'<td >交易时间(tradingTime)</td>'+"</tr>"
                );
                for(var i=0; i<data.length; i++){
                        $("#transact").append(
                            "<tr id='apptr"+i+"'></tr>"			
                        );
                        var lists = data[i];
                        var str = JSON.stringify(lists);  //将返回的data值[object object]转化
                        //alert(str);
                        $.each(JSON.parse(str),function(index,domele){
                            //alert(index);
                            //index为属性名，domele为user的属性值
                            $("#apptr"+i).append(
                                "<td>"+domele+"</td>"	
                            );
                        });
    
                    }			  
            }
        },
        error: function(response) {
            HttpRequest.error(response)
        }
    }
    $.ajax(settings).done(function (response) {
            console.log(response);
    });
})

function mySerch(){
    let token = sessionStorage.token
    var tem =document.getElementById("number").value;
    var tal= document.getElementById("transact");
    $("#transact").html("");
    if(tem==0){
        $(document).ready(function(){
        var settings = {
            "url": `${address}/salesman/transact`,
            "method": "POST",
            "timeout": 0,
            "headers": {
                "token": token,
                "Content-Type": "application/json"
            },
            
            success:function(response){
                if(response.code==0) {
                    Toast.warning(response.msg)
                    return ;
                }
                else {
                    var data =response.data
                    $("#transact").append(
                        "<tr>" +"<td >交易ID(tradId)</td>" +"<td>账号(account)</td>"+'<td >机器码(machine)</td>'+'<td >交易类型(tradType)</td>'+'<td >金额(balance)</td>' +'<td >交易时间(tradingTime)</td>'+"</tr>"
                    );
                    for(var i=0; i<data.length; i++){
                            $("#transact").append(
                                "<tr id='apptr"+i+"'></tr>"			
                            );
                            var lists = data[i];
                            var str = JSON.stringify(lists);  //将返回的data值[object object]转化
                            //alert(str);
                            $.each(JSON.parse(str),function(index,domele){
                                //alert(index);
                                //index为属性名，domele为user的属性值
                                $("#apptr"+i).append(
                                    "<td>"+domele+"</td>"	
                                );
                            });
        
                    }			  
                }
            },
            error: function(response) {
                HttpRequest.error(response)
            }
        }
        $.ajax(settings).done(function (response) {
                console.log(response);
        });
    })
    }
    else {
    $(document).ready(function(){
    var settings = {
        "url": `${address}/salesman/transact`,
        "method": "POST",
        "timeout": 0,
        "headers": {
            "token": token,
            "Content-Type": "application/json"
        },
        
        success:function(response){
            if(response.code==0) {
                Toast.warning(response.msg)
                return ;
            }
            else {
                var data =response.data
                $("#transact").append(
                    "<tr>" +"<td >交易ID(tradId)</td>" +"<td>账号(account)</td>"+'<td >机器码(machine)</td>'+'<td >交易类型(tradType)</td>'+'<td >金额(balance)</td>' +'<td >交易时间(tradingTime)</td>'+"</tr>"
                );
                for(var i=0 ;i<data.length;i++){
                    if(tem==data[i].account){
                        $("#transact").append(
                            "<tr id='apptr"+i+"'></tr>"			
                        );
                        var lists = data[i];
                        var str = JSON.stringify(lists);  //将返回的data值[object object]转化
                        //alert(str);
                        $.each(JSON.parse(str),function(index,domele){
                            //alert(index);
                            //index为属性名，domele为user的属性值
                            $("#apptr"+i).append(
                                "<td>"+domele+"</td>"	
                            );
                        });
                    }
                }
                }
            },
            error: function(response) {
                HttpRequest.error(response)
            }
        }
    $.ajax(settings).done(function (response) {
            console.log(response);
    });
})
}
}