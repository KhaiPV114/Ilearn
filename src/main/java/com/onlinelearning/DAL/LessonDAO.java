package com.onlinelearning.DAL;

import com.onlinelearning.Enums.LessonStatus;
import com.onlinelearning.Models.Lesson;
import java.util.List;

public interface LessonDAO {

    Float getNewOrderNumberBySectionId(Integer sectionId);

    Lesson createLesson(Lesson lesson);

    Lesson getLesson(Integer id);

    List<Lesson> getLessonsBySectionId(Integer sectionId);

    List<Lesson> getLessonsBySectionIdAndStatus(Integer sectionId, LessonStatus status);

    Lesson updateLesson(Lesson lesson);

    Lesson deleteLesson(Lesson lesson);

}
