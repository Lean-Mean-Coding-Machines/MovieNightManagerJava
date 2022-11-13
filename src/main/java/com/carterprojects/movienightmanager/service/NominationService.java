package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.model.NominationRequest;
import com.carterprojects.movienightmanager.repository.models.Nomination;

import java.util.List;

public interface NominationService {
    List<Nomination> getAllNominationsByCurrentSegment();
    List<Nomination> getAllNominationsByUserId(Integer userID);
    Nomination createNominationFromRequest(NominationRequest nominationRequest);
}
