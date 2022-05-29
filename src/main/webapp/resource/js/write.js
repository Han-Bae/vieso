$(document).ready(function(){
	
	$('#toDofid').modal();
	
	// 홈버튼 클릭 이벤트
	$('#hbtn').click(function(){
		$(location).attr('href', '/vieso/member/loginForm.blp');
	});
	
	// 저장버튼 클릭 이벤트
	$('#savebtn').click(function(){
		var title = $('#title').val();
		var aname = $("#aname option:selected").text();
		var city = $("#city option:selected").text();
		
		if(title == ""){
			alert('제목을 입력하세요.');
			$('#title').focus();
		}
		
		if(aname == ""){
			alert('지역을 선택하세요.');
			$('#aname').focus();
		}

		if(city == ""){
			alert('도시를 선택하세요.');
			$('#city').focus();
		}
		
		$('#area').val(aname+""+city);
		
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
					$('#toDofid').modal('hide');
					$('#frm')[0].reset();
					$(location).attr("href", "/viseo/main.blp");
					}
				},
			error: function(){
				alert('### 저장버튼 통신실패 ###');
				$('#toDofid').modal('hide');
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
	
	//메인달력에서 날짜 클릭시 이벤트
	$('.border-primary,.mainDate').click(function(){
    var id = $(this).attr('id'); //선택한 날짜
    
    //선택한 날짜 ex>2022-05-25로 형식변환
    var checkDate = id.substring(0, 4)+'-'+id.substring(4, 6)+'-'+id.substring(6, 8);
    
    $('#chcekDate').val(checkDate); //모달창 내부 날짜 셋팅
    
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
					}else{ //기존 할일이 없는 경우 기본지역조회
						setTimeout(function() {
							selMainArea(checkDate);
						}, 500);
					}
				},
			error: function(){
				alert('### 달력 클릭 통신실패 ###');
				$('#toDofid').modal('hide');
				$('#frm')[0].reset();
			}
		});
	});
   	
   	//기본 지역 조회
   	function selMainArea(checkDate) {
		$('#chcekDate').val(checkDate);
		$('#frm').attr('action', '/viseo/todo/selMainArea.blp').submit();     
	}
	
	//상세 지역 선택 조회
   $('#aname').change(function(){
		var sname = $(this).val();
		
		$.ajax({
			url: '/viseo/todo/selCityList.blp',
			type: 'POST',
			dataType: 'json',
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			data: {
				areaname: sname
			},
			success(data){
				$('#city').html('');
				$('#city').append('<option> 도시 선택 </option>');
				for(i = 0 ; i < data.length ; i++ ){
					var stag = '<option value="'+ data[i].addr +'">' + data[i].city + '</option>';
					$('#city').append(stag);
				}
				
				$('#city').css('display', 'inline');
				$('#citymsg').css('display', 'inline');
			}
		});
	});
	
	//x버튼 클릭시 메인페이지 호출
	$("#closeBtn").click(function(){
		$(location).attr("href", "/viseo/main.blp");
	});

	//모달창 외부영역 클릭시 메인페이지 호출
	$(document).mouseup(function (e){
		if($(".modal").has(e.target).length === 0){
			$(location).attr("href", "/viseo/main.blp");
		}
	});

	
		
});


	
