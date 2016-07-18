package com.tw.dao;

import com.tw.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion,String> {
    Promotion findByPromotionName(String promotionName);
}
