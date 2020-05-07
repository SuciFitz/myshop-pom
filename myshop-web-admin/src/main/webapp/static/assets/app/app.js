let app = function () {

    // iCheck
    let _masterCheckbox;
    let _checkbox;
    // 存放id的数组
    let _idArray = [];
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

    /**
     * 批量删除
     */
    let handleDeleteMulti = function (url) {
        _idArray = [];

        // 将选中元素的id存入数组中
        _checkbox.each(function () {
            let _id = $(this).attr("id");
            if (_id !== null && _id !== undefined && $(this).is(":checked")) {
                _idArray.push(_id)
            }
        });

        if (_idArray.length === 0) {
            $("#modal-message").html("您还没有选择任何数据项，请至少选择一项")
        } else {
            $("#modal-message").html("确定删除?")
        }
        $("#modal-default").modal('show');
        $("#btnModalOk").off("click");//接触绑定事件，防止多次绑定
        $("#btnModalOk").bind("click", function () {
            del();
        });

        /**
         * 当前函数的私有函数,删除数据
         */
        function del() {
            $("#modal-default").modal('hide');
            // 如果没有选择数据
            if (_idArray.length === 0) {
                //...
            } else {
                // 删除
                setTimeout(function () {
                    $.ajax({
                        url: url,
                        type: "post",
                        data: {
                            ids: _idArray.toString()
                        },
                        dataType: "json",
                        success: function (res) {
                            console.log(res)
                            // 删除成功
                            if (res.status === 200) {
                                window.location.reload();
                            } else {
                                // 删除失败
                                $("#btnModalOk").unbind("click");
                                $("#btnModalOk").one("click", function () {
                                    $("#modal-default").modal('hide');
                                });
                                $("#modal-message").html(res.message);
                                $("#modal-default").modal('show');
                            }
                        }
                    })
                }, 500)
            }
        }
    };

    return {
        init: function() {
            handlerInitCheckbox();
            handlerCheckboxAll();
        },

        getCheckbox: function () {
            return _checkbox;
        },

        deleteMulti: function (url) {
            handleDeleteMulti(url);
        }
    }
}();

$(document).ready(function () {
    app.init();
});
