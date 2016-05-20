<%@ page contentType= "text/html;charset=UTF-8"%>
<html>
  <head>
    <title>新規登録</title>
  </head>
  <body>
    <form method ="POST" action = "AddressBookServlet">
      ADR002<BR>
      氏名<input type="text" value ="" name ="txtName"><BR>
      かな<input type="text" value ="" name ="txtKana"><BR>
      メールアドレス１<input type="text" value = "" name ="txtMailAddress1"><BR>
      メールアドレス２<input type="text" value = "" name ="txtMailAddress2"><BR>
      メールアドレス３<input type="text" value = "" name ="txtMailAddress3"><BR>
      電話番号1<input type ="text" value = "" name ="txtPhoneNumber1"><BR>
      電話番号2<input type ="text" value = "" name ="txtPhoneNumber2"><BR>
      電話番号3<input type ="text" value = "" name ="txtPhoneNumber3"><BR>
      住所<input type ="text" value = "" name ="txtAddress"><BR>
      メモ<textarea name ="txtMemo" ></textarea><BR>
      <button type ="submit" name = "btnRegister" value = "btnRegister">登録</button>
      <button type ="submit" name = "btnBack" value = "001Back">戻る</button>
    </form>
  </body>
</html>