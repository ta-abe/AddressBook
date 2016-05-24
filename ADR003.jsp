<%@ page contentType = "text/html;charset=UTF-8"%>
<html>
  <head>
    <title>
      参照、削除
    </title>
  </head>
  <body>
    <form method = "POST" action = "AddressBookServlet">
      <input type = "hidden" value = "<%=request.getAttribute("UUID")%>" name = "hidUuid">
      氏名
      <label name = "lblName" style = "position : absolute ; left : 120px;"><%=request.getAttribute("NAME")%></label><BR><BR>
      かな
      <label name = "lblKana" style = "position : absolute ; left : 120px;"><%=request.getAttribute("KANA")%></label><BR><BR>
      メールアドレス
      <%Object size = request.getAttribute("mailsize");
        int j = Integer.parseInt(size.toString());
        for(int i = 0; i < j ; i++){ %>
          <label name = "lblMailAddress" style = "position : absolute ; left : 120px;"><%=request.getAttribute("MAIL" + i)%></label><BR><BR>
      <%}%>
      電話番号
      <%Object psize = request.getAttribute("phonesize");
        int k = Integer.parseInt(psize.toString());
        for(int l = 0; l < k ; l++){ %>
          <label name = "lblPhoneNumber" style = "position : absolute ; left : 120px;"><%=request.getAttribute("PHONE" + l)%></label><BR><BR>
      <%}%>
      住所
      <label name = "lblAddress" style = "position : absolute ; left : 120px;"><%=request.getAttribute("ADDRESS")%></label><BR><BR>
      メモ
      <label name = "lblMemo" style = "position : absolute ; left : 120px;"><%=request.getAttribute("MEMO")%></label><BR><BR><BR>
      <button type = "submit" value = "btnEdit" name = "btnEdit" style = "position : absolute ; left : 120px;">修正</button>
      <button type = "button" name = "btnBack" style = "position : absolute ; left : 170px;" onclick = "location.href = 'http://localhost:8080/address_book/ADR001.jsp'">戻る</button>
      <button type = "submit" value = "btndelete" name = "btnDelete" onclick = "return confirm('削除しますか？')" style = "position : absolute ; left : 300px;">削除</button>
    </form>
  </body>
</html>