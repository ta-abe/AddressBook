<%@ page contentType = "text/html;charset=UTF-8"%>
<html>
  <head>
    <title>新規登録</title>
  </head>
  <body>
    <form method = "POST" action = "AddressBookServlet">
      氏名
      <input type = "text" value = "" name = "txtName" maxlength = "50" required style = "position : absolute ; left : 120px;"><BR><BR>
      かな
      <input type = "text" value = "" name = "txtKana" maxlength = "50" style = "position : absolute ; left : 120px;"><BR><BR>
      メールアドレス
      <input type = "text" value = "" name = "txtMailAddress1" pattern = "^([a-z0-9A-Z\.\-])+@([a-z0-9A-Z\.\-])+$" maxlength = "256" size = "50" style = "position : absolute ; left : 120px;"><BR><BR>
      <input type = "text" value = "" name = "txtMailAddress2" pattern = "^([a-z0-9A-Z\.\-])+@([a-z0-9A-Z\.\-])+$" maxlength = "256" size = "50" style = "position : absolute ; left : 120px;"><BR><BR>
      <input type = "text" value = "" name = "txtMailAddress3" pattern = "^([a-z0-9A-Z\.\-])+@([a-z0-9A-Z\.\-])+$" maxlength = "256" size = "50" style = "position : absolute ; left : 120px;"><BR><BR>
      電話番号
      <input type = "text" value = "" name = "txtPhoneNumber1" pattern = "\(?\d{2,4}\)?-?\(?\d{3,4}\)?-?\(?\d{3,4}\)?" maxlength = "20" style = "position : absolute ; left : 120px;"><BR><BR>
      <input type = "text" value = "" name = "txtPhoneNumber2" pattern = "\(?\d{2,4}\)?-?\(?\d{3,4}\)?-?\(?\d{3,4}\)?" maxlength = "20" style = "position : absolute ; left : 120px;"><BR><BR>
      <input type = "text" value = "" name = "txtPhoneNumber3" pattern = "\(?\d{2,4}\)?-?\(?\d{3,4}\)?-?\(?\d{3,4}\)?" maxlength = "20" style = "position : absolute ; left : 120px;"><BR><BR>
      住所
      <input type = "text" value = "" name = "txtAddress" maxlength = "200" size = "70" style = "position : absolute ; left : 120px;"><BR><BR>
      メモ
      <textarea name = "txtMemo" maxlength = "2000" cols = "60" rows = "5" style = "position : absolute ; left : 120px;"></textarea><BR><BR><BR><BR><BR><BR>
      <button type = "submit" name = "btnRegister" value = "btnRegister" style = "position : absolute ; left : 120px;" onclick = "return confirm('登録しますか？')">登録</button>
      <button type = "button" name = "btnBack" style = "position : absolute ; left : 170px;" onclick = "location.href = 'http://localhost:8080/address_book/ADR001.jsp'">戻る</button>
    </form>
  </body>
</html>