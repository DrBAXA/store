
/*
Send request to add article with given id to user's basket
*/
function addToBasket(id){
    jQuery.ajax({
        url: getHomeUrl() + "users/basket/"+id,
        type: "PUT",
        statusCode: {
            200: updateBasket
        }
    })
}

/*
Send request for basket data from current user
Basket data is summary price and array with articles id
*/
function updateBasket(){
    jQuery.ajax({
        url: getHomeUrl() +  "users/basket",
        type: "GET",
        dataType: "json",
        statusCode: {
            200: fillBasketData
        }
    })
}

/*
Change display basket data in header
*/
function fillBasketData(data){
    jQuery("#basketCount").text(data.articles.length);
    jQuery("#basketPrice").text(data.price+'$');
}

/*
Send request for Send request for basket data from current user 
Fill basket page
*/
function initBasket(){
    jQuery("#basket").empty();
    jQuery.ajax({
        url: getHomeUrl() +  "users/basket",
        type: "GET",
        dataType: "json",
        statusCode: {
            200: function(data){
                jQuery("#fullPrice").text(data.price + '$');
                for(var i = 0; i < data.articles.length; i++){
                    getArticleElement(data.articles[i])
                }
            }
        }
    })
}

/*
Send request to get article with given id
*/
function getArticleElement(id){
    jQuery.ajax({
        url: getHomeUrl() +  "articles/"+id,
        type: "GET",
        dataType: "json",
        statusCode: {
            200: addArticleElement
        }
    })
}

/*
Add article element received from server to basket page
*/
function addArticleElement(article){
    var element = '<div class="media">' +
                      '<div class="media-left">' +
                          '<button type="button" class="btn btn-danger btn-sm" onclick="deleteFromBasket(' + article.id + ')">' +
                              '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                          '</button>' +
                          '<a href="#">' +
                              '<img class="media-object" src="/resources/img/' + article.photo + '" style="height: 128px;vertical-align: top">' +
                          '</a>' +
                      '</div>' +
                      '<div class="media-body">' +
                          '<h4 class="media-heading">' + article.name + '</h4>' +
                          '<p class="price price-text">' + article.price + '$</p>' +
                      '</div>' +
                  '</div>' +
                  '<hr/>';
    jQuery("#basket").append(jQuery.parseHTML(element));
}

/*
Send request to delete article from user's basket and update data in header and basket page
*/
function deleteFromBasket(id){
    jQuery.ajax({
        url: getHomeUrl() + "users/basket/"+id,
        type: "DELETE",
        async: false,
        dataType: "json"
    })
    updateBasket();
    initBasket();
}