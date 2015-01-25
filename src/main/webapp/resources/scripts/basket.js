function addToBasket(id){
    jQuery.ajax({
        url: "/users/basket/"+id,
        type: "PUT",
        statusCode: {
            200: updateBasket
        }
    })
}

function updateBasket(){
    jQuery.ajax({
        url: "/users/basket",
        type: "GET",
        dataType: "json",
        statusCode: {
            200: fillBasketData
        }
    })
}

function fillBasketData(data){
    jQuery("#basketCount").text(data.articles.length);
    jQuery("#basketPrice").text(data.price+'$');
}