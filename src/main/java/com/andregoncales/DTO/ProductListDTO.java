package com.andregoncales;

import java.util.List;

public class ProductListDTO {
    private List<ProductDataDTO> data;

    public ProductListDTO() {}

    public ProductListDTO(List<ProductDataDTO> data) {
        this.data = data;
    }

    public List<ProductDataDTO> getData() {
        return data;
    }

    public void setData(List<ProductDataDTO> data) {
        this.data = data;
    }
}