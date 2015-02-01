package sombra.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sombra.dao.CategoriesDAO;
import sombra.entity.Category;

import java.beans.PropertyEditorSupport;

@Service
public class CategoryBinder extends PropertyEditorSupport {

    @Autowired
    CategoriesDAO categoriesDAO;

    @Override
    public void setAsText(String name){
        Category category = categoriesDAO.getByName(name);
        setValue(category);
    }
}
