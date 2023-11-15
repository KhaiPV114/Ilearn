package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.Impl.TrackingDAOImpl;
import com.onlinelearning.DAL.TrackingDAO;
import com.onlinelearning.Enums.TrackingStatus;
import com.onlinelearning.Models.Tracking;
import com.onlinelearning.Services.TrackingService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackingServiceImpl implements TrackingService {

    private final TrackingDAO trackingDAO = new TrackingDAOImpl();

    @Override
    public Tracking getUserTrackingByLessonId(Integer userId, Integer lessonId) {
        if (userId == null || lessonId == null) {
            return null;
        }
        return trackingDAO.getTracking(userId, lessonId);
    }

    @Override
    public List<Tracking> getUserTrackingByCourseId(Integer userId, Integer courseId) {
        if (userId == null || courseId == null) {
            return null;
        }
        return trackingDAO.getTrackingsByUserIdAndCourseId(userId, courseId);
    }

    @Override
    public List<Tracking> getUserTrackingByCourseIdAndStatus(Integer userId, Integer courseId, TrackingStatus status) {
        if (userId == null || courseId == null) {
            return null;
        }
        return trackingDAO.getTrackingsByUserIdAndCourseIdAndStatus(userId, courseId, status);
    }

    @Override
    public Tracking changeUserTracking(Integer userId, Integer lessonId) {
        Tracking tracking = trackingDAO.getTracking(userId, lessonId);
        if (tracking == null) {
            Tracking newTracking = Tracking.builder()
                    .userId(userId)
                    .lessonId(lessonId)
                    .status(TrackingStatus.FINISHED)
                    .build();
            return trackingDAO.createTracking(newTracking);
        }
        if (tracking.getStatus() == TrackingStatus.FINISHED) {
            tracking.setStatus(TrackingStatus.STUDYING);
        } else {
            tracking.setStatus(TrackingStatus.FINISHED);
        }
        return trackingDAO.updateTracking(tracking);
    }

    @Override
    public Map<Integer, TrackingStatus> mapUserTrackingLessonsByUserIdAndCourseId(Integer userId, Integer courseId) {
        if (userId == null || courseId == null) {
            return null;
        }
        List<Tracking> trackingList = getUserTrackingByCourseId(userId, courseId);
        Map<Integer, TrackingStatus> trackingMap = new HashMap<>();
        for (Tracking tracking : trackingList) {
            trackingMap.put(tracking.getLessonId(), tracking.getStatus());
        }
        return trackingMap;
    }

}
