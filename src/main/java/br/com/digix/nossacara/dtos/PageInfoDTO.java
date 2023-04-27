package br.com.digix.nossacara.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageInfoDTO {
    private long total;
    private int currentPage;
    private int pageSize;
    private boolean hasPrevious;
    private boolean hasNext;
    private int totalPages;
}
