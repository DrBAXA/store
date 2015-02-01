jQuery(document).ready(function(){
    updateBasket();
});

function changeUrl() {
    var id = jQuery("#id").val();
    jQuery("form").prop("action", getHomeUrl() + "admin/articles/" + id );
    return true;
}

