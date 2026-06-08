package com.interviewtracker.dao;

import com.interviewtracker.model.Revision;
import java.util.List;

public interface RevisionDAO {
    boolean addRevision(Revision revision);
    boolean updateRevision(Revision revision);
    boolean deleteRevision(int revisionId);
    List<Revision> getAllRevisionsByUser(int userId);
    List<Revision> getPendingRevisionsByUser(int userId);
    boolean markAsCompleted(int revisionId);
}
