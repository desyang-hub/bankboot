/*
 * @Author: Anna 2838539481@qq.com
 * @Date: 2022-12-06 10:12:46
 * @LastEditors: Anna 2838539481@qq.com
 * @LastEditTime: 2022-12-09 15:03:47
 * @FilePath: \bankfront\js\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
var flag = 0;
$(function () {
  if(localStorage.machine == undefined) {
    jump('/html/start_server.html');
    return;
  }
  
  /**
   * 点击注销按钮
   */
  $("#imgBtn").click(function() {
      localStorage.removeItem('machine');
      jump('/html/start_server.html');
  })

  $(".chose2").click(function () {
    flag = 1;
    $("#right").show();
    $("#left").hide();
    $("#choose1").css("backgroundColor", "rgba(0, 0, 0, 0.671)");
    $("#choose1").css("color", "aliceblue");
    $("#choose2").css("backgroundColor", "rgb(225, 228, 231, 0.699)");
    $("#choose2").css("color", "black");
  });
  $(".chose1").click(function () {
    flag = 0;
    $("#left").show();
    $("#right").hide();
    $("#choose2").css("backgroundColor", "rgba(0, 0, 0, 0.671)");
    $("#choose2").css("color", "aliceblue");
    $("#choose1").css("backgroundColor", "rgb(225, 228, 231, 0.699)");
    $("#choose1").css("color", "black");
  });
});

$(function () {
  let menu_user = '/html/menu_user.html'
  let menu_salesman = '/html/menu_salesman.html'

  

  $("#submit").click(function () {
    if (flag == 0) {
      var account = $("#depositor").val();
      var password = $("#uPass").val();
    } else {
      var account = $("#salesperson").val();
      var password = $("#sPass").val();
    }
    
    let setting_user = {
      url: `${address}/account?account=${account}&password=${password}`,
      method: "GET",
      timeout: 0,
      success: function (response) {
        if(response.code == 1) {
          Toast.correct(response.msg)
          sessionStorage.setItem("token", response.data);
          console.log("user sessionStorage`s token：" + sessionStorage.getItem("token"));
          setTimeout(function(){
            jump(menu_user)
          }, 1000)
        }
        else {
          Toast.warning(response.msg)
        }
      },
      error: function (response) {
        Toast.error('请求失败')
      }
    };

    let data = {
        "jobNo": account,
        "password": password
    }

    let setting_salesman = {
      "url": `${address}/salesman`,
      "method": "GET",
      "timeout": 0,
      "headers": {
         "Content-Type": "application/json"
      },
      "data": data,
      success: function(response) {
        if(response.code == 1) {
          Toast.correct(response.msg)
          sessionStorage.setItem("token", response.data);
          console.log("salesman sessionStorage`s token：" + sessionStorage.getItem("token"));
          setTimeout(function(){
            jump(menu_salesman)
          }, 1000)
          
        }
        else {
          Toast.warning(response.msg)
        }
      },
      error: function(response) {
        Toast.error('请求失败')
      }
   };
   
   if(flag == 0) {
      $.ajax(setting_user).done()
   }
   else {
    $.ajax(setting_salesman).done()
   }
  });
});
