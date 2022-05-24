$(document).ready(function(){
	/*$('.subbtn').click(function(){
		var btn = $(this).html();
		
		switch(btn){
			case '아이디찾기':
				console.log('click');
				document.getElementById('IDFIND').addEventLinstner('show.bs.modal');
				break;
			case '비밀번호':
				break;
			case '회원가입':
				var addr = '/vieso/member/join.blp';
				$(location).attr('href', addr);
				return;
		}
	});*/
	// 홈버튼 클릭 이벤트
	$('#hbtn').click(function(){
		$(location).attr('href', '/whistle/main.blp');
	});
});