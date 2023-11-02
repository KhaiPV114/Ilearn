package com.onlinelearning.DAL;

import com.onlinelearning.Models.Lesson;
import java.util.List;

public interface LessonDAO {

    Float getNewOrderNumberBySectionId(Integer sectionId);

    Lesson createLesson(Lesson lesson);

    Lesson getLesson(Integer id);

    List<Lesson> getLessonsBySectionId(Integer sectionId);

    Lesson updateLesson(Lesson lesson);

    Lesson deleteLesson(Lesson lesson);

}
