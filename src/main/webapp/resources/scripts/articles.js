function getArticles() {
    jQuery.ajax({
        url: "/articles?first=0&last=5&orderby=NAME&down=true",
        type: "GET",
        dataType: "json",
        statusCode: {
            200: addArticles
        }
    })
}

function addArticles(data){
    for(var i = 0; i < data.length; i++){
        addArticle(data[i]);
    }
}

function addArticle(article){
    var articleElement = '<div class="col-sm-6 col-md-3">' +
                              '<div class="thumbnail">' +
                                   '<img src="/resources/img/' + article.photo + '"/>' +
                                   '<div class="caption">' +
                                        '<h3>' + article.name + '</h3>' +
                                        '<p>' + article.description + '</p>' +
                                        '<p><a href="#" class="btn btn-primary" role="button">' +
                                             '<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>' +
                                             'Add to basket' +
                                        '</a></p>' +
                                   '</div>' +
                              '</div>' +
                         '</div>';
    jQuery("#articlesContainer").append(jQuery.parseHTML(articleElement));
}