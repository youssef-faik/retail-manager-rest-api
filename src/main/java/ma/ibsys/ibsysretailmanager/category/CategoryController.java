package ma.ibsys.ibsysretailmanager.category;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoryApi {
  private final CategoryService categoryService;

  @Override
  public ResponseEntity<List<Category>> getAllCategories() {
    return categoryService.getAllCategories();
  }

  @Override
  public ResponseEntity<Category> getCategoryById(int id) {
    return categoryService.getCategoryById(id);
  }

  @Override
  public ResponseEntity<Void> createCategory(CategoryCreateDto categoryCreateDto) {
    return categoryService.createCategory(categoryCreateDto);
  }

  @Override
  public ResponseEntity<Void> updateCategory(int id, CategoryCreateDto categoryCreateDto) {
    return categoryService.updateCategory(id, categoryCreateDto);
  }

  @Override
  public ResponseEntity<Void> deleteCategory(int id) {
    return categoryService.deleteCategory(id);
  }
}
