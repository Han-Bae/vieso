
   /*select option 생일 날짜 생성 함수*/
   $(document).ready(function(){            

    var now = new Date();
    var year = now.getFullYear();
    var mon = (now.getMonth() + 1) > 9 ? ''+(now.getMonth() + 1) : '0'+(now.getMonth() + 1); 
    var day = (now.getDate()) > 9 ? ''+(now.getDate()) : '0'+(now.getDate());           

    //년도 selectbox만들기               
    for(var i = 1900 ; i <= year ; i++) {
        $('#year').append('<option value="' + i + '">' + i + '년</option>');    
    }

    // 월별 selectbox 만들기            
    for(var i=1; i <= 12; i++) {
        var mm = i > 9 ? i : "0"+i ;             
        $('#month').append('<option value="' + mm + '">' + mm + '월</option>');    
    }
    
    // 일별 selectbox 만들기
    for(var i=1; i <= 31; i++) {
        var dd = i > 9 ? i : "0"+i ;            
        $('#day').append('<option value="' + dd + '">' + dd+ '일</option>');    
	    }
		    $("#year  > option[value="+year+"]").attr("selected", "true");        
		    $("#month  > option[value="+mon+"]").attr("selected", "true");    
		    $("#day  > option[value="+day+"]").attr("selected", "true");     
		 
      
   // 아이디 정규식
   $('#id').change(function(){
      // 할일
      // 입력된 데이터 읽어온다.
      var id = $(this).val();
      
      var pat = /^([A-Za-z0-9]){4,10}$/;
      
      if(!pat.test(id)){
         $('#idmsg').html('형식에 맞게 입력해주세요.');
         $('#idmsg').removeClass('w3-text-green w3-text-red').addClass('w3-text-red')
      } else {
         $('#idmsg').html('정확한 아이디입니다.');
         $('#idmsg').removeClass('w3-text-green w3-text-red').addClass('w3-text-green');
      }
      $('#idmsg').css('display', 'block');
   });       
     
     
   // 아이디 체크 버튼 클릭이벤트(중복 검사)
   $('#idck').click(function(){
      // 할일
      // 입력한 아이디 꺼내오고
      var sid = $('#id').val();
      
      if(!sid){
         // 입력내용이 없는 경우
         $('#id').focus();
         alert('아이디를 입력하세요.');
         return;
      }
      // 전달해서 사용가능 유무 판단하고
      $.ajax({
         url: '/viseo/member/idCheck.blp',
         type: 'post',
         dataType: 'json',
         data: {
            id: sid
         },
         success: function(data){
            var result = data.result;
            $('#idckmsg').removeClass('w3-text-green w3-text-red');
            
            // 뷰에 보여주고
            if(result == 'OK'){
               // 입력한 아이디가 사용가능한 경우
               $('#idckmsg').html('사용 가능한 아이디입니다.');
               $('#idckmsg').addClass('from-text text-green');
            } else {
               // 입력한 아이디가 사용불가능한 경우
               $('#idckmsg').html('사용 불가능한 아이디입니다.');
               $('#idckmsg').addClass('from-text text-red');
            }
            $('#idckmsg').css('display', 'block');
            
         },
         error: function(){
            alert('통신 에러');
         }
      });      
   });


   // 비밀번호 정규식 검사 이벤트
   $('#pw').change(function(){
      // 입력된 데이터 읽어온다.
      var pw = $(this).val();
      
      var pat = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,15}$/;
      
      if(!pat.test(pw)){
         $('#pwmsg').html('형식에 맞게 입력해 주세요.');
         $('#pwmsg').removeClass('w3-text-green w3-text-red').addClass('w3-text-red');
      } else {
         $('#pwmsg').html('정확한 비밀번호입니다.');
         $('#pwmsg').removeClass('w3-text-green w3-text-red').addClass('w3-text-green');
      }
      $('#pwmsg').css('display', 'block');
   });   
   
   
   // 비밀번호 입력이벤트
   $('#repw').keyup(function(){
      // 입력된 데이터 읽어오기
      var pw = $('#pw').val();
      var repw = $(this).val();
      if(pw != repw){
         $('#repwmsg').html('비밀번호가 일치하지 않습니다.');
         $('#repwmsg').css('display', 'block');
      } else {
         $('#repwmsg').html('비밀번호가 일치합니다.');
         $('#repwmsg').css('display', 'block');
/*         $('#repw').parent().parent().stop().slideDown(300).stop().slideUp(300);*/
/*         $('#pw').css('background-color', 'blue').prop('readonly', true);*/
      	}
   	});
   
   
	// 닉네임 확인 버튼 클릭 이벤트 (중복 검사)
    $('#nicknameck').click(function(){
    	var sname = $('#nickname').val();
      
		if(!sname){
        // 입력내용이 없는 경우
		$('#nickname').focus();
        alert('닉네임을 입력하세요.');
        return;
      	}
      	
      	// 전달해서 사용가능 유무 판단
      	$.ajax({
	        url: '/viseo/member/nicknameCheck.blp',
	        type: 'post',
	        dataType: 'json',
	        data: {
	            nickname: sname
	 		},
			success: function(data){
        	var result = data.result;
        	$('#nicknamemsg').removeClass('w3-text-green w3-text-red');
            
            // 뷰에 보여주고
            if(result == 'OK'){
                // 입력한 아이디가 사용가능한 경우
                $('#nicknamemsg').html('사용 가능한 닉네임입니다.');
                $('#nicknamemsg').addClass('w3-text-green');
        	} else {
                // 입력한 아이디가 사용불가능한 경우
                $('#nicknamemsg').html('사용 불가능한 닉네임입니다.');
               	$('#nicknamemsg').addClass('w3-text-red');
            	}
            	$('#nicknamemsg').css('display', 'block');
            
         		},
     		error: function(){
        	alert('통신 에러');
     		}
  		});      
	});
   
   // 지역 선택 이벤트 처리
   $('#aname').change(function(){
		var sname = $(this).val();
		
		$.ajax({
			url: '/viseo/member/cityList.blp',
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
	
   // 이메일 체크 버튼 클릭이벤트(중복 검사)
   $('#mailck').click(function(){
      // 할일
      // 입력한 아이디 꺼내오고
      var smail = $('#mail').val();
      
      if(!smail){
         // 입력내용이 없는 경우
         $('#mail').focus();
         alert('이메일을 입력하세요.');
         return;
      }
      // 전달해서 사용가능 유무 판단하고
      $.ajax({
         url: '/viseo/member/mailCheck.blp',
         type: 'post',
         dataType: 'json',
         data: {
            mail: smail
         },
         success: function(data){
            var result = data.result;
            $('#mailckmsg').removeClass('w3-text-green w3-text-red');
            
            // 뷰에 보여주고
            if(result == 'OK'){
               // 입력한 아이디가 사용가능한 경우
               $('#mailckmsg').html('사용 가능한 이메일입니다.');
               $('#mailckmsg').addClass('from-text text-green');
            } else {
               // 입력한 아이디가 사용불가능한 경우
               $('#mailckmsg').html('사용 불가능한 이메일입니다.');
               $('#mailckmsg').addClass('from-text text-red');
            }
            $('#mailckmsg').css('display', 'block');
            
         },
         error: function(){
            alert('통신 에러');
         }
      });      
   });
   
   
   
   // 가입하기 버튼 클릭 이벤트
   $('#jbtn').click(function(){
      // 데이터 유효성 검사
      var name = $('#name').val();
      var id = $('#id').val();
      var pw = $('#pw').val();
      var mail = $('#mail').val();
      var gen = $('[name="gen"]:checked').val();
      var nickname = $('#nickname').val();
      var tel = $('#tel').val();
      var yyyy = $('#year').val();
      var mm = $('#month').val();
      var dd = $('#day').val();
      var addr = $('#addr').val();
      var birth = $('#birth').val(yyyy + mm + dd);
      
      var el = $('#name, #id, #pw, #mail, #nickname, #tel, #addr');
      
      for(var i = 0 ; i < el.length ; i++ ){
         var txt = $(el).eq(i).val();
         
         if(!txt){
            alert('필수 입력사항을 확인하세요!');
            $(el).eq(i).focus();
            return;
         }
         
      }
      
      if(!(gen)){
         alert('필수 선택 사항을 확인하세요!');
         return;
      }
      
      $('#frm').attr('action', '/viseo/member/joinProc.blp').submit();     
   });
      
/*  
   오류확인용    
     $('#year').change(function(){
      var yyyy = $('#year').val();
      console.log(yyyy);
      console.log(typeof yyyy); 
   });
  */    
  
  
//마지막 주석
});