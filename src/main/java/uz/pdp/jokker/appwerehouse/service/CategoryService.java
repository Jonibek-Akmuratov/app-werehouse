package uz.pdp.jokker.appwerehouse.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jokker.appwerehouse.entity.Category;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.CategoryDto;
import uz.pdp.jokker.appwerehouse.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;


    public ApiResponce add(CategoryDto categoryDto) {
        if (categoryRepository.existsByName(categoryDto.getName())) {
            return new ApiResponce("Bunday Categorya avvaldann mavjud", false);
        }
        Optional<Category> optionalCategory = null;
        Category parentCategory = null;
        if (!(categoryDto.getParentCategory() == null)) {
            optionalCategory = categoryRepository.findById(categoryDto.getParentCategory());
            if (!optionalCategory.isPresent()) {
                return new ApiResponce("Bunday ParentCategorya  topilmadi", false);
            }
            parentCategory = optionalCategory.get();
        }
        if (categoryDto.getParentCategory() == null)
            parentCategory = null;
        Category category = new Category(
                categoryDto.getName(),
                parentCategory
        );
        Category save = categoryRepository.save(category);
        return new ApiResponce("Category succesfully saved", true, save);
    }
}
