var table = layui.table;
//第一个实例
var tableIns = table.render({
    elem: '#customerList',
    url: '/customer/list',
    page: true,
    parseData: function (res) {
        return {
            "code": res.code,
            "msg": res.msg,
            "count": res.data.count,
            "data": res.data.records
        };
    },
    cols: [[
        {field: 'realName', title: '真实姓名'},
        {field: 'sex', title: '性别'},
        {field: 'age', title: '年龄'},
        {field: 'address', title: '地址'},
        {field: 'phone', title: '手机号码'},
        {field: 'createTime', title: '创建时间'},
        {field: '操作', toolbar: '#barDemo'},

    ]]
});

/**
 * 按条件查询
 */

function query() {
    tableIns.reload({
        where: {
            realName: $("#realName").val(),
            phone: $("#phone").val()
        },
        page: {
            curr: 1
        }
    });
}


function toAdd() {
    openLayer('/customer/toAdd','新增客户');
    layui.form.render();
    mySubmit('addSubmit','POST');
}

//监听工具条
table.on('tool(test)',function (obj){
    var data=obj.data;
    var layEvent=obj.event;
    var tr=obj.tr;

    let customerId = data.customerId;


    if (layEvent=='detail'){

    }else if (layEvent=='del'){
        layer.confirm('真的删除吗？',function (index){
            // obj.del();
            myDelete('/customer/delete/'+customerId);
            layer.close(index);
        });
    }else if (layEvent=='edit'){
        openLayer('/customer/toUpdate/'+customerId,'更新客户');
        layui.form.render();
        mySubmit('updateSubmit','PUT');

    }
})

