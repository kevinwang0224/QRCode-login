<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="/static/common.css">
</head>
<body>
<div class="hello">
    <div style="width: 300px;">Hello , please sign in ...</div>
</div>
<div>
    <div class="qrcode">
        <img id="qrCodeImage" alt="scan this for sign in" src=""/>
    </div>
    <div class="error-text" style="display: none;">
        <div class="error-box"></div>
        <span class="text">二维码已失效</span>
        <a class="refresh" href="javascript:void(0);" onclick="qrcode.getQrCode()">刷新</a>
    </div>
</div>
<div class="confirm-box">
    <div class="confirm-login">
        <span id="user-name-text"></span>
        <span>扫描成功！</span><br>
        <span>点击确认登录</span>
    </div>
</div>
</body>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/static/qrcode-login.js"></script>
<script>
    $(function () {
        qrcode.getQrCode();
    });
</script>
</html>