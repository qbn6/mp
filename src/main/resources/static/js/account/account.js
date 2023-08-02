layui.laydate.render({
    elem:'#createTimeRange',
    range:true
})
var table = layui.table;
//第一个实例
var tableIns = table.render({
    elem: '#accountList',
    url: '/account/list',
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
        {field: 'username', title: '用户名'},
        {field: 'realName', title: '真实姓名'},
        {field: 'roleName', title: '角色名称'},
        {field: 'sex', title: '性别'},
        {field: 'email', title: '邮箱'},
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
            email: $("#email").val(),
            createTimeRange:$("#createTimeRange").val()
        },
        page: {
            curr: 1
        }
    });
}

/**
 * 添加账号
 */
function toAdd() {
    openLayer('/account/toAdd','新增账号');
    layui.form.render();
    mySubmit('addSubmit','POST');
}

//监听工具条

//监听工具条
table.on('tool(test)',function (obj){
    var data=obj.data;
    var layEvent=obj.event;
    var tr=obj.tr;

    let accountId = data.accountId;


    if (layEvent=='detail'){
        openLayer('/account/toDetail/'+accountId,'查看客户');
    }else if (layEvent=='del'){
        layer.confirm('真的删除吗？',function (index){
            // obj.del();

            myDelete('/account/delete/'+accountId);
            layer.close(index);
        });
    }else if (layEvent=='edit'){
        openLayer('/account/toUpdate/'+accountId,'更新客户');
        layui.form.render();
        mySubmit('updateSubmit','PUT');

    }
})


