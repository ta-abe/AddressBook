<%@ page contentType = "text/html;charset=UTF-8"%>
<html>
  <head>
    <title>
      修正
    </title>
  </head>
  <body>
    <form method = "POST" action = "AddressBookServlet">
      <input type = "hidden" value = "<%=request.getAttribute("UUID")%>" name = "hidUuid">
      ADR004<BR>
      氏名
      <input type = "text" value = "<%=request.getAttribute("NAME")%>" name = "txtName" style = "position : absolute ; left : 120px;"><BR><BR>
      かな
      <input type = "text" value = "<%=request.getAttribute("KANA")%>" name = "txtKana" style = "position : absolute ; left : 120px;"><BR><BR>
      メールアドレス
      <%
        Object size = request.getAttribute("mailsize");
        int s = Integer.parseInt(size.toString());
        for(int i = 0 ; i < s  + 1; i++){
      %>
      <input type = "text" value = "<%=request.getAttribute("MAIL" + i)%>" name = "txtMailAddress<%= + i %>" style = "position : absolute ; left : 120px;"><BR><BR>
      <%} %>
      電話番号
      <%
        Object phonesize = request.getAttribute("phonesize");
        int t = Integer.parseInt(phonesize.toString());
        for(int i = 0 ; i < t + 1; i++){
      %>
      <input type = "text" value = "<%=request.getAttribute("PHONE" + i)%>" name = "txtPhoneNumber<%= + i %>" style = "position : absolute ; left : 120px;"><BR><BR>
      <%} %>
      住所
      <input type = "text" value = "<%=request.getAttribute("ADDRESS")%>" name = "txtAddress" style = "position : absolute ; left : 120px;"><BR><BR>
      メモ
      <textarea name = "txtMemo" style = "position : absolute ; left : 120px;"><%=request.getAttribute("MEMO")%></textarea><BR><BR>
      <button type = "submit" value = "btnUpdate" name = "btnUpdate">更新</button>
      <button type = "submit" value = "003Back" name = "btnBack">戻る</button>
    </form>
  </body>
</html>