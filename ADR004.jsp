<%@ page contentType= "text/html;charset=UTF-8"%>
<html>
<head>
<title>
修正
</title>
</head>
<body>
<form method ="POST" action="AddressBookServlet">
<input type ="hidden" value="" name ="hidUuid">
ADR004<BR>
氏名<input type ="text" value ="" name ="txtName" ><BR>
かな<input type ="text" value ="" name ="txtKana" ><BR>
メールアドレス<input type ="text" value ="" name ="txtMailAddress1" ><BR>
<input type ="text" value ="" name ="txtMailAddress2" ><BR>
<input type ="text" value ="" name ="txtMailAddress3" ><BR>
電話番号<input type ="text" value ="" name ="txtPhoneNumber1" ><BR>
<input type ="text" value ="" name ="txtPhoneNumber2" ><BR>
<input type ="text" value ="" name ="txtPhoneNumber3" ><BR>
住所<input type ="text" value ="" name ="txtAddress" ><BR>
メモ<input type ="text" value ="" name ="txtMemo" ><BR>
<button type ="submit" value ="btnUpdate" name ="btnUpdate">更新</button>
<button type ="submit" value ="003Back" name ="btnBack">戻る</button>
</form>
</body>

</html>