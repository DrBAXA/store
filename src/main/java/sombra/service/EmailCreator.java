package sombra.service;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STRawGroupDir;
import sombra.entity.Article;
import sombra.entity.User;

import java.io.IOException;
import java.util.Map;

/**
 * Class for creating e-mail messages from templates
 */
@Service
public class EmailCreator {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private String dirPath;
    private STGroup stGroup;

    private final static char TEMPLATE_DELIMITER = '#';

    public EmailCreator(){
        //creating template group  from files in dir
        try {
            dirPath = new ClassPathResource("email_templates").getURI().getPath();
            logger.info("Loading StringTemplateGroup from dir "+dirPath);
            stGroup = new STRawGroupDir(dirPath, TEMPLATE_DELIMITER, TEMPLATE_DELIMITER);
            stGroup.encoding = "UTF-8";
        }catch (IOException ioe){
            logger.error("Can't load StringTemplateGroup from dir " + dirPath);
        }

    }

    //Method for creating e-mail message to just registered user
    public String registrationEmail(User user){
        logger.info("Loading email message template from registration_template.st");
        ST template = stGroup.getInstanceOf("registration_template");
        template.add("name", user.getName());
        template.add("password", user.getPassword());
        return template.render();
    }

    //Method for creating e-mail message to customer that made order
    public String buyEmail(Map<Article, Integer> basket){
        logger.info("Loading email message template from buy_template.st");
        ST template = stGroup.getInstanceOf("buy_template");
        StringBuilder articlesString = new StringBuilder();
        int price = 0;
        for(Article article: basket.keySet()){
            articlesString.append(article.getName() + " : " + basket.get(article) +  "\n");
            price += article.getPrice()*basket.get(article);
        }
        template.add("articles", articlesString);
        template.add("price", price);
        return template.render();
    }

}
