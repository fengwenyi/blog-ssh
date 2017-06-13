$(function() {
	/*$('#submit').on('click', function() {
		var token = $('#token').val();
		var sign = "xfsy";
		var d = new Date();
		var pwd = sign + formatDate(d);
		if (token == pwd) {
			alert('测试中···感谢您的参与！');
		} else {
			alert('验证失败，请向管理员索要~');
		}
	});*/

	// 异步登录
    $('#submit').on('click', function() {
        $('#tips').show(1000);
        $('#bg').show();
        $.ajax({
            url:'/login-impl',
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:{
				token: $('#token').val()
            },
            timeout:5000,    //超时时间
            dataType:'text',    //返回的数据格式：json/xml/html/script/jsonp/text
            beforeSend:function(xhr){
                $('#tip').html('正在验证，请稍后···');
            },
            success:function(data, textStatus, jqXHR){
                if (data == 1) {
                    $('#tip').html('登录成功');
                }
                if (data == 0) {
                    $('#tip').html('token错误');
                }
            },
            error:function(xhr, textStatus){
                $('#tips').html('网络异常···');
            },
            complete:function(){
                console.log('结束')
                //倒计时
                $(document).ready(function() {
                    function jump(count) {
                        window.setTimeout(function(){
                            count--;
                            if(count >= 0) {
                                $('#tips-num').html(count);
                                jump(count);
                            } else {
                                location.href="/admin/index"; //跳转
                                $('#tips').hide();
                                $('#bg').hide(1000);
                            }
                        }, 1000);
                    }
                    jump(4);
                });
            }
        })
    });
});
/*js 也可以写密码验证哦！*/
function formatDate(now) {
　　var year = now.getFullYear(),
　　month = now.getMonth() + 1,
　　date = now.getDate(),
　　hour = now.getHours(),
　　minute = now.getMinutes();
　　return year + "" + month + "" + date + "" + hour + "" + minute;
}