function loginCheck(){
	
	if (document.frm.userid.value.length == 0){
		alert("아이디를 입력 해주세요");
		frm.userid.focus();
		return false;
	}
	
	if (document.frm.pwd.value == ""){
		alert("암호를 입력 해주세요");
		frm.pwd.focus();
		return false;
	}
	
	return true;
}