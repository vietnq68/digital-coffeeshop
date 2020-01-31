package io.digital.orderservice.service;

import io.digital.orderservice.entity.BaseEntity;
import io.digital.orderservice.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.ParameterizedType;

public abstract class BaseService<T extends BaseEntity> {
    @Autowired
    protected JpaRepository<T,Integer> repository;

    protected String clazzName = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();


    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);

    public T create(T entity) {
        logger.info("Creating new {}:{}",clazzName,entity);
        entity.setCreatedAt(System.currentTimeMillis());
        entity = repository.save(entity);
        logger.info("Created new {} with id: {}",clazzName,entity.getId());
        return entity;
    }

    public void update(Integer id, T entity) {
        T updated = get(id);
        BeanUtils.copyProperties(entity,updated,"id");
        entity.setUpdatedAt(System.currentTimeMillis());
        repository.save(updated);
    }

    public T get(Integer id) {
        T entity = repository.getOne(id);
        if(entity == null) {
            throw new EntityNotFoundException("No " + clazzName + " with id " + id);
        }
        return entity;
    }

    public void delete(Integer id) {
        T entity = get(id);
        repository.delete(entity);
    }
}
