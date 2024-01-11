package com.carterprojects.movienightmanager.repository.models;

import com.carterprojects.movienightmanager.repository.models.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

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
