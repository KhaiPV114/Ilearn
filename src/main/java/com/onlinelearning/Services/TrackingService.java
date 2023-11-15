package com.onlinelearning.Services;

import com.onlinelearning.Enums.TrackingStatus;
import java.util.List;

import com.onlinelearning.Models.Tracking;
import java.util.Map;

public interface TrackingService {

    Tracking getUserTrackingByLessonId(Integer userId, Integer lessonId);

    List<Tracking> getUserTrackingByCourseId(Integer userId, Integer courseId);

    List<Tracking> getUserTrackingByCourseIdAndStatus(Integer userId, Integer courseId, TrackingStatus status);

    Tracking changeUserTracking(Integer userId, Integer lessonId);

    Map<Integer, TrackingStatus> mapUserTrackingLessonsByUserIdAndCourseId(Integer userId, Integer courseId);
}
