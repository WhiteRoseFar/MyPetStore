package csu.web.MyPetStore.persistence;

import csu.web.MyPetStore.domain.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getCategoryList();

    Category getCategory(String categoryId);
}
