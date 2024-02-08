package com.carterprojects.movienightmanager.model.nomination;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class NominationDeleteRequest {
    Integer segmentId;
    Integer userId;
    Integer nominationId;
}
