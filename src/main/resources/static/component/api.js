function jump(url) { // 提供url:相对路径
    let address = 'http://192.168.33.128:8081'
    url = address + url;
    console.log(url)
    $(location).attr('href',url)
}

/**
 * type: 0 salesman, type: 1 user 
 * @param {Integer} type 
 * @returns 
 */
function verify(type) {
    if(sessionStorage.token == undefined) {
        jump("/index.html");
        return false
    }
    let result = true;
    let addr = "salesman";
    if(type == 1) {
        addr = "account"
    }
    let settings = {
        async: false,
        "url": `${address}/${addr}/verify`,
        "method": "GET",
        "timeout": 0,
        "headers": {
            "token": sessionStorage.token
        },
        success: function(response) {
            if(response.code == 0) {
                jump("/index.html");
                result = false;
            }
        },
        error: function(response) {
            show_error(response)
        }
    };
    $.ajax(settings).done();
    return result;
}

function freeze(account) {
    if(sessionStorage.token == undefined) {
        jump("/index.html");
        return false;
    }

    let res = true;
    var settings = {
        async: false,
        "url": `${address}/salesman/account?account=${account}`,
        "method": "POST",
        "timeout": 0,
        "headers": {
           "token": sessionStorage.token
        },
        success: function(response) {
            if(response.code == 0) {
                res = false;
            }
        },
        error: function() {
            res = false;
        }
     };
     
     $.ajax(settings).done();
     return res;
}
