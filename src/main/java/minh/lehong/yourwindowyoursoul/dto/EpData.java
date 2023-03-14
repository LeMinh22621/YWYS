package minh.lehong.yourwindowyoursoul.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class EpData<T> implements Serializable {

	private static final long serialVersionUID = -7667221873686301566L;

	private T data;

	private Meta meta;
}
