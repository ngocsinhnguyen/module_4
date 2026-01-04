package com.example.module4_s3_su_dung_thymeleaf_quan_ly_san_pham.repository;

import com.example.module4_s3_su_dung_thymeleaf_quan_ly_san_pham.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p", Product.class);
        return query.getResultList();
    }

    @Override
    public void save(Product product) {
        if (product.getId() != 0) {
            entityManager.merge(product);
        } else {
            entityManager.persist(product);
        }
    }

    @Override
    public Product findById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public void update(int id, Product product) {
        entityManager.merge(product);
    }

    @Override
    public void remove(int id) {
        Product product = findById(id);
        if (product != null) {
            entityManager.remove(product);
        }
    }

    @Override
    public List<Product> findByName(String name) {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.name like :name", Product.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
}
