/**
 * Created by hxmic on 17-5-25.
 */

/*用于设置清空消息列表,和标记消息*/
function sampleAll(){
    var obj = document.getElementById('mark-read');
    var allread = document.getElementById('all-read');
    var clearinfo = document.getElementById('clear-infor');
    allread.style.display = "none";
    obj.style.display = "none";
    clearinfo.style.display = "block";
}
/*用于设置清空消息列表,和标记消息*/
function sampleNone() {
    var obj = document.getElementById('mark-read');
    var allread = document.getElementById('all-read');
    var clearinfo = document.getElementById('clear-infor');
    allread.style.display = "none";
    obj.style.display = "block";
    clearinfo.style.display = "block";
}
/*用于设置清空消息列表,和标记消息*/
function sampleRead() {
    var obj = document.getElementById('mark-read');
    var allread = document.getElementById('all-read');
    var clearinfo = document.getElementById('clear-infor');
    obj.style.display = "none"
    allread.style.display = "block";
    clearinfo.style.display = "none"
}

function g(){
    alert('good');
}
