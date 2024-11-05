package com.manikanta.microservices.project.ProductService.Repository;

import com.manikanta.microservices.project.ProductService.Entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findProductsByCriteria(String brand, String productDesc, String productName, Long quantity) {
        // initialize the CriteriaBuilder and CriteriaQuery

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if(productDesc != null && !productDesc.isEmpty()){
            predicates.add(criteriaBuilder.like(productRoot.get("productDesc"), "%" + productDesc + "%"));
        }
        if(productName != null && !productName.isEmpty()){
            predicates.add(criteriaBuilder.like(productRoot.get("productName"), "%" + productName + "%"));
        }
        if(quantity != null){
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(productRoot.get("quantity"), quantity));
        }
        if(brand != null && !brand.isEmpty()){
            predicates.add(criteriaBuilder.equal(productRoot.get("brand"), brand));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
