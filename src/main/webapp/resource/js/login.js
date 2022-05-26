/**
 * 
 * @author	김태현
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.24	-	담당자 : 김태현
 * 								내	용 : 클래스 제작
 */
 
$(document).ready(function(){
	// 비밀번호 체크
	$('#rpw, #rpwck').keyup(function(){
		var pw = $('#rpw').val();
		var ckpw = $('#rpwck').val();
		
		if(pw!=ckpw){
			$('#rpwmsg').html('* 비밀번호가 다릅니다.').css('color', 'red');
			$('#rpwbtn').prop('disabled', true);
		}else{
			$('#rpwmsg').html('* 비밀번호가 동일합니다.').css('color', 'blue');
			$('#rpwbtn').prop('disabled', false);
		}
	});
	
	// 모달 나가면 폼 초기화
	$('.subbtn, .logoBtn, .btn-close').click(function(){
		$('#rpwmsg').html('');
		$('#rpwbtn').prop('disabled', false);
		$('form').each(function(){
			this.reset();
		})
	});
	
	// 실패시 상태에 따른 모달창 띄우기
	var status = $("#status").val();
	switch (status){
		case 'refindId':
			$('#fid').modal();
			break;
		case 'refindPw':
			$('#fpw').modal();
			break;
		case 'refindPw_next':
			$('#rpwmd').modal();
	};
		
	
});