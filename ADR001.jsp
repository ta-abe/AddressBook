<%@ page contentType = "text/html;charset=UTF-8"%>

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
    <% Object size = request.getAttribute("size");
    if(size != null){
      String s;
      s = size.toString();
      int j = Integer.parseInt(s);%>
      <form method = "POST" action = "AddressBookServlet">
        <button type = "submit" value = "新規登録"  name = "btnAdd">新規登録</button><br><br>
      </form>
      <table border = "1" width = "800">
        <tr>
          <th>氏名</th>
          <th>かな</th>
          <th>メールアドレス</th>
          <th>電話番号</th>
          <th></th>
        </tr>
        <%for(int i= 0 ; i < j ; i++){%>
          <tr>
            <form method = "POST" action = "AddressBookServlet">
              <th><label value = "<%=request.getAttribute("NAME" + i)%>" name = "lblName"><%=request.getAttribute("NAME" + i)%></label></th>
              <th><label value = "<%=request.getAttribute("KANA" + i)%>" name = "lblKana"><%=request.getAttribute("KANA" + i)%></label></th>
              <th><label value = "<%=request.getAttribute("MAIL_ADDRESS" + i)%>" name = "lblMailAddress"><%=request.getAttribute("MAIL_ADDRESS" + i)%></label></th>
              <th><label value = "<%=request.getAttribute("PHONE_NUMBER" + i)%>" name = "lblPhoneNumber"><%=request.getAttribute("PHONE_NUMBER" + i)%></label></th>
              <th><button type = "submit" value = "参照" name = "btnRefer">参照</button>
              <input type = "hidden" value = "<%=request.getAttribute("UUID" + i) %>" name = "hidUuid"></th>
            </form>
          </tr>
        <%} %>
      </table>
    <%}else{%>
      <form method = "GET" action = "ShoppingListServlet" name = "F" >
        <meta http-equiv = "refresh" content = "0;URL=http://localhost:8080/address_book/AddressBookServlet">
      </form>
    <%} %>
  </body>
</html>