/**
 * Copyright (c) 2008-2024: Shizhong Shang
 * Project: SupermartketMS
 * @className: supplier
 * @Description: TODO
 *
 * @author: SHIZHONG SHANG
 * @date: 2022/6/29 14:46
 *
 */
window.onload=function(){
    let vue = new Vue({
        el:"#t4",
        data:{
            suppliers:{},
        },
        methods:{
            getSuppliers:function() {
                axios({
                    method:"POST",
                    url:"supplier.do",
                    params:{
                        operate:"getAllSuppliers"
                    }
                }).then(function(response){
                    let supplier = response.data;
                    vue.suppliers = supplier;
                    console.log(vue.suppliers);
                }).catch(function(error){

                });
            },
        },
        mounted:function(){
            this.getSuppliers();
        }
    });
}
function checkEmpty(name) {
    var text = $(name).html();
    alert(text);
    if (text.value === "") {
        alert("不能为空哦！");
    } else {
        alert("新角色添加成功！");
    }
}