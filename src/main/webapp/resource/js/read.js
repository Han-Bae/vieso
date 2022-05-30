$(document).ready(function(){
	$('#fid').modal();
	
	var tmpCategory = $('#tmpCategory').val();
	var tmpAlarmRepeat = $('#tmpAlarmRepeat').val();
	var tmpCheckTime = $('#tmpCheckTime').val();
	
	$("#category").val(tmpCategory).prop("selected", true); 
	$("#alarmRepeat").val(tmpAlarmRepeat).prop("selected", true); 
	
	if(tmpCheckTime == "종일"){
		$('#c123').prop("checked", true);
		$('#chcekTime').prop("disabled", true);
		$('#alarmRepeat').prop("disabled", true);
	}else{
		$('#c123').prop("checked", false);
	}

	//확인버튼 클릭 이벤트
	$('#confirmbtn').click(function(){
		$('#fid').modal('hide');
		$(location).attr("href", "/viseo/main.blp");
	});
	
	//수정버튼 클릭 이벤트
	$('#updatbtn').click(function(){
		var frmData = $('#frm').serialize() ;	
		
			$.ajax({
				url: '/viseo/todo/update.blp',
				type: 'post',
				dataType: 'json',
				data: frmData,
				success: function(obj){
					 	var result = obj.result;
					 	
					 	if(result == 'OK'){//정상 저장처리된 경우
							alert('수정되었습니다.');
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
	
	//x버튼 클릭시 메인페이지 호출
	$("#closeBtn").click(function(){
		$(location).attr("href", "/viseo/main.blp");
	});
	
	//종일 체크 이벤트
	$('#c123').click(function(){
		if($('#c123').is(':checked')){
			$('#chcekTime').prop('disabled', true);
			$('#alarmRepeat').prop('hidden', true);
		} else {
			$('#chcekTime').prop('disabled', false);
			$('#alarmRepeat').prop('hidden', false);
			$('#alarmRepeat').prop("disabled", false);
		}
	});
	
	/*
	//모달창 외부영역 클릭시 메인페이지 호출
	$(document).mouseup(function (e){
		if($(".modal").has(e.target).length === 0){
			$(location).attr("href", "/viseo/main.blp");
		}
	});
	*/
});