<%@ page contentType= "text/html;charset=UTF-8"%>

<html>
<head>
<title>
アドレス帳
</title>
</head>
<body>
<h1>
アドレス一覧
</h1>
<form method ="POST" action="AddressBookServlet">
<button type="submit" value="新規登録"  name ="btnAdd">新規登録</button><br><br>
</form>
<span style="word-spacing: 110pt">氏名 かな メールアドレス 電話番号</span><BR>
<%for(int i= 0 ; i < 5 ; i++){%>
<form method = "POST" action ="AddressBookServlet">
<input type="text" value="NAME" readonly>
<input type="text" value="KANA" readonly >
<input type="text" value="ADDRESS" readonly >
<input type="text" value="PHONE_NUMBER" readonly >
<button type ="submit" value ="参照" name ="btnRefer">参照</button>
<input type="hidden" value="hiduuid" name ="hiduuid">
<BR>
</form>
<%} %>
</body>
</html>