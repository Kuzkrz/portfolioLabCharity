package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.service.CategoryService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/stepOne")
    public String getAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "form";
    }
}
