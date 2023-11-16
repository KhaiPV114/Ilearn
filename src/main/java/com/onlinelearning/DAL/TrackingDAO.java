package com.onlinelearning.DAL;

import com.onlinelearning.Enums.TrackingStatus;
import com.onlinelearning.Models.Tracking;
import java.util.List;

public interface TrackingDAO {

    Tracking createTracking(Tracking tracking);

    Tracking getTracking(Integer userId, Integer lessonId);

    Tracking updateTracking(Tracking tracking);

    Tracking deleteTracking(Tracking tracking);

    List<Tracking> getTrackingsByUserIdAndCourseId(Integer userId, Integer courseId);

    List<Tracking> getTrackingsByUserIdAndCourseIdAndStatus(Integer userId, Integer courseId, TrackingStatus status);

}
