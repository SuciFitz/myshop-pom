let app = function () {

    let _masterCheckbox;
    let _checkbox;
    /**
     * 私有方法，初始化ICheck
     */
    let handlerInitCheckbox = function () {
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });

        // 顶部控制CheckBox
        _masterCheckbox = $('input[type = "checkbox"].minimal.icheck_master');
        // 获取全部CheckBox集合
        _checkbox = $('input[type="checkbox"].minimal');
    };

    /**
     * CheckBox全选功能
     */
    let handlerCheckboxAll = function () {
        _masterCheckbox.on("ifClicked", function (e) {
            // true表示未选中
            if (e.target.checked) {
                _checkbox.iCheck("uncheck");
            }
            // 选中状态
            else {
                _checkbox.iCheck("check");
            }
        })
    };

    return {
        init: function() {
            handlerInitCheckbox();
            handlerCheckboxAll();
        },

        getCheckbox: function () {
            return _checkbox;
        }
    }
}();

$(document).ready(function () {
    app.init();
});
