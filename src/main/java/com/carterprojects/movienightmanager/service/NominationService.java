package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.nomination.NominationRequest;
import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;
import com.carterprojects.movienightmanager.repository.models.Nomination;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;

import java.util.List;

public interface NominationService {
    List<Nomination> getAllNominationsBySegmentId(Integer segmentId);
    List<Nomination> getAllNominationsByUserId(Integer userID);
    Nomination createNominationFromRequest(NominationRequest nominationRequest) throws MnmAppException;
    Nomination saveNewNomination(NominationRequest nominationRequest, MovieNightSegment segment, AppUser user);
    void deleteNomination(Integer nominationId, Integer userId, Integer communityId) throws MnmAppException;
}
