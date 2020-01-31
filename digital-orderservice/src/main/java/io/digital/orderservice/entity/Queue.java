package io.digital.orderservice.entity;

import io.digital.orderservice.exception.ExceedQueueException;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "queues")
public class Queue extends BaseEntity {
    private Integer shopId;
    private Integer queueNumber;
    private Integer endIndex = 0;
    private Integer size;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(Integer queueNumber) {
        this.queueNumber = queueNumber;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void enqueue() {
        endIndex++;
        if(endIndex > size) {
            throw new ExceedQueueException();
        }
    }

    public void dequeue() {
        endIndex--;
    }
}
