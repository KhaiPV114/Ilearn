package com.onlinelearning.Services;

import com.onlinelearning.Models.Category;
import java.util.List;

public interface CategoryService {

    Category getCategoryById(Integer id);

    List<Category> getAllCategories();

    Category createCategory(Category category) throws Exception;

    Category updateCategory(Category category) throws Exception;

    Category deleteCategory(Category category) throws Exception;

    Category getCategoryByName(String name);
}
