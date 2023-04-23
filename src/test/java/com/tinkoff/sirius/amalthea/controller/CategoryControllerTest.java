//package com.tinkoff.sirius.amalthea.controller;
//
//import com.tinkoff.sirius.amalthea.dto.category.request.CategoryRequestDTO;
//import com.tinkoff.sirius.amalthea.dto.category.response.CategoryResponseDTO;
//import com.tinkoff.sirius.amalthea.model.CategoryType;
//import com.tinkoff.sirius.amalthea.service.CategoryService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.Arrays;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@AutoConfigureMockMvc
//@AutoConfigureWebMvc
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = CategoryController.class)
//public class CategoryControllerTest {
//
//    protected MockMvc mockMvc;
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    @MockBean
//    private CategoryService categoryService;
//
//    @BeforeEach
//    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//    }
//
//    @Test
//    public void getAllCategoriesReturnsListOfAllCategories() throws Exception {
//        CategoryRequestDTO categoryRequestDTO1 = new CategoryRequestDTO()
//                .setType(CategoryType.INCOME)
//                .setName("name1")
//                .setIconId(1L);
//        CategoryRequestDTO categoryRequestDTO2 = new CategoryRequestDTO()
//                .setType(CategoryType.EXPENSE)
//                .setIconId(1L);
//
//        CategoryResponseDTO categoryResponseDTO1 = categoryService.save(categoryRequestDTO1);
//        CategoryResponseDTO categoryResponseDTO2 = categoryService.save(categoryRequestDTO2);
//
//        Mockito.when(categoryService.findAll()).thenReturn(Arrays.asList(categoryResponseDTO1, categoryResponseDTO2));
//
//        mockMvc.perform(get("/categories")).andDo(print());
//    }
//}
