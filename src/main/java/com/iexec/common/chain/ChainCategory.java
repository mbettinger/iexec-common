/*
 * Copyright 2020 IEXEC BLOCKCHAIN TECH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iexec.common.chain;

import com.iexec.common.contract.generated.IexecHubContract;
import lombok.*;

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

    public static ChainCategory tuple2ChainCategory(long id, IexecHubContract.Category category) {
        return ChainCategory.builder()
                .id(id)
                .name(category.name)
                .description(category.description)
                .maxExecutionTime(category.workClockTimeRef.longValue() * 1000)
                .build();
    }
}
