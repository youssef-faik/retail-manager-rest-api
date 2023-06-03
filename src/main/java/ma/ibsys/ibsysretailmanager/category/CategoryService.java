package ma.ibsys.ibsysretailmanager.category;

import jakarta.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;
  private final ModelMapper modelMapper;

  public ResponseEntity<List<Category>> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();
    return ResponseEntity.ok(categories);
  }

  public ResponseEntity<Category> getCategoryById(int id) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Categorie introuvable avec l'ID " + id));
    return ResponseEntity.ok(category);
  }

  public ResponseEntity<Void> createCategory(CategoryCreateDto categoryCreateDto) {
    Category category = modelMapper.map(categoryCreateDto, Category.class);
    Category savedCategory = categoryRepository.save(category);

    return ResponseEntity.created(URI.create("/api/v1/categories/" + savedCategory.getId()))
        .build();
  }

  public ResponseEntity<Void> updateCategory(int id, CategoryCreateDto categoryCreateDto) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Categorie introuvable avec l'ID " + id));

    category.setName(categoryCreateDto.getName());
    category.setDescription(categoryCreateDto.getDescription());
    categoryRepository.save(category);
    return ResponseEntity.ok().build();
  }

  public ResponseEntity<Void> deleteCategory(int id) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Categorie introuvable avec l'ID " + id));
    categoryRepository.delete(category);
    return ResponseEntity.noContent().build();
  }
}
