
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>demo</title>
    <link rel="stylesheet" href="<c:url value="/jcrop/css/jquery.Jcrop.css"/>" type="text/css"></link>
    <script type="text/javascript" src="<c:url value="/jcrop/js/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/jcrop/js/jquery.Jcrop.js"/>"></script>
</head>
<body>
<form name="form" action="<%=request.getContextPath()%>/user/uploadHeadImage" class="form-horizontal" method="post" enctype="multipart/form-data">
    <div class="modal-body text-center">
        <div class="zxx_main_con">
            <div class="zxx_test_list">
                <input class="photo-file" type="file" name="imgFile" id="fcupload" onchange="readURL(this);" />
                <img alt="" src="" id="cutimg" />
                <input type="hidden" id="x" name="x" />
                <input type="hidden" id="y" name="y" />
                <input type="hidden" id="w" name="w" />
                <input type="hidden" id="h" name="h" />
            </div>
        </div>
    </div>

    <div class="modal-footer">
        <button id="submit" onclick="">上传</button>
    </div>

    <div id="preview-pane">
        <div class="preview-container">
            <img src="" class="jcrop-preview" alt="预览">
        </div>
    </div>

</form>

<script type="text/javascript">
    //定义一个全局api，这样操作起来比较灵活
    var api = null,

        boundx,
        boundy,

        $preview = $('#preview-pane'),
        $pcnt = $('#preview-pane .preview-container'),
        $pimg = $('#preview-pane .preview-container img'),

        xsize = $pcnt.width(),
        ysize = $pcnt.height();

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.readAsDataURL(input.files[0]);
            reader.onload = function(e) {
                $('#cutimg').removeAttr('src');
                $('#cutimg').attr('src', e.target.result);
                $pimg.removeAttr('src');
                $pimg.attr('src', e.target.result);

                api = $.Jcrop('#cutimg', {
                    setSelect: [ 20, 20, 200, 200 ],
                    aspectRatio: 1,
                    onSelect: updateCoords,
                    onChange:updateCoords
                });
                var bounds = api.getBounds();
                boundx = bounds[0];
                boundy = bounds[1];
                $preview.appendTo(api.ui.holder);
            };
            if (api != undefined) {
                api.destroy();
            }
        }
        function updateCoords(obj) {
            $("#x").val(obj.x);
            $("#y").val(obj.y);
            $("#w").val(obj.w);
            $("#h").val(obj.h);
            if (parseInt(obj.w) > 0) {
                var rx = xsize / obj.w;
                var ry = ysize / obj.h;
                $pimg.css({
                    width : Math.round(rx * boundx) + 'px',
                    height : Math.round(ry * boundy) + 'px',
                    marginLeft : '-' + Math.round(rx * obj.x) + 'px',
                    marginTop : '-' + Math.round(ry * obj.y) + 'px'
                });
            }
        }
        ;
    }
</script>
</body>
</html>

