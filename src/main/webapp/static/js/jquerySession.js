<script type="text/javascript">
    $(function(){
            initCart();
            initHotel();
        }
    );
function initHotel(){
    $.ajax({
        url:'ajaxHotelList',
        type:'post',
        dataType:'json',
        success:function(data){
            sc='<table><tr><th>房间名称</th><th>图片</th><th style="text-align:center;">数量</th><th style="width:50px;">价格</th></tr>';
            $.each(data.data,function(key,val){
                //alert(JSON.stringify(data.Num[key]));
                if (data.Num[key]>0) {
                    sc+='<tr><td><input type="hidden" value="'+val.pid+'"><label for="txt_statu" style="width:200px;">'+val.pname+'门票</label></td><td><img height="50px" weight="50px" title=" " alt=" " src="__STATIC__/images/'+val.pimg+'" /></td><td><label id="shuliang" style="width:200px;text-align:center;" for="txt_statu"><button onclick="gbjg1($(this))" value="-1">-</button><label id="suhliangzhen">'+data.Num[key]+'</label><button onclick="gbjg1($(this))" value="1">+</button></label></td><td><label id="jiage">'+(val.pprice*val.pdiscount*data.Num[key]).toFixed(2)+'</label></div></td></tr>';}

            });
            sc+="</table>";
            $('#cartbody1').html(sc);

        }

    });
}
function initCart(){
    $.ajax({
        url:'ajaxCartList',
        type:'post',
        dataType:'json',
        success:function(data){
            //
            //	alert(JSON.stringify(data));
            sc='<table><tr><th>商品名称</th><th>图片</th><th style="text-align:center;">数量</th><th style="width:50px;">价格</th><th  >是否学生</th></tr>';

            $.each(data.data,function(key,val){
                //alert(JSON.stringify(data.Num[key]));
                if (data.Num[key]>0) {
                    sc+='<tr><td><input type="hidden" value="'+val.sid+'"><label for="txt_statu" style="width:200px;">'+val.sname+'门票</label></td><td><img height="50px" weight="50px" title=" " alt=" " src="__STATIC__/images/'+val.simg+'" /></td><td><label id="shuliang" style="width:200px;text-align:center;" for="txt_statu"><button onclick="gbjg($(this))" value="-1">-</button><label id="suhliangzhen">'+data.Num[key]+'</label><button onclick="gbjg($(this))" value="1">+</button></label></td><td><label id="jiage">'+val.sprice*data.Num[key]+'</label></div></td><td><label><input onclick="alerttr($(this))" name="isStudent'+key+'" type="radio" value="1" />学生 </label><label style="text-align:center;"><input onclick="alerttr($(this))" name="isStudent'+key+'" type="radio"  checked value="2" />成人</label></td></tr>';}

            });
            sc+='</table>';
            $('#cartbody').html(sc);
        }
    });
}
function ajaxCart(sid,state){
    $.ajax({
        url:'ajaxPutCart',
        type:'post',
        dataType:'json',
        data:{'sid':sid,'state':state},
        success:function(data){
            //
            //	alert(JSON.stringify(data));''
            sc='<table><tr><th>商品名称</th><th>图片</th><th style="text-align:center;">数量</th><th style="width:50px;">价格</th><th  >是否学生</th></tr>';
            $.each(data.data,function(key,val){
                //alert(JSON.stringify(data.Num[key]));
                if (data.Num[key]>0) {
                    sc+='<tr><td><input type="hidden" value="'+val.sid+'"><label for="txt_statu" style="width:200px;">'+val.sname+'门票</label></td><td><img height="50px" weight="50px" title=" " alt=" " src="__STATIC__/images/'+val.simg+'" /></td><td><label id="shuliang" style="width:200px;text-align:center;" for="txt_statu"><button onclick="gbjg($(this))" value="-1">-</button><label id="suhliangzhen">'+data.Num[key]+'</label><button onclick="gbjg($(this))" value="1">+</button></label></td><td><label id="jiage">'+val.sprice*data.Num[key]+'</label></div></td><td><label><input onclick="alerttr($(this))" name="isStudent'+key+'" type="radio" value="1" />学生 </label><label style="text-align:center;"><input onclick="alerttr($(this))" name="isStudent'+key+'" type="radio"  checked value="2" />成人</label></td></tr>';}
            });
            sc+='</table>';
            $('#cartbody').html(sc);

        },error:function(){
            //alert(JSON.stringify(sid));
            ajaxCart(sid,0);
        }
    });
}
function gbjg($button){
    $tr=$button.parent().parent().parent();
    $sid=$tr.find("td input[type='hidden']").val();
    $psb=$button.val();
    $.ajax({
        url:'ajaxPlusSub',
        type:'post',
        dataType:'json',
        data:{'sid':$sid,'psb':$psb},
        success:function(data){
            sc='<table><tr><th>商品名称</th><th>图片</th><th style="text-align:center;">数量</th><th style="width:50px;">价格</th><th  >是否学生</th></tr>';
            $.each(data.data,function(key,val){
                if (data.Num[key]>0) {
                    sc+='<tr><td><input type="hidden" value="'+val.sid+'"><label for="txt_statu" style="width:200px;">'+val.sname+'门票</label></td><td><img height="50px" weight="50px" title=" " alt=" " src="__STATIC__/images/'+val.simg+'" /></td><td><label id="shuliang" style="width:200px;text-align:center;" for="txt_statu"><button onclick="gbjg($(this))" value="-1">-</button><label id="suhliangzhen">'+data.Num[key]+'</label><button onclick="gbjg($(this))" value="1">+</button></label></td><td><label id="jiage">'+val.sprice*data.Num[key]+'</label></div></td><td><label><input onclick="alerttr($(this))" name="isStudent'+key+'" type="radio" value="1" />学生 </label><label style="text-align:center;"><input onclick="alerttr($(this))" name="isStudent'+key+'" type="radio"  checked value="2" />成人</label></td></tr>';}

            });
            sc+='</table>';
            $('#cartbody').html(sc);
        }
    });
}
function alerttr($radio){
    $tr=$radio.parent().parent().parent();
    $sid=$tr.find("td input[type='hidden']").val();
    //alert($tr.find("#shuliang").val());
    $.ajax({
        url:'ajaxStudent',
        type:'post',
        dataType:'json',
        data:{'sid':$sid},
        success:function(data){
            if ($radio.val()==1) {
                if(data.sstudent<0){
                    $tr.find("#jiage").html($tr.find("#suhliangzhen").html()*0);}else
                    $tr.find("#jiage").html($tr.find("#suhliangzhen").html()*data.sstudent)
            }
            else{
                $tr.find("#jiage").html($tr.find("#suhliangzhen").html()*data.sprice);
            }
        }
    });

}
function ajaxCartHotel(pid,state){
    $.ajax({
        url:'ajaxPutHotel',
        type:'post',
        dataType:'json',
        data:{'pid':pid,'state':state},
        success:function(data){
            sc='<table><tr><th>房间名称</th><th>图片</th><th style="text-align:center;">数量</th><th style="width:50px;">价格</th></tr>';
            $.each(data.data,function(key,val){
                //alert(JSON.stringify(data.Num[key]));
                if (data.Num[key]>0) {
                    sc+='<tr><td><input type="hidden" value="'+val.pid+'"><label for="txt_statu" style="width:200px;">'+val.pname+'门票</label></td><td><img height="50px" weight="50px" title=" " alt=" " src="__STATIC__/images/'+val.pimg+'" /></td><td><label id="shuliang" style="width:200px;text-align:center;" for="txt_statu"><button onclick="gbjg1($(this))" value="-1">-</button><label id="suhliangzhen">'+data.Num[key]+'</label><button onclick="gbjg1($(this))" value="1">+</button></label></td><td><label id="jiage">'+(val.pprice*val.pdiscount*data.Num[key]).toFixed(2)+'</label></div></td></tr>';}

            });
            sc+="</table>";
            $('#cartbody1').html(sc);

        },error:function(){
            //alert(JSON.stringify(sid));
            ajaxCartHotel(pid,0);
        }
    });
}
function gbjg1($button){
    $tr=$button.parent().parent().parent();
    $pid=$tr.find("td input[type='hidden']").val();
    $psb=$button.val();
    $.ajax({
        url:'ajaxPlusSub1',
        type:'post',
        dataType:'json',
        data:{'pid':$pid,'psb':$psb},
        success:function(data){
            sc='<table><tr><th>房间名称</th><th>图片</th><th style="text-align:center;">数量</th><th style="width:50px;">价格</th></tr>';
            $.each(data.data,function(key,val){
                //alert(JSON.stringify(data.Num[key]));
                if (data.Num[key]>0) {
                    sc+='<tr><td><input type="hidden" value="'+val.pid+'"><label for="txt_statu" style="width:200px;">'+val.pname+'门票</label></td><td><img height="50px" weight="50px" title=" " alt=" " src="__STATIC__/images/'+val.pimg+'" /></td><td><label id="shuliang" style="width:200px;text-align:center;" for="txt_statu"><button onclick="gbjg1($(this))" value="-1">-</button><label id="suhliangzhen">'+data.Num[key]+'</label><button onclick="gbjg1($(this))" value="1">+</button></label></td><td><label id="jiage">'+(val.pprice*val.pdiscount*data.Num[key]).toFixed(2)+'</label></div></td></tr>';}

            });
            sc+="</table>";
            $('#cartbody1').html(sc);
        }
    });
}

function subOrder(){
    var totalRow = 0;
    var isStudents = new Array();//学生1成人2
    $('#cartbody table tr').each(function() {
        $(this).find('td:eq(4)').each(function(){
            isStudents.push($(this).find("input[type='radio']:checked").val());
        });
    });
    $('#cartbody table tr').each(function() {
        $(this).find('td:eq(3)').each(function(){
            totalRow += parseFloat($(this).text());
        });
    });
    $('#cartbody1 table tr').each(function() {
        $(this).find('td:eq(3)').each(function(){
            totalRow += parseFloat($(this).text());
        });
    });
    //alert(isStudents);
    $.ajax({
        url:'payment',
        type:'get',
        tradional:'true',
        dataType:'json',
        data:{'totalRow':totalRow,'isStudents':isStudents},
        success:function(data){
            alert(data.msg);
            if (data.msg=='请先登录系统') {
                window.location.href = "login";
            }
            if (data.msg=='账户余额不足，请先充值!') {
                window.location.href = "usersingle";
            }
            if (data.code==1) {
                $('#cartbody').html(" ");
                $('#cartbody1').html(" ");
                window.location.href = "order";
            }
        }
    });
}
</script>