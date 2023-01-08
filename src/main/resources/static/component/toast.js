var Toast = {
    show: function(message, time, backgroundColor) {
        /*设置信息框停留的默认时间*/
        if(time == undefined || time == '') {
            time = 1500;
        }
        var el = document.createElement("div");
        el.setAttribute("class", "web-toast");
        el.style.color = '#FFFFFF';
        el.innerHTML = message;
        if(backgroundColor != undefined) {
            el.style.background = backgroundColor
        }
        document.body.appendChild(el);
        el.classList.add("fadeIn");
        setTimeout(function () {
            el.classList.remove("fadeIn");
            el.classList.add("fadeOut");
            /*监听动画结束，移除提示信息元素*/
            el.addEventListener("animationend", function () {
                document.body.removeChild(el);
            });
            el.addEventListener("webkitAnimationEnd", function () {
                document.body.removeChild(el);
            });

        }, time);
    },
    correct: function(message, time) {
        this.show(message, time, "#00CC00")
    },
    warning: function(message, time) {
        this.show(message, time, '#FF6600')
    },
    error: function(message, time) {
        this.show(message, time, '#CC0000')
    }
}

var HttpRequest = {
    error: function(response) {
        Toast.error(response.statusText);
    },
    success: function(response) {
        if(response.code == 1) {
            Toast.correct(response.msg);
        }
        else {
            Toast.warning(response.msg);
        }
    }
}

function show_success(messsage) {
    Toast.correct(messsage);
}

function show_warning(messsage) {
    Toast.correct(messsage);
}

function show_error(messsage) {
    Toast.correct(messsage);
}

