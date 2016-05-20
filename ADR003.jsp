<%@ page contentType= "text/html;charset=UTF-8"%>
<html>
  <head>
    <title>
      参照、削除
    </title>
  </head>
  <body>
    <form method ="POST" action ="AddressBookServlet">
      <input type ="hidden" value="<%=request.getAttribute("UUID")%>" name ="hidUuid">
      ADR003<BR>
      氏名
      <label name ="lblName" style = "position : absolute ; left : 120px;"><%=request.getAttribute("NAME")%></label><BR>
      かな
      <label name ="lblKana" style = "position : absolute ; left : 120px;"><%=request.getAttribute("KANA")%></label><BR>
      メールアドレス
      <%Object size = request.getAttribute("mailsize");
        int j = Integer.parseInt(size.toString());
        for(int i = 0; i < j ; i++){ %>
          <label name ="lblMailAddress" style = "position : absolute ; left : 120px;"><%=request.getAttribute("MAIL" + i)%></label><BR>
      <%}%>
      電話番号
      <%Object psize = request.getAttribute("mailsize");
        j = Integer.parseInt(psize.toString());
        for(int i = 0; i < j ; i++){ %>
          <label name ="lblPhoneNumber" style = "position : absolute ; left : 120px;"><%=request.getAttribute("PHONE" + i)%></label><BR>
      <%}%>
      住所
      <label name ="lblAddress" style = "position : absolute ; left : 120px;"><%=request.getAttribute("ADDRESS")%></label><BR>
      メモ
      <label name ="lblMemo" style = "position : absolute ; left : 120px;"><%=request.getAttribute("MEMO")%></label><BR><BR>
      <button type ="submit" value ="btnEdit" name ="btnEdit">修正</button>
      <button type ="submit" value ="001Back" name ="btnBack">戻る</button>
      <button type ="submit" value ="btndelete" name ="btnDelete">削除</button>
    </form>
  </body>
</html>