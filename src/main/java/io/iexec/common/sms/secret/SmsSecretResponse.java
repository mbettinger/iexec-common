package io.iexec.common.sms.secret;

import io.iexec.common.sms.SmsAbstractResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class SmsSecretResponse extends SmsAbstractResponse {

    private SmsSecretResponseData data;
}