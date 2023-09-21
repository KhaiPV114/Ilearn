package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.CategoryDAO;
import com.onlinelearning.DAL.Impl.CategoryDAOImpl;
import com.onlinelearning.Models.Category;
import com.onlinelearning.Services.CategoryService;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public Category getCategoryById(Integer id) {
        if (id == null) {
            return null;
        }
        return categoryDAO.getCategoryById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    private void validateCategory(Category category) throws Exception {
        if (categoryDAO.getCategoryByName(category.getName()) != null) {
            throw new Exception("Category name is already existed!");
        }
    }

    @Override
    public Category createCategory(Category category) throws Exception {
        validateCategory(category);
        Category createdCategory = categoryDAO.createCategory(category);
        if (createdCategory == null) {
            throw new Exception("Create category failed!");
        }
        return createdCategory;
    }

    @Override
    public Category updateCategory(Category category) throws Exception {
        validateCategory(category);
        Category updatedCategory = categoryDAO.updateCategory(category);
        if (updatedCategory == null) {
            throw new Exception("Update category failed!");
        }
        return updatedCategory;
    }

    @Override
    public Category deleteCategory(Category category) throws Exception {
        if (category.getId() == null) {
            throw new Exception("Category id must not be empty!");
        }
        Category deletedCategory = categoryDAO.deleteCategory(category);
        if (deletedCategory == null) {
            throw new Exception("Delete category failed!");
        }
        return deletedCategory;
    }

}
