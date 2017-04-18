/**
 * Created by brainy on 17-4-14.
 */
//自定义验证账户
$.validator.addMethod("isStaffId", function (value, element) {

    //邮箱正则表达式
    var REGEX_EMAIL = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;

    //手机正则表达式
    var REGEX_MOBILE = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;

    //员工号正则表达式 6-8 位数字
    var REGEX_STAFF = /^[0-9]{6,8}$/;

    return this.optional(element) || REGEX_EMAIL.test(value) ||
        REGEX_MOBILE.test(value) || REGEX_STAFF.test(value);
}, "员工号/手机号/邮箱格式错误");
//自定义手机号码验证
$.validator.addMethod("isPhoneNum", function (value, element) {

    //手机正则表达式
    var REGEX_MOBILE = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;

    return this.optional(element) || REGEX_MOBILE.test(value);
}, "手机号码格式错误");
//自定义邮箱验证
$.validator.addMethod("isEMail", function (value, element) {

    //邮箱正则表达式
    var REGEX_EMAIL = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;

    return this.optional(element) || REGEX_EMAIL.test(value);
}, "邮箱格式错误");
//自定义密码验证
$.validator.addMethod("isPassword", function (value, element) {

    //密码正则表达式 6-16 位 合法字符
    //数字 + 字母，数字 + 特殊字符，字母 + 特殊字符，数字 + 字母 + 特殊字符组合，而且不能是纯数字，纯字母，纯特殊字符
    var REGEX_PASSWORD = /^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,16}$/;

    return this.optional(element) || REGEX_PASSWORD.test(value);
}, "密码格式错误");
//自定义验证码验证
$.validator.addMethod("isCode", function (value, element) {

    //验证码格式 4-6 位字母或则数字
    var REGEX_CODE = /^[a-zA-Z0-9]{4,6}$/;

    return this.optional(element) || REGEX_CODE.test(value);
}, "验证码格式错误");

//全局变量
var phone_rest_validator;
var email_rest_validator;

$().ready(function () {

    $("#loginForm").validate({
        submitHandler: function (form) {

            //验证过后提交表单
            form.submit();
        },
        rules: {
            sname: {
                required: true,
                isStaffId: true
            },
            spassword: {
                required: true,
                isPassword: true
            },
            kcode: {
                required: true,
                isCode: true,
                remote: {
                    type: "post",
                    url: "/checkcaptcha",
                    data: {
                        kcode: function () {

                            return $("#kcode").val();
                        }
                    }
                }
            }
        },
        messages: {
            sname: "员工号/手机号/邮箱格式错误",
            spassword: "密码格式错误",
            kcode: "验证码错误"
        }
    });

    phone_rest_validator = $("#phone_reset").validate({
        submitHandler: function (form) {

            //验证过后提交表单
            form.submit();
        },
        rules: {
            sphone: {
                required: true,
                isPhoneNum: true,
            },
            code: {
                required: true,
                isCode: true,
                remote: {
                    type: "get",
                    url: "/checksmscode",
                    data: {
                        smscode: function () {
                            return $("#code").val();
                        }
                    }
                }
            },
            spassword: {
                required: true,
                isPassword: true
            },
            confirm_password: {
                required: true,
                equalTo: "#spassword"
            }
        },
        messages: {
            sphone: {
                required: "手机号码不能为空",
                isPhoneNum: "请输入正确手机号码"
            },
            code: {
                required: "验证码不能为空",
                isCode: "验证码格式错误",
                remote: "验证码错误"
            },
            spassword: {
                required: "密码不能为空",
                isPassword: "密码位6-16位，至少包含两种字符"
            },
            confirm_password: "两次密码不一致"
        },
        highlight: function (e) {
            $(e).closest(".form-group").removeClass("has-success").addClass("has-error");
        },
        success: function (e) {
            $(e).closest(".form-group").removeClass("has-error").addClass("has-success");
            e.remove();
        }
    });

    email_rest_validator = $("#email_reset").validate({
        submitHandler: function (form) {

            //验证过后提交表单
            form.submit();
        },
        rules: {
            semail: {
                required: true,
                isEMail: true,
            },
            code: {
                required: true,
                isCode: true,
                remote: {
                    type: "get",
                    url: "/checkemailcode",
                    data: {
                        emailcode: function () {
                            return $("#code").val();
                        }
                    }
                }
            },
            spassword: {
                required: true,
                isPassword: true
            },
            confirm_password: {
                required: true,
                equalTo: "#spassword"
            }
        },
        messages: {
            semail: {
                required: "邮箱不能为空",
                isPhoneNum: "请输入正确邮箱"
            },
            code: {
                required: "验证码不能为空",
                isCode: "验证码格式错误",
                remote: "验证码错误"
            },
            spassword: {
                required: "密码不能为空",
                isPassword: "密码位6-16位，至少包含两种字符"
            },
            confirm_password: "两次密码不一致"
        },
        highlight: function (e) {
            $(e).closest(".form-group").removeClass("has-success").addClass("has-error");
        },
        success: function (e) {
            $(e).closest(".form-group").removeClass("has-error").addClass("has-success");
            e.remove();
        }
    });
});
function checkPhoneNum() {      //单独校验 手机号码

    return phone_rest_validator.element($("#sphone"));
}
function checkEMail() {     //单独校验邮箱

    return email_rest_validator.element($("#semail"));
}

