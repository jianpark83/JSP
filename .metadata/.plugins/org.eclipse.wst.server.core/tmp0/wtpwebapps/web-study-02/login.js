function login(){
	if(document.frm.id.value == ""){
		alert("아이디 입력 요망!");
		document.frm.id.focus();
		return false;
	}else if(document.frm.pwd.value == ""){
		alert("비밀번호 입력 요망!");
		document.frm.id.focus();
		return false;
	}else{
		return true;
	}
}