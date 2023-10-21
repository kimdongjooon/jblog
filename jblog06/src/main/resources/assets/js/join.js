/**
 * 회원가입 js 
 * 1. 이름, 아이디, 비밀번호 체킹.
 */



 function onCheckingId(){
	var id = document.getElementById("blog-id").value;
	var img= document.getElementById("img-checkemail");
	var p = document.getElementById("p-checkemail");
	var pattern = /\s/g;
	// 데이터 요청 처리 전처리 : 출력메시지 빨강, 중복확인체크 이미지 블럭,
	p.style.color = "red";
	img.style.display="none";
	
	// 1. 빈값 체킹,
	if(id.match(pattern)){
		p.innerText = '공백을 입력할 수 없습니다.';
		return 0;
	}
	
	// 2. 4자~ 16자까지
	if(id.length<4 || id.length>16){
		p.innerText = 'ID는 4자 ~ 16자까지 입력해주세요.';
		return 0;
	}
	
	// 3.assets 계정 사용 불가.
	if(id == "assets"){
		p.innerText = id+' 계정은 사용할 수 없습니다.';
		return 0;
	}
	
	const Http = new XMLHttpRequest();
	const url = 'join/'+id;
	
	Http.open('GET',url);
	Http.send();
	
	Http.onreadystatechange = function () {
	    if (this.readyState == 4 && this.status == 200) {
	    	
	    	// 3. 중복 체킹
	    	var result = Http.responseText;
	    	p.innerText = result;
	    	if("사용가능한 ID입니다." == result){
				p.style.color = "blue";
	    		img.style.display="";
	    		
	    		
	    		
	    	}else if("현재 사용중인 ID입니다." == result){
				p.style.color = "red";
	    		img.style.display="none";
	    		
	    		
	    	}else{
	    		p.innerText = '오류입니다.';
	    	}

	    } else {
	    	// alert('요청 실패.');
	    }
	}

}