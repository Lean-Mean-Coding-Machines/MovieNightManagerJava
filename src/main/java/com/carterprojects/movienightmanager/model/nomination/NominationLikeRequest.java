package com.carterprojects.movienightmanager.model.nomination;

import lombok.Getter;

@Getter
public class NominationLikeRequest {
    Integer nominationId;
    String watchDate;
    Integer userId;
}
