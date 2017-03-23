function getSMSCode(){
    alert("aaaa");
    $.get("sendSMS.from",$("#phone").val(),function (data) {
    });
    alert("bbbb");
}
function svalidaSMS() {
    $.get("validaSMS",$("#code").val(),function(data){

    });
}

