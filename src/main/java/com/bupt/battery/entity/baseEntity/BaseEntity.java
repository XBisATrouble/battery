package com.bupt.battery.entity.baseEntity;

import com.bupt.battery.entity.baseEntity.AbstractEntity;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> extends AbstractEntity<ID> {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private ID id;


    public BaseEntity() {
    }

    public ID getId() {
        return this.id;
    }

    public void setId(ID id) {
        this.id = id;
    }

}
