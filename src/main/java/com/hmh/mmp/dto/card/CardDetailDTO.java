package com.hmh.mmp.dto.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDetailDTO extends CardSaveDTO{
    private Long cardId;
}
