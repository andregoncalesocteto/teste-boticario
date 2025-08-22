package com.andregoncales;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByProductAndType(String product, String type);
    Page<Produto> findByProductContainingIgnoreCase(String product, Pageable pageable);
}