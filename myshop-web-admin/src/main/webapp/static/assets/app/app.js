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
            radioClass: 'iradio_minimal-blue'
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

    // 初始化datatables
    let handleInitDataTables = function (url, columns) {
        return $("#dataTable").DataTable({
            // 是否分页
            paging: true,
            // 底部信息
            info: true,
            // 页数据量
            lengthChange: true,
            // 是否允许Datatables开启排序
            ordering: false,
            // 展示加载状态
            processing: true,
            // 搜索
            searching: false,
            // 开启服务端分页
            serverSide: true,
            ajax: {
                url: url
            },
            // 控制Datatables的延迟渲染，可以提高初始化的速度
            deferRender: true,
            // 设定列的所有初始属性
            columns: columns,
            // 国际化
            language: {
                sProcessing: "处理中...",
                sLengthMenu: "显示 _MENU_ 项结果",
                sZeroRecords: "没有匹配结果",
                sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                sInfoEmpty: "显示第 0 至 0 项结果，共 0 项",
                sInfoFiltered: "(由 _MAX_ 项结果过滤)",
                sInfoPostFix: "",
                sSearch: "搜索:",
                sUrl: "",
                sEmptyTable: "表中数据为空",
                sLoadingRecords: "载入中...",
                sInfoThousands: ",",
                oPaginate: {
                    sFirst: "首页",
                    sPrevious: "上页",
                    sNex: "下页",
                    sLast: "末页"
                },
                oAria: {
                    sSortAscending: ": 以升序排列此列",
                    sSortDescending: ": 以降序排列此列"
                }
            },
            drawCallback: function (settings, json) {
                app.init();
            }
        });
    };

    // 查看详情
    let handleShowDetail = function (url) {
        $.ajax({
            url: url,
            type: "get",
            success: function (res) {
                $("#modal-detail-body").html(res)
                $("#modal-detail").modal('show')
            }
        })
    }

    return {
        init: function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
        },

        getCheckbox: function () {
            return _checkbox;
        },

        deleteMulti: function (url) {
            handleDeleteMulti(url);
        },

        initDataTables: function (url, columns) {
            return handleInitDataTables(url, columns);
        },

        showDetail: function (url) {
            handleShowDetail(url);
        }
    }
}();

$(document).ready(function () {
    app.init();
});
