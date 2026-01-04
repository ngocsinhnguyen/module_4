package com.example.module4_s5.repository;

import com.example.module4_s5.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductRepository implements IProductRepository {

    private final SessionFactory sessionFactory;

    public ProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product", Product.class).list();
    }

    @Override
    public Product findById(int id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    public void save(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public void update(Product product) {
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        if (product != null) {
            session.delete(product);
        }
    }

    @Override
    public List<Product> search(String name, Double minPrice, Double maxPrice) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Product p where 1=1";
        if (name != null && !name.isEmpty()) {
            hql += " and p.name like :name";
        }
        if (minPrice != null) {
            hql += " and p.price >= :minPrice";
        }
        if (maxPrice != null) {
            hql += " and p.price <= :maxPrice";
        }
        
        org.hibernate.query.Query<Product> query = session.createQuery(hql, Product.class);
        if (name != null && !name.isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }
        if (minPrice != null) {
            query.setParameter("minPrice", minPrice);
        }
        if (maxPrice != null) {
            query.setParameter("maxPrice", maxPrice);
        }
        return query.list();
    }
}
