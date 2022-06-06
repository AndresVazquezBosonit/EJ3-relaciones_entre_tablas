package CRUDvalidacionDTOSmodelMapper.insfrastructure.exceptions;

import lombok.Getter;

import java.util.Date;
@Getter
public class CustomError {
    private final Date timestamps;
    private final int httpCode;
    private final String message;

    public CustomError(Date timestamps, int httpCode, String message) {
        this.timestamps = timestamps;
        this.httpCode = httpCode;
        this.message = message;
    }
}


