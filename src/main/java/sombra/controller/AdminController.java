package sombra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sombra.entity.Article;
import sombra.entity.Category;
import sombra.service.ArticlesService;
import sombra.service.CategoryBinder;

import javax.servlet.http.HttpServletRequest;

import static sombra.util.ImageSaver.saveImage;

@Controller
@RequestMapping("/admin/articles")
public class AdminController {

    @Autowired
    ArticlesService articlesService;
    @Autowired
    CategoryBinder categoryBinder;

    @RequestMapping(method = RequestMethod.GET)
    public String getAdminPage(ModelMap modelMap){
        modelMap.addAttribute("disabled", "articles");
        modelMap.addAttribute("active", "admin");
        return "adminArticles";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getArticle(@PathVariable(value="id") int id, ModelMap modelMap){
        modelMap.addAttribute("article", articlesService.getArticle(id));
        modelMap.addAttribute("active", "admin");
        modelMap.addAttribute("disabled", "articles");
        modelMap.addAttribute("categories", articlesService.getAllCategories());
        return "adminArticle";
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Category.class, categoryBinder);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String saveUser(@PathVariable("id") int id,
                           Article article,
                           @RequestParam(value = "image", required = false) MultipartFile image,
                           HttpServletRequest request, ModelMap model) {
        Article oldArticle = articlesService.getArticle(id);
        if (! image.isEmpty()) {
            article.setPhoto(image.getOriginalFilename());
            saveImage(image, request);
        } else {
            article.setPhoto(oldArticle.getPhoto());
        }
        articlesService.update(article);
        return "adminArticles";
    }

}
