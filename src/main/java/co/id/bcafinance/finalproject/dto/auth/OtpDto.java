package co.id.bcafinance.finalproject.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OtpDto {
    @NotNull(message = "OTP Tidak Boleh NULL")
    @NotBlank(message = "OTP Tidak Boleh Blank")
    @NotEmpty(message = "OTP Tidak Boleh Kosong")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}


