package com.server.EZY.model.plan.tag.dto;

import com.server.EZY.model.plan.tag.embedded_type.Color;
import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class TagGetDto {
    private String tag;
    private Color color;
}
