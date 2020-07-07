package com.iexec.common.chain;

import com.iexec.common.contract.generated.IexecHubContract;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ChainCategory {

    private long id;
    private String name;
    private String description;
    private long maxExecutionTime;

    public static ChainCategory toChainCategory(long id, IexecHubContract.Category category) {
        return ChainCategory.builder()
                .id(id)
                .name(category.name)
                .description(category.description)
                .maxExecutionTime(category.workClockTimeRef.longValue() * 1000)
                .build();
    }
}
