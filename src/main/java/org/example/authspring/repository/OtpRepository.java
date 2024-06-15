package org.example.authspring.repository;

import org.apache.ibatis.annotations.*;
import org.example.authspring.model.dto.request.OtpRequest;
import org.example.authspring.model.entity.Otp;


import java.time.LocalDateTime;
import java.util.UUID;

@Mapper
public interface OtpRepository {

    @Select("SELECT * FROM otps WHERE user_id = #{userId} ORDER BY issued_at DESC LIMIT 1")
    @Results(id = "otpMapper",value = {
            @Result(property = "otpId",column = "opt_id"),
            @Result(property = "otpCode",column = "otp_code"),
            @Result(property = "issuedAt",column = "issued_at")
    })
    Otp findOtpByUserId(UUID userId);

    @Insert("INSERT INTO otps (otp_code, issued_at, expiration, verify, user_id) VALUES (#{otp.otpCode}, #{otp.issuedAt}, #{otp.expiration}, #{otp.verify}, #{otp.userId})")
    void saveOpt(@Param("otp") OtpRequest otpRequest);

    @Select("SELECT * FROM otps WHERE otp_code = #{otpCode}")
    @ResultMap("otpMapper")
    Otp findOtpByOtpCode(String otpCode);

    @Update("UPDATE otps SET verify = true WHERE otp_code = #{otpCode}")
    void verify(String otpCode);
    @Update("""
    UPDATE otps SET otp_code = #{otp.otpCode},issued_at= #{otp.issuedAt},expiration = #{otp.expiration},verify = #{otp.verify}
    WHERE user_id = #{otp.userId}
""")
    void updateResendOtpForUser(@Param("otp") OtpRequest otpRequest);
}
