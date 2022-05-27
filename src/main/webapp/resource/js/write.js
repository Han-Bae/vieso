$(document).ready(function(){
	// 홈버튼 클릭 이벤트
	$('#hbtn').click(function(){
		$(location).attr('href', '/vieso/member/loginForm.blp');
	});
	
	// 저장버튼 클릭 이벤트
	$('#savebtn').click(function(){
		var frmData = $('#frm').serialize() ;
		
		$.ajax({
			url: '/viseo/todo/saveTodo.blp',
			type: 'post',
			dataType: 'json',
			data: frmData,
			success: function(obj){
				 	var result = obj.result;
				 	
			 	if(result == 'OK'){//정상 저장처리된 경우
					alert(' 저장되었습니다.');
					$('#fid').modal('hide');
					$('#frm')[0].reset();
					} 
				},
			error: function(){
				alert('### 통신실패 ###');
				$('#fid').modal('hide');
				$('#frm')[0].reset();
			}
		});
	});
	
	$('#c123').click(function(){
		if($('#c123').is(':checked')){
			$('#chcekTime').prop('disabled', true);
			$('#alarmRepeat').prop('hidden', true);
		} else {
			$('#chcekTime').prop('disabled', false);
			$('#alarmRepeat').prop('hidden', false);
		}
	});

});