<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
			<div class="row">
				<div class="col-sm-offset-3 span9">
					<h1>聯絡我們表單</h1>
					<hr/>
				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="row">
				<div class="span12">
					<form class="form-horizontal" role="form" id="form" METHOD="post" ACTION="<%=request.getContextPath()%>/mail/connectionMail">
						
						<div class="form-group">
							<label for="first_name" class="col-sm-3 control-label">姓名</label>
							<div class="col-sm-3 controls">
								<input type="text" id="first_name" name="first_name" placeholder="萌萌der第三組" class="form-control" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="email" class="col-sm-3 control-label">電子郵件</label>
							<div class="col-sm-4 controls">
								<input type="email" id="email" name="email" placeholder="email@email.com.zh" maxlength="255" class="form-control">
							</div>
						</div>
						
				
						<div class="form-group">
							<label for="content" class="col-sm-3 control-label">詢問內容</label>
							<div class="col-sm-6 controls">
                                <textarea class="form-control" rows="7" id="content" name="content" placeholder="請輸入1,000 字以内"></textarea>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-6">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<input type="hidden" name="action" value="mailsend">
								<button type="submit" class="btn btn-primary btn-lg btn-block">送出</button>
							</div>
						</div>
						
					</form>
				</div>
			</div>
		</div>
</body>
</html>