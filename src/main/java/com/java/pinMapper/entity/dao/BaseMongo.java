package com.java.pinMapper.entity.dao;

import com.java.pinMapper.entity.constant.fields.BaseMongoFields;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Field;


public abstract class BaseMongo implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Field(value = BaseMongoFields.ID)
  private String id;

  @Version
  @Field(value = BaseMongoFields.VERSION)
  private Long version;

  @CreatedDate
  @Field(value = BaseMongoFields.CREATED_DATE)
  private Date createdDate;

  @Field(value = BaseMongoFields.CREATED_BY)
  private String createdBy = "system";

  @LastModifiedDate
  @Field(value = BaseMongoFields.UPDATED_DATE)
  private Date updatedDate;

  @Field(value = BaseMongoFields.UPDATED_BY)
  private String updatedBy = "system";

  @Field(value = BaseMongoFields.STORE_ID)
  private String storeId;

  @Field(value = BaseMongoFields.IS_DELETED)
  private Integer isDeleted = 0;

}
