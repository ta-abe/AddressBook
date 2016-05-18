<%@ page contentType= "text/html;charset=UTF-8"%>
<html>
<head>
<title>
参照、削除
</title>
</head>
<body>
<form method ="POST" action ="AddressBookServlet">
<input type ="hidden" value="" name ="hidUuid">
ADR003<BR>
氏名<input type ="text" value ="" name ="lblName" readonly><BR>
かな<input type ="text" value ="" name ="lblKana" readonly><BR>
メールアドレス<input type ="text" value ="" name ="lblMailAddress1" readonly><BR>
<input type ="text" value ="" name ="lblMailAddress2" readonly><BR>
<input type ="text" value ="" name ="lblMailAddress3" readonly><BR>
電話番号<input type ="text" value ="" name ="lblPhoneNumber1" readonly><BR>
<input type ="text" value ="" name ="lblPhoneNumber2" readonly><BR>
<input type ="text" value ="" name ="lblPhoneNumber3" readonly><BR>
住所<input type ="text" value ="" name ="lblAddress" readonly><BR>
メモ<input type ="text" value ="" name ="lblMemo" readonly><BR>
<button type ="submit" value ="btnEdit" name ="btnEdit">修正</button>
<button type ="submit" value ="001Back" name ="btnBack">戻る</button>
<button type ="submit" value ="btndelete" name ="btnDelete">削除</button>
</form>
</body>

</html>