package com.andregoncales;

public class ProdutoMapper {

    public static Produto toEntity(ProductDataDTO dto) {
        if (dto == null) return null;
        Produto produto = new Produto();
        produto.setProduct(dto.getProduct());
        produto.setQuantity(dto.getQuantity());
        produto.setPrice(dto.getPrice());
        produto.setType(dto.getType());
        produto.setIndustry(dto.getIndustry());
        produto.setOrigin(dto.getOrigin());
        return produto;
    }

    public static ProductDataDTO toDTO(Produto produto) {
        if (produto == null) return null;
        ProductDataDTO dto = new ProductDataDTO();
        dto.setProduct(produto.getProduct());
        dto.setQuantity(produto.getQuantity());
        dto.setPrice(produto.getPrice());
        dto.setType(produto.getType());
        dto.setIndustry(produto.getIndustry());
        dto.setOrigin(produto.getOrigin());
        return dto;
    }
}