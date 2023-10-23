package com.onlinelearning.Services;

import com.onlinelearning.Models.Lesson;
import java.util.List;

public interface LessonService {

    Lesson getLessonById(Integer id);

    List<Lesson> getLessonsBySectionId(Integer sectionId);

    Lesson createLesson(Lesson lesson) throws Exception;

    Lesson updateLesson(Lesson lesson) throws Exception;

    Lesson updateLessonOrderNumber(Integer lessonId, Integer previousLessonId, Integer nextLessonId, Integer sectionId) throws Exception;

    Lesson deleteLesson(Lesson lesson) throws Exception;

}
