<%@ page contentType = "text/html;charset=UTF-8"%>
<html>
  <head>
    <title>新規登録</title>
  </head>
  <body>
    <form method = "POST" action = "AddressBookServlet">
      ADR002<BR>
      氏名<input type = "text" value = "" name = "txtName" maxlength = "50" required style = "position : absolute ; left : 120px;"><BR><BR>
      かな<input type = "text" value = "" name = "txtKana" maxlength = "50" style = "position : absolute ; left : 120px;"><BR><BR>
      メールアドレス１<input type = "text" value = "" name = "txtMailAddress1" pattern = "^[0-9A-Za-z\@\-\.]+$" maxlength = "256" style = "position : absolute ; left : 120px;"><BR><BR>
      メールアドレス２<input type = "text" value = "" name = "txtMailAddress2" maxlength = "256" style = "position : absolute ; left : 120px;"><BR><BR>
      メールアドレス３<input type = "text" value = "" name = "txtMailAddress3" maxlength = "256" style = "position : absolute ; left : 120px;"><BR><BR>
      電話番号1<input type = "text" value = "" name = "txtPhoneNumber1" maxlength = "20" style = "position : absolute ; left : 120px;"><BR><BR>
      電話番号2<input type = "text" value = "" name = "txtPhoneNumber2" maxlength = "20" style = "position : absolute ; left : 120px;"><BR><BR>
      電話番号3<input type = "text" value = "" name = "txtPhoneNumber3" maxlength = "20" style = "position : absolute ; left : 120px;"><BR><BR>
      住所<input type = "text" value = "" name = "txtAddress" maxlength = "200" style = "position : absolute ; left : 120px;"><BR><BR>
      メモ<textarea name = "txtMemo" maxlength = "2000" style = "position : absolute ; left : 120px;"></textarea><BR><BR>
      <button type = "submit" name = "btnRegister" value = "btnRegister">登録</button>
      <button type = "button" name = "btnBack" onclick = "location.href = 'http://localhost:8080/address_book/ADR001.jsp'">戻る</button>
    </form>
  </body>
</html>