package com.onlinelearning.DAL;

import com.onlinelearning.Models.Category;
import java.util.List;

public interface CategoryDAO {

    Category getCategoryById(Integer id);
    
    Category getCategoryByName(String name);

    List<Category> getAllCategories();

    Category createCategory(Category category);

    Category updateCategory(Category category);

    Category deleteCategory(Category category);
    
    
}
