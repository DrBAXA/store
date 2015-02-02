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
import java.security.Principal;

@Controller
@RequestMapping("/admin/articles")
public class AdminController {

    @Autowired
    ArticlesService articlesService;
    @Autowired
    CategoryBinder categoryBinder;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Category.class, categoryBinder);
    }

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

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String newArticle(ModelMap modelMap,
                             Principal user){
        if(user != null){
            modelMap.addAttribute("user", user.getName());
        }
        modelMap.addAttribute("categories", articlesService.getAllCategories());
        modelMap.addAttribute("article", new Article());
        return "adminArticle";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String saveArticle(@PathVariable("id") int id,
                           Article article,
                           @RequestParam(value = "image", required = false) MultipartFile image,
                           HttpServletRequest request, ModelMap model) {
        articlesService.saveOrUpdate(article, image);
        return "redirect:/admin/articles/"+article.getId();
    }

}
