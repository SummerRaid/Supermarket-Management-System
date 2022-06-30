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
        el:"#w2",
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
            deleteSupplier:function(supplierId) {
                axios({
                    method:"POST",
                    url:"supplier.do",
                    params:{
                        operate:"delSupplier",
                        supplierId: supplierId,
                    }
                }).then(function(response){
                    vue.getSuppliers();
                }).catch(function(error){

                });
            },
            editTheSupplier:function(supplierId) {
                axios({
                    method:"POST",
                    url:"supplier.do",
                    params:{
                        operate:"editSupplier",
                        supplierId:supplierId,
                    }
                }).then(function(response){
                    vue.getSuppliers();
                }).catch(function(error){

                });
            },
            addNewSupplier:function(supplierId) {
                axios({
                    method:"POST",
                    url:"supplier.do",
                    params:{
                        operate:"addSupplier",
                    }
                }).then(function(response){
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
        vue.addNewSupplier();
        alert("新角色添加成功！");
    }
}
