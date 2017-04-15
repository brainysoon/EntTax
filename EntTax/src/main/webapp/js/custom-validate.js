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
});