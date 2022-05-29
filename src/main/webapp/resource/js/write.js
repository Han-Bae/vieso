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
					$(location).attr("href", "/viseo/main.blp");
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
	
		$('.border-primary,.mainDate').click(function(){
        var id = $(this).attr('id'); //선택한 날짜
        
        var checkDate = id.substring(0, 4)+'-'+id.substring(4, 6)+'-'+id.substring(6, 8);
        
        $('#chcekDate').val(checkDate);
        
        	//기존 할일 등록여부 조회용
        	$.ajax({
			url: '/viseo/todo/readCnt.blp',
			type: 'post',
			dataType: 'json',
			data: {
				checkDate: checkDate
			},
			success: function(obj){
				 	var result = obj.result;
				 	if(result == 'OK'){//기존 할일이 존재하는 경우 
						$('#frm').attr('action', '/viseo/todo/read.blp').submit();     
					}else{ //기존 할일이 없는 경우 입력창
						$('#fid').modal();
					} 
				},
			error: function(){
				alert('### 통신실패 ###');
				$('#fid').modal('hide');
				$('#frm')[0].reset();
			}
		});
        
   	});
});


	
