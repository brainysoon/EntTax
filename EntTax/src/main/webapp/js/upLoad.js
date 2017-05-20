/**
 * Created by hxmic on 17-5-19.
 */

var mark = 0;//文件上传标记
var flag = 0;//选择文件标记
var mark1 = 0;//税型标记

/*获取上传的文件,显示文件的信息*/
function fileSelected() {
    mark = 1;
    flag = 1;
    var file = document.getElementById('upload-file').files[0];
    var fileselect = document.getElementById('fileSelect');
    document.getElementById('processbar').innerHTML = " ";
    if (flag === 1) {
        fileselect.style.display = "none";
        flag = 0;
    }
    /*else{
     fileselect.style.display = "block";
     }*/

    if (file) {
        var fileSize = 0;
        if (file.size > 1024 * 1024)
            fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
        else
            fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';

        document.getElementById('fileName').innerHTML = '文件名称: ' + file.name;
        document.getElementById('fileSize').innerHTML = '文件大小: ' + fileSize;
        document.getElementById('fileType').innerHTML = '文件类型: ' + file.type;
    }

}

/*显示未进行税型选择的提示信息*/
function dis() {

    var intax = document.getElementById('inTax');
    var outtax = document.getElementById('outTax');
    var ui = document.getElementById("alertState");

    if (intax.checked || outtax.checked) {
        mark1 = 1;
        ui.style.visibility = "hidden";
    } else {
        ui.style.visibility = "visible";
    }

}

/*文件上传按钮,上传文件*/
function uploadFile() {

    //检查有没有勾选
    dis();

    var fd = new FormData();
    fd.append("excelFile", document.getElementById('upload-file').files[0]);
    var intax = document.getElementById('inTax');
    var outtax = document.getElementById('outTax');
    if (intax.checked) fd.append("bmark", $("#inTax").val());
    if (outtax.checked) fd.append("bmark", $("#outTax").val());
    var xhr = new XMLHttpRequest();
    if (mark === 1 && mark1 === 1) {

        xhr.upload.onprogress = function (ev) {

            if (ev.lengthComputable) {

                var per = 100 * ev.loaded / ev.total;
                setPro(per);
            }
        };

        xhr.addEventListener("load", uploadComplete, false);
        xhr.addEventListener("error", uploadFailed, false);
        xhr.addEventListener("abort", uploadCanceled, false);
        xhr.open("POST", "/bill/uploadexcel");
        xhr.send(fd);
    }
}

/*用户确认信息 然后清空上传列表*/
function confirmFile() {
    document.getElementById('fileName').innerHTML = '';
    document.getElementById('fileSize').innerHTML = '';
    document.getElementById('fileType').innerHTML = '';
    var file = document.getElementById('upload-file');
    file.value = "";
    document.getElementById('processbar').style.width = '0%';
    document.getElementById('processbar').innerHTML = '';
    document.getElementById('fileSelect').style.display = "block"
}

/*进度条函数*/
function setPro(per) {
    var processbar = document.getElementById("processbar");
    processbar.style.width = parseInt(per) + "%";
    processbar.innerHTML = processbar.style.width;
}

function uploadComplete(evt) {
    /* 当服务器响应后，这个事件就会被触发 */
    var data=JSON.parse(evt.target.responseText);

    if (data.status > 0) {

        alert("上传文件成功!");
        window.location.href = "/bill/uploadexcel?key=" + data.key;
    }
}

function uploadFailed(evt) {
    alert("上传文件发生了错误尝试");
}

function uploadCanceled(evt) {
    alert("上传被用户取消或者浏览器断开连接");
}