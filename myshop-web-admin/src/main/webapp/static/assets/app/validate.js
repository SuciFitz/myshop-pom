/**
 * 函数对象
 * @constructor
 */
let Validate = function () {
    /**
     * 初始化jQueryValidation
     */
    let handlerInitValidation = function () {
        $.validator.addMethod("mobile", function(value, element) {
            let length = value.length;
            let mobile =  /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
            return this.optional(element) || (length === 11 && mobile.test(value));
        }, "手机号码格式错误");
    };

    return {
        init: function () {
            handlerInitValidation()
        }
    }
}();

$(document).ready(function () {
    Validate.init();
});