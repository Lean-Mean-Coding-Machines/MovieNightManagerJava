package com.carterprojects.movienightmanager.repository.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public
class CommunityUserKey implements Serializable {

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "community_id")
    Integer communityId;

}
