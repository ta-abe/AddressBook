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
      氏名
      <input type = "text" value = "<%=request.getAttribute("NAME")%>" name = "txtName" maxlength = "50" required style = "position : absolute ; left : 120px;"><BR><BR>
      かな
      <input type = "text" value = "<%=request.getAttribute("KANA")%>" name = "txtKana" maxlength = "50" style = "position : absolute ; left : 120px;"><BR><BR>
      メールアドレス
      <%
        Object size = request.getAttribute("mailsize");
        int s = Integer.parseInt(size.toString());
        for(int i = 0 ; i < s; i++){
      %>
      <input type = "text" value = "<%=request.getAttribute("MAIL" + i)%>" name = "txtMailAddress<%= + i %>" pattern = "^([a-z0-9A-Z\.\-])+@([a-z0-9A-Z\.\-])+$" maxlength = "256" size = "50" style = "position : absolute ; left : 120px;"><BR><BR>
      <%} %>
      <input type = "text" value = "" name = "txtMailAddress<%= + s %>" pattern = "^([a-z0-9A-Z\.\-])+@([a-z0-9A-Z\.\-])+$" maxlength = "256" size = "50" style = "position : absolute ; left : 120px;"><BR><BR>
      電話番号
      <%
        Object phonesize = request.getAttribute("phonesize");
        int t = Integer.parseInt(phonesize.toString());
        for(int i = 0 ; i < t; i++){
      %>
      <input type = "text" value = "<%=request.getAttribute("PHONE" + i)%>" name = "txtPhoneNumber<%= + i %>" pattern = "[0-9\(\)\-]+$" maxlength = "20" style = "position : absolute ; left : 120px;"><BR><BR>
      <%} %>
      <input type = "text" value = "" name = "txtPhoneNumber<%= + t %>" pattern = "[0-9\(\)\-]+$" maxlength = "20" style = "position : absolute ; left : 120px;"><BR><BR>
      住所
      <input type = "text" value = "<%=request.getAttribute("ADDRESS")%>" name = "txtAddress" maxlength = "200"  size = "70" style = "position : absolute ; left : 120px;"><BR><BR>
      メモ
      <textarea name = "txtMemo" maxlength = "200" cols = "60" rows = "5" style = "position : absolute ; left : 120px;"><%=request.getAttribute("MEMO")%></textarea><BR><BR><BR><BR><BR>
      <button type = "submit" value = "btnUpdate" name = "btnUpdate" style = "position : absolute ; left : 120px;" onClick="return confirm('更新しますか？')">更新</button>
      <button type = "button" value = "003Back" name = "btnBack" style = "position : absolute ; left : 170px;" onclick = "location.href = 'http://localhost:8080/address_book/AddressBookServlet?hidUuid=<%=request.getAttribute("UUID")%>&btnBack=003Back'">戻る</button>
    </form>
  </body>
</html>