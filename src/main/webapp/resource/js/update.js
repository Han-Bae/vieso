$(document).ready(function(){
	// 확인 클릭 이벤트
	$('#up_savebtn').click(function(){
		$(location).attr('href', '/viseo/todo/read.blp');
		$('#fid').modal('hide');
	});
	
	$('#fid').modal();
	
	$('#fid').click(function(){
		$(location).attr('href', '/viseo/todo/read.blp');
		$('#fid').modal('hide');
	});
	
	
	
});