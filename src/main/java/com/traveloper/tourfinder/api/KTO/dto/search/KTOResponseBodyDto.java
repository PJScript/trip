package com.traveloper.tourfinder.api.KTO.dto.search;

import lombok.Data;

@Data
public class KTOResponseBodyDto {
    private KTOResponseItemsDto items;
    private Integer numOfRows;
    private Integer pageNo;
    private Integer totalCount;
}
