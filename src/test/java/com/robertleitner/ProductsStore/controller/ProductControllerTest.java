package com.robertleitner.ProductsStore.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.robertleitner.ProductsStore.ProductsStoreApplication;
import com.robertleitner.ProductsStore.controller.ProductController;
import com.robertleitner.ProductsStore.model.Product;
import com.robertleitner.ProductsStore.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductsStoreApplication.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    ProductService productService;

    @MockBean
    ProductController target;

    List<Product> productList;

    Product product;

    private Map<String, String> allRequestParams;

    ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        product = new Product();
        product.setId(1);
        product.setName("Iphone XI");
        product.setDescription("Apple device");
        product.setImg("product-x.jpg");
        product.setPrice(1200);

        productList = new ArrayList<>();
        productList.add(product);

        allRequestParams = new HashMap<>();
        allRequestParams.put("productName", "Iphone");

        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        given(target.getAllProducts()).willReturn(productList);

        mockMvc.perform(get("/v1/product/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    /*@Test
    public void testSearchProduct() throws Exception {
        given(target.searchProduct(allRequestParams)).willReturn(productList);

        mockMvc.perform(post("/v1/product").
                        contentType(MediaType.APPLICATION_JSON)
                        .param("productName", "Iphone")
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());
    }*/

}