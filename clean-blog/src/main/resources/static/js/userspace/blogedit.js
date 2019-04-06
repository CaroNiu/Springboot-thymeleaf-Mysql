/*!
 * writeBlog.html 页面脚本.
 * 
 * @since: 1.0.0 2019-04-23
 * @author Nuri
 */
"use strict";
//# sourceURL=blogedit.js

// DOM 加载完再执行
$(function() {
 
	// 初始化 md 编辑器
    $("#md").markdown({
        language: 'zh',
        fullscreen: {
            enable: true
        },
        resize:'vertical',
        localStorage:'md',
    });
 
 	// 发布博客
 	$("#submitBlog").click(function() {
		// 获取 CSRF Token 
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		// 校验输入框
		if($('#title').val() == "" || $('#title').val() == null){
			$("#titleError").show();
			return;
		}
		if($('#summary').val() == "" || $('#summary').val() == null){
			$("#summaryError").show();
			return;
		}
		if($('#md').val() == "" || $('#md').val() == null){
			$("#contentError").show();
			return;
		}
		$.ajax({
		    url: '/u/'+ $(this).attr("userName") + '/blogs/edit',
		    type: 'POST',
			contentType: "application/json; charset=utf-8",
		    data:JSON.stringify({"id":$('#blogId').val(), 
						    	 "title": $('#title').val(), 
						    	 "summary": $('#summary').val() , 
						    	 "content": $('#md').val()
		    	}),
			beforeSend: function(request) {
			    request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token 
			},
			 success: function(data){
				 if (data.success) {
					// 成功后，重定向
					 window.location = data.body;
				 } 
		     }
		})
 	});
 	
 	
});