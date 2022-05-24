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
});