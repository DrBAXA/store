var MAX_FILE_SIZE = 716800;// 700 кБ
var EXTENTIONS = ['jpg', 'jpeg', 'png', 'gif'];

var FILE_SIZE_LIMIT = "Розмір файлу не повинен перевищувати 700 кБ";
var INVALID_FILE_EXTENTION = "Неправильне розширення файлу (прийнятні png, gif або jpg)";

jQuery(document).ready(function () {
    updateBasket();
    showPhoto();
    jQuery("#image").change(function(){
        checkImage(this);
    });
});

function changeUrl() {
    var id = jQuery("#id").val();
    jQuery("form").prop("action", getHomeUrl() + "admin/articles/" + id);
    return true;
}

function showPhoto() {
    var photo = jQuery("#photo").val();
    if (photo == '') {
        photo = 'default.png'
    }
    jQuery("#photoImg").attr("src", getHomeUrl() + "resources/img/" + photo)
}

function checkImage(image) {
    if (image.files && image.files[0]) {
        var extension = image.files[0].name.split('.').pop().toLowerCase();  //file extension from input fileisSuccess =  //is extension in acceptable types
        if (!checkFileExtention(extension)) {
            window.alert(INVALID_FILE_EXTENTION);
            jQuery(image).val('').clone(true);
        } else if (!checkFileSize(image.files[0].size)) {
            window.alert(FILE_SIZE_LIMIT);
            jQuery(image).val('').clone(true);
        } else {
            jQuery("#fileName").text(image.files[0].name);
            jQuery("#fileName").show();
        }
    }
}

function checkFileExtention(extention) {
    return EXTENTIONS.indexOf(extention) > -1;
}
function checkFileSize(size) {
    return size <= MAX_FILE_SIZE;
}

