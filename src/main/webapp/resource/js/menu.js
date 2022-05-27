/**
 *	 
 *
 *	@author 	전다빈
 *	@since		2022.05.27
 *	@version 	v.1.0
 
 				작업이력	]
 					2022.05.27	-	담당자 : 전다빈
 									내	용 : 로그아웃 이벤트 추가
*/
$(document).ready(function(){
	
		// 메뉴 버튼 click 이벤트 추가
	$(".menu-btn").click(function(){
		const rightPx = $(".menu-bar").css("right");
		if(rightPx == "-500px"){
			$(".menu-btn").css("color", "var(--bs-white)");
			$(".menu-bar").animate({right: "0px"},400);
		} else {
			$(".menu-btn").css("color", "var(--bs-blue)");
			$(".menu-bar").animate({right: "-500px"},400);
		}
	});
	// white 버전 메뉴 버튼 click 이벤트
	$(".menu-btn--white").click(function(){
		const rightPx = $(".menu-bar").css("right");
		if(rightPx == "-500px"){
			$(".menu-bar").animate({right: "0px"},400);
		} else {
			$(".menu-bar").animate({right: "-500px"},400);
		}
	});
	
	
	// 프로필 버튼
	$(".profileBtn").click(function(){
		$(location).attr("href", "/viseo/member/info/ProfileUpdate.blp");
	});
});