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

years();
/*用于实现年的选择*/
function years() {
    var y = document.getElementById('year');
    var dates = new Date();
    var nowYear = dates.getFullYear();
    for (i = nowYear; i >= 1995; i--) {
        //用于打印输出年的范围，以下类同
        var str = "<option value=\"" + i + "\">" + i + "年"+"</option>";
        y.innerHTML +=str;
    }
}