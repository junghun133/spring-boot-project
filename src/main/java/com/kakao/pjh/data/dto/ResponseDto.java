package com.kakao.pjh.data.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto implements Response {
    String message;
}
