package ru.kulsha.wintermarket.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kulsha.wintermarket.core.repositories.CategoryRepository;
import ru.kulsha.wintermarket.core.entities.Category;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title){
        return categoryRepository.findByTitle(title);
    }

}
