function productCheck() {
   if (document.frm.name.value.length == 0) {
      alert("상품명을 써주세요.");
      frm.name.focus();
      return false;
   }
   if (document.frm.price.value.length == 0) {
      alert("가격을 써주세요");
      frm.price.focus();
      return false;
   }
   if (isNaN(document.frm.price.value)) {  //숫자 아닌거 입력할 경우 동작
      alert("숫자를 입력해야 합니다");
      frm.price.focus();
      return false;
   }
   return true;
}