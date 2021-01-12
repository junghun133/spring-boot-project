$(document).ready(function() {
    $('#login-btn').eq(0).bind("click", function() {
        var loginInfo = new Object();
        loginInfo.id = $('#id-input').val();
        loginInfo.password = $('#password-input').val();
        if(loginInfo.id == '' || loginInfo.password == ''){
            alert('아이디와 비밀번호를 입력하세요');
            return;
        }

        var jsonData = JSON.stringify(loginInfo);
        $.ajax({
            method : 'POST',
            contentType :'application/json',
            url : 'http://127.0.0.1:8080/v1/user/login',
            data : jsonData,

            success : function(data) {
                // data는 서버로부터 전송받은 결과(JSON)이므로 바로 사용한다
                if (data.message == '성공') {
                    alert(data.name + '님 환영합니다.');
                    location.href = "http://localhost:8080/index.html?apiKey=" + data.apiKey;
                } else if (data.answer == '실패') {
                    alert('아이디와 비밀번호가 일치하지 않습니다.');
                }
            },
            error : function(e) {
                alert('서버 연결 도중 에러가 발생하였습니다. 다시 시도해 주십시오.');
            }
        });
    });
});