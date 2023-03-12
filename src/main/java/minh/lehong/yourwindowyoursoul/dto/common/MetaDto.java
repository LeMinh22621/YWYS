package minh.lehong.yourwindowyoursoul.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MetaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("current_page")
    private int page;

    @JsonProperty("limit")
    private int limit;

    @JsonProperty("total_records")
    private long totalRecords;

    @JsonProperty("total_page")
    private long totalPage;

    public MetaDto() {
        super();
    }
}