$(function() {
    /*$('#main').css('width', screen.availWidth - 100);*/

    /*editor*/

    //upload title img
    $('#upload').on('click', function() {
        $('#title-img').click();
        //alert('sdfsdf');
    });

    $('#reUpload').on('click', function() {
        $('#img-preview').hide(1000);
        $('#reUpload').hide(1000);
        $('#upload').show(1000);
    });

});
function imgPreview(fileDom){
    //判断是否支持FileReader
    if (window.FileReader) {
        var reader = new FileReader();
    } else {
        alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
    }

    //获取文件
    var file = fileDom.files[0];
    var imageType = /^image\//;
    //是否是图片
    if (!imageType.test(file.type)) {
        alert("请选择图片！");
        return;
    }
    //读取完成
    reader.onload = function(e) {
        //获取图片dom
        // var img = document.getElementById("upload");
        // img.style.display = 'none';

        // var img = document.getElementById("img-preview");
        // img.style.display = 'block';
        // var img = document.getElementById("reUpload");
        // img.style.display = 'block';
        $('#upload').hide(1000);
        $('#img-preview').show(1000);
        $('#reUpload').show(1000);


        //图片路径设置为读取的图片
        //img.src = e.target.result;
        $('#img-preview').attr("src", e.target.result);
        //alert('thtyjy');
    };
    reader.readAsDataURL(file);
}