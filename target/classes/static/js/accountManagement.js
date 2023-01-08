/*
 * @Author: Anna 2838539481@qq.com
 * @Date: 2022-12-09 15:51:40
 * @LastEditors: Anna 2838539481@qq.com
 * @LastEditTime: 2022-12-16 13:44:52
 * @FilePath: \bankfront\js\accountManagement.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
$(function () {
  if(verify(0) == false) return
  // 获取所有用户
  var settings = {
    url: `${address}/salesman/account`,
    method: "GET",
    timeout: 0,
    headers: {
      token:
        sessionStorage.token,
    },
    success: function (response) {
      console.log(response);
      var data = response.data; 
      $("table").append(
        "<tr>" +
          "<th>账户(account)</th>" +
          "<th>储户ID(userId)</th>" +
          "<th>密码(password)</th>" +
          "<th>余额(balance)</th>" +
          "<th>电话号(phoneNumber)</th>" +
          "<th>冻结信息(freeze)</th>" +
          "<th>操作</th>" +
          "</tr>"
      );
      for (let i = 0; i < data.length; i++) {
        $("table").append("<tr id='apptr" + i + "'></tr>");
        $.each(data[i], function (index, domele) {
          $("#apptr" + i).append("<td>" + domele + "</td>");
        });
        if (data[i].freeze == 0) {
          $("#apptr" + i).append("<td><button id='btn"+i+"'>冻结</button></button></td>");
          
        } else {
          $("#apptr" + i).append("<td><button id='btn"+i+"'>解冻</button></button></td>");
        }
        $("#btn" + i).click(function(){
          let res = freeze(data[i].account)
          if(res == true) {
              data[i].freeze = data[i].freeze ^ 1;
              $("#apptr" + i).children('td').eq(5).text(data[i].freeze)
              if(data[i].freeze == 0)
                  $("#btn" + i).text("冻结")
              else 
                  $("#btn" + i).text("解冻")
              Toast.correct("成功")
          }
          else {
              Toast.warning("失败")
          }
        })
      }
    },

    error: function (response) {
      Toast.warning(response.msg);
    },
  };

  $.ajax(settings).done();
  // search事件
  $("img").click(function () {
    var account = $("input").val();
    $("table").html("");
    var settings = {
      url: `${address}/salesman/account/${account}`,
      method: "GET",
      timeout: 0,
      headers: {
        token:
          sessionStorage.token,
      },
      success: function (response) {
        console.log(response);
        var data = response.data;
        $("table").append(
          "<tr>" +
            "<td>账户(account)</td>" +
            "<td>储户ID(userId)</td>" +
            "<td>密码(password)</td>" +
            "<td>余额(balance)</td>" +
            "<td>电话号(phoneNumber)</td>" +
            "<td>冻结信息(freeze)</td>" +
            "<td>操作</td>"+
            "</tr>"
        );
        let i = 0;
        $("table").append("<tr id='apptr0'></tr>");
        $.each(data, function (index, domele) {
          $("#apptr" + i).append("<td>" + domele + "</td>");
        });
        if (data.freeze == 0) {
         $("#apptr" + i).append("<td><button id='btn"+i+"'>冻结</button></button></td>");
       } else {
         $("#apptr" + i).append("<td><button id='btn"+i+"'>解冻</button></button></td>");
       }
       $("#btn" + i).click(function(){
        let res = freeze(data.account)
        if(res == true) {
            data.freeze = data.freeze ^ 1;
            $("#apptr" + i).children('td').eq(5).text(data.freeze)
            if(data.freeze == 0)
                $("#btn" + i).text("冻结")
            else 
                $("#btn" + i).text("解冻")
            Toast.correct("成功")
        }
        else {
            Toast.warning("失败")
        }
      })
      },
      error: function (response) {
        Toast.warning(response.msg);
      },
    };
    $.ajax(settings).done();
  });
});
