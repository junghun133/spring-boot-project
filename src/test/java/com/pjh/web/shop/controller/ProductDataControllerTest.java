package com.pjh.web.shop.controller;

import com.pjh.web.shop.api.controller.ProductDataController;
import com.pjh.web.shop.http.entity.Documents;
import com.pjh.web.shop.http.entity.Meta;
import com.pjh.web.shop.http.entity.ProductResponse;
import com.pjh.web.shop.service.ProductMasterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ProductDataController.class)
class ProductDataControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    ProductMasterService productMasterService;

    @Test
    public void loadPageTest() throws Exception {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setMeta(Meta.builder().pageable_count(4).total_count(4).is_end(false).build());
        List<Documents> documentsList = new ArrayList<>();
        documentsList.add(Documents.builder().id(1L).explain("test").price(1000).name("testname").imagePath("/test/imsg.jpg").build());
        productResponse.setDocuments(documentsList);
        PageRequest pageRequest = PageRequest.of(1, 9 , Sort.by(Sort.Direction.DESC, "id"));
        Mockito.when(productMasterService.findProductsPageable(pageRequest)).thenReturn(productResponse);

        mockMvc.perform(get("/product/more/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$['meta']", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['meta'].total_count", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['meta'].pageable_count", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['meta'].is_end", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['documents']", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['documents'][0].id", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['documents'][0].name", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['documents'][0].explain", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['documents'][0].price", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['documents'][0].imagePath", is(notNullValue())))
                .andDo(print());
    }

    @Test
    public void 더보기_올바르지않은URI_실패_테스트() throws Exception {
        mockMvc.perform(get("/product/more/이상한주소"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("error/500"))
                .andDo(print());
    }
}