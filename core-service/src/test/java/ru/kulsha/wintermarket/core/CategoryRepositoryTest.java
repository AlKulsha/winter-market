package ru.kulsha.wintermarket.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.kulsha.wintermarket.core.entities.Category;
import ru.kulsha.wintermarket.core.repositories.CategoryRepository;

import java.util.Collections;
import java.util.List;

@DataJpaTest
@ActiveProfiles("db.test")
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findAllCategoriesTest() {
        Category category = new Category();
        category.setTitle("ElectroGoods");
        category.setProducts(Collections.emptyList());
        entityManager.persist(category);
        entityManager.flush();

        List<Category> categoriesList = categoryRepository.findAll();

        Assertions.assertEquals(3, categoriesList.size());
        Assertions.assertEquals("Apparel", categoriesList.get(0).getTitle());
    }
}
