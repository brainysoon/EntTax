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
    var fd = new FormData();
    fd.append("upload-file", document.getElementById('upload-file').files[0]);
    var xhr = new XMLHttpRequest();
    if (mark === 1 && mark1 === 1) {
        if (mark1 === 1) {
            /*alert(mark1);*/
            xhr.upload.addEventListener("progress", uploadProgress, false);
            mark = 0;
            mark1 = 0;
        }

    }
    xhr.addEventListener("load", uploadComplete, false);
    xhr.addEventListener("error", uploadFailed, false);
    xhr.addEventListener("abort", uploadCanceled, false);
    xhr.open("POST", "UploadMinimal.aspx");
    xhr.send(fd);
}

/*显示上传进度条*/
function uploadProgress(evt) {
    mark = 0;
    /*if (evt.lengthComputable) {
     var percentComplete = Math.round(evt.loaded * 100 / evt.total);

     if (percentComplete.toString() == "100") {
     document.getElementById('processbar').innerHTML = '上传完成' + percentComplete.toString() + '%';
     } else {
     document.getElementById('processbar').innerHTML = '正在上传' + percentComplete.toString() + '%';
     }

     } else {
     document.getElementById('processbar').innerHTML = '无法计算';
     }*/
    setPro();
}

/*用户确认信息 然后清空上传列表*/
function confirmFile() {
    document.getElementById('fileName').innerHTML = '';
    document.getElementById('fileSize').innerHTML = '';
    document.getElementById('fileType').innerHTML = '';
    document.getElementById('processbar').style.width = '0%';
    document.getElementById('processbar').innerHTML = '';
    document.getElementById('fileSelect').style.display = "block"
}

/*进度条函数*/
function setPro() {
    function setProcess() {
        var processbar = document.getElementById("processbar");
        processbar.style.width = parseInt(processbar.style.width) + 1 + "%";
        processbar.innerHTML = processbar.style.width;
        if (processbar.style.width == "100%") {
            window.clearInterval(bartimer);
        }
    }

    var bartimer = window.setInterval(function () {
        setProcess();
    }, 100);
    window.onload = function () {
        bartimer;
    }

}

function uploadComplete(evt) {
    /* 当服务器响应后，这个事件就会被触发 */
    /*alert(evt.target.responseText);*/
}

function uploadFailed(evt) {
    alert("上传文件发生了错误尝试");
}

function uploadCanceled(evt) {
    alert("上传被用户取消或者浏览器断开连接");
}