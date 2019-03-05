package com.iexec.common.replicate;

import com.iexec.common.chain.ContributionAuthorization;
import com.iexec.common.disconnection.RecoverableAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterruptedReplicateModel {

    ContributionAuthorization contributionAuthorization;
    RecoverableAction recoverableAction;
}