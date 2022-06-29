/*<![CDATA[*/
window.onload=function(){
    let vue = new Vue({
        el:"#w2",
        data:{
            products:{},
            deleted:0
        },
        methods:{
            getProducts:function() {
                axios({
                    method:"POST",
                    url:"product.do",
                    params:{
                        operate:"getAllProducts"
                    }
                }).then(function(response){
                    let product = response.data;
                    vue.products = product;
                    console.log(vue.products);
                    if(product[0].deleted === 1) {
                        vue.deleted = 1;
                    }
                }).catch(function(error){

                });
            },
            editCart:function(cartItemId, buyCount) {
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operate:"editCart",
                        cartItemId:cartItemId,
                        buyCount:buyCount
                    }
                }).then(function(response){
                    vue.getCart();
                }).catch(function(error){

                });
            }
        },
        mounted:function(){
            this.getProducts();
        },
        updated:function(){
            let btn = document.getElementById("add-btn");
            console.log("btn style" + btn.style);
            if(vue.deleted === 1) {
                btn.style.display = "none";
            } else {
                btn.style.display = "block";
            }
        }
    });
}
function checkEmpty(name){
    var text=$(name).html();
    alert(text);
    if(text.value===""){
        alert("不能为空哦！");
    }else{
        alert("新产品添加成功！");
    }
}
/*]]>*/