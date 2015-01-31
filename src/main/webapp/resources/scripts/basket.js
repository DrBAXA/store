/*
Send request to add article with given id to user's basket
*/
function addToBasket(id){
    jQuery.ajax({
        url: getHomeUrl() + "users/basket/"+id,
        type: "PUT",
        statusCode: {
            202: updateBasket
        }
    })
}

/*
Send request for basket data from current user
Basket data is summary price and array with articles id
*/
function updateBasket(){
    jQuery.ajax({
        url: getHomeUrl() +  "users/basket_info",
        type: "GET",
        dataType: "json",
        statusCode: {
            200: fillBasketInfo
        }
    })
}

/*
Change display basket data in header
*/
function fillBasketInfo(data){
    var count = 0;
    for(var key in data.articles){
        if(data.articles.hasOwnProperty(key)){
            count += data.articles[key];
        }
    }
    jQuery("#basketCount").text(count);
    jQuery("#basketPrice").text(data.price+'$');
}

/*
Send request for Send request for basket data from current user 
Fill basket page
*/
function initBasket(){
    jQuery("#basket").empty();
    jQuery.ajax({
        url: getHomeUrl() +  "users/basket_info",
        type: "GET",
        dataType: "json",
        statusCode: {
            200: function(data){
                jQuery("#fullPrice").text(data.price + '$');
                for(var key in data.articles){
                    if(data.articles.hasOwnProperty(key)){
                        getArticleElement(key, data.articles[key])
                    }
                }
            }
        }
    })
}

/*
Send request to get article with given id
*/
function getArticleElement(id, count){
    jQuery.ajax({
        url: getHomeUrl() +  "articles/"+id,
        type: "GET",
        dataType: "json",
        statusCode: {
            200: function(data){
                addArticleElement(data, count)
            }
        }
    })
}

/*
Add article element received from server to basket page
*/
function addArticleElement(article, count){
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
                      '<div class="media-right" style="padding-left: 160%">' +
                          '<h4 class="media-heading">Кількість</h4>' +
                          '<p class="price price-text">' + count + '</p>' +
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
    });
    updateBasket();
    initBasket();
}

function buyWindow(){
    //Send request for users data
    jQuery.ajax({
        url: getHomeUrl() +  "users",
        type: "GET",
        dataType: "json",
        statusCode: {
            200: fillUserData,
            404: function(){
                jQuery("#buyButton").attr("disabled", true);
                jQuery("#emailError").css("display", "block");
                jQuery("#phoneError").css("display", "block");
            }
        }
    });

    //send request for basket data
    jQuery.ajax({
        url: getHomeUrl() +  "users/basket_info",
        type: "GET",
        dataType: "json",
        statusCode: {
            200: function(data){
                jQuery("#bill-positions").empty();
                jQuery("#fullBill").text(data.price);
                var i = 1;
                for(var key in data.articles){
                    if(data.articles.hasOwnProperty(key)){
                        getBillElement(key,data.articles[key],i++);
                    }
                }
            }
        }
    })

    $("#buyModal").modal("show");
}

function fillUserData(user){
    jQuery("#email").val(user.email);
    jQuery("#phone").val(user.phone);
}

function getBillElement(id, count, position){
    jQuery.ajax({
        url: getHomeUrl() +  "articles/"+id,
        type: "GET",
        dataType: "json",
        statusCode: {
            200: function(data){
                addToBill(data, count, position)
            }
        }
    })
}

function addToBill(article, count, position){
    var billElement = '<tr>' +
                          '<td>' + position + '</td>' +
                          '<td>' + article.name + '</td>' +
                          '<td>' + count + '</td>' +
                          '<td>' + article.price + '</td>' +
                      '</tr>';
    jQuery("#bill-positions").append(jQuery.parseHTML(billElement));
}

function validateEmail(){
    var regExp = /^[A-Za-z]([A-Za-z0-9])+([\.\-\_]?[A-Za-z0-9]+)*@([a-z0-9-])+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/;
    if(regExp.test(jQuery("#email").val())){
        jQuery("#emailError").css("display", "none");
        return true;
    }else{
        jQuery("#emailError").css("display", "block");
        return false;
    }
}

function validatePhone(){
    var regExp = /^\+380[0-9]{9}$/;
    if(regExp.test(jQuery("#phone").val())){
        jQuery("#phoneError").css("display", "none");
        return true;
    }else{
        jQuery("#phoneError").css("display", "block");
        return false;
    }
}

function checkValidData(){
    if(validateEmail() && validatePhone()){
        jQuery("#buyButton").attr("disabled", false)
    }else{
        jQuery("#buyButton").attr("disabled", true)
    }
}

function doBuy(){
    jQuery.ajax({
        url: getHomeUrl() +  "users//basket/buy",
        type: "POST",
        dataType: "json",
        statusCode: {
            200: function(data){
                addToBill(data, count, position)
            }
        }
    })
}