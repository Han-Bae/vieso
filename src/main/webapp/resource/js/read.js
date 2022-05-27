$(document).ready(function(){
	// 홈버튼 클릭 이벤트
	$('#confirmbtn').click(function(){
		$('#fid').modal('hide');
	});
	
	// 홈버튼 클릭 이벤트
	$('#updatbtn').click(function(){
		$(location).attr('href', '/viseo/todo/update.blp');
	});
});