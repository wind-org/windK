<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>Web Tools</title>
	<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_en.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-form/jquery.form.js"  type="text/javascript"></script>
	<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/bootstrap/progressbar/progressbar.css" type="text/css" rel="stylesheet" />
	
	<style type="text/css">
		#progressOverlay .progress {
		    position:relative;
		    width:80%;
		    top:50%;
		    margin:0 auto;
		}
		#progressOverlay {
		    position:relative;
		    height:100%;
		}
	</style>
	<script>
		$(document).ready(function() {
			$("#web-tab").addClass("active");
			$("#message").hide();
			$(".progress").hide();
			$("#urlForm").validate();
			$("#fileform").validate();
		});
		 
		//file upload script
		$('#fileform').ajaxForm({
		    beforeSend: function() {
		    	$('#status').html("");
		        var percentVal = '0%';
		        $('.bar').width(percentVal);
		        $('.percent').html(percentVal);
		    },
		    uploadProgress: function(event, position, total, percentComplete) {
		        var percentVal = percentComplete + '%';
		        $(".progress").show();
		        $('.bar').width(percentVal);
		        $('.percent').html(percentVal);
				//console.log(percentVal, position, total);
		    },
		    success: function() {
		        var percentVal = '100%';
		        $('.bar').width(percentVal);
		        $('.percent').html(percentVal);
		    },
			complete: function(xhr) {
				if(xhr.responseText != ""){
					$('#status').html(xhr.responseText);
					$("#message").show();
				}
				
			}
		});     
		
		
		
		//jsonp mashup script
		var remoteUrl = "http://api.flickr.com/services/feeds/photos_public.gne?tags=cat&tagmode=any&format=json&jsoncallback=?";
		
		function getMashupContent(){
			$.getJSON(remoteUrl,
			function(data) {
	            $.each(data.items,
	            function(i, item) {
	                $("<img/>").attr("src", item.media.m).appendTo("#images");
	                if (i == 2) return false;
	            });
			});
		}
		
		function getMashupContent2() {
			$.ajax({
				url:remoteUrl, 
				dataType:"jsonp"})
				.done(function(data) {
					 $.each(data.items,
			            function(i, item) {
			                $("<img/>").attr("src", item.media.m).appendTo("#images");
			                if (i == 2) return false;
			         });
				});
		}
		
		var contentUrl = "http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"+"/tools/mashup";
		function getMashupContent3(){
			$.ajax({
				url:contentUrl,
				dataType:"jsonp"}).done(function(data){
					$("#contentDiv").html(data.content);
				});
		}
		
		
	</script>	
</head>
<body>
		<h1>Web Tools</h1>
		<h5>1.Static Content Download</h5>
		<div>
			&nbsp;&nbsp;&nbsp;>>&nbsp;<a href="${ctx}/tools/staticContent?contentPath=static/images/eye.jpg">Click and get an image from server !</a>
		</div>
		<h5>2.Remote Content Download</h5>
		<div>
			<form action="${ctx}/tools/remoteContent" method="post" name="urlForm" class="form-horizontal" id="urlForm">
			&nbsp; <input type="radio" name="type" value="Y" checked="checked"> By Apache Http Client &nbsp; &nbsp;<input type="radio" name="type" value="N"> By JDK UrlConnection<br>
			<div> URL:<input type="text" name="remoteUrl" id="remoteUrl" class="input-xxlarge required"><input type="submit" class="btn" id="sub_btn" value="submit" onclick=""/></div>
			</form>
		</div>
		<h5>3.Multiple Files Upload</h5>
		<div>
		    <!--  
			<div class="progress">
			  <div class="bar" style="width: 60%;">60%</div>
			</div>
			<div class="progress progress-danger progress-striped active">
			  <div class="bar" style="width: 40%;">40%</div>
			</div>
			-->
			<div class="progress progress-info progress-striped active">
			  <div class="bar" style="width: 0%;"><span class="percent">0%</span></div>
			</div>
			<form id="fileform" method="post" enctype="multipart/form-data" action="${ctx}/tools/uploadFile">
			    <input type="file"  name="files" id="file1" class="required"/>
			    <input type="file"  name="files" id="file2" /><br>
			    <input type="file"  name="files" id="file3"/>
			    <input type="submit" value="Upload Files" class="btn"/>
			    <div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button><span id="status"></span></div>
			</form>
		</div>
		<h5>4.JSONP Mashup</h5>
		<div id="remoteContent" class="center">
			<div><label>Sample 1: Get the latest cat pictures from Flickr. </label>
			<input type="button" class="btn" id="sub_btn" value="Click" onclick="getMashupContent2()"/></div>
			<div id="images" style="margin:10px"></div>
			<div><label>Sample 2: Get info built by hand. </label>
			<input type="button" class="btn" id="sub_btn" value="Click" onclick="getMashupContent3()"/></div>
			<div id="contentDiv" style="margin:10px"></div>
		</div>
</body>
</html>