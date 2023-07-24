package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.nomination.NominationRequest;
import com.carterprojects.movienightmanager.repository.models.Nomination;

import java.util.List;

public interface NominationService {
    List<Nomination> getAllNominationsByCurrentSegment();
    List<Nomination> getAllNominationsByUserId(Integer userID);
    Nomination createNominationFromRequest(NominationRequest nominationRequest) throws MnmAppException;
}
