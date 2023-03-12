package minh.lehong.yourwindowyoursoul.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class EntityCommon{
    @Column(name = "create_date", updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("create_date")
    private Date createDate;

    @Column(name = "update_date", updatable = true)
    @JsonProperty("update_date")
    private Date updatedDate;

    @Column(name = "is_active", length = 1)
    @JsonProperty("is_active")
    private int isActive;

    @Column(name = "is_deleted", length = 1)
    @JsonProperty("is_deleted")
    private int isDeleted;

    @PrePersist
    protected void onCreate() {
        if (createDate == null) {
            createDate = new Date();
        }
        if (updatedDate == null) {
            updatedDate = new Date();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        if (updatedDate == null) {
            updatedDate = new Date();
        }
    }
}
