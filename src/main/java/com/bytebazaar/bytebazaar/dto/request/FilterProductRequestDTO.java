package com.bytebazaar.bytebazaar.dto.request;

import com.bytebazaar.bytebazaar.model.Condizione;
import lombok.Data;

@Data
public class FilterProductRequestDTO {
    private Condizione condizione;
}
