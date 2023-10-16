package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.Impl.LessonDAOImpl;
import com.onlinelearning.DAL.LessonDAO;
import com.onlinelearning.Enums.LessonStatus;
import com.onlinelearning.Models.Lesson;
import com.onlinelearning.Services.LessonService;
import java.util.List;

public class LessonServiceImpl implements LessonService {

    private final LessonDAO lessonDAO = new LessonDAOImpl();

    @Override
    public Lesson getLessonById(Integer id) {
        if (id == null) {
            return null;
        }
        return lessonDAO.getLesson(id);
    }

    @Override
    public List<Lesson> getLessonsBySectionId(Integer sectionId) {
        if (sectionId == null) {
            return null;
        }
        return lessonDAO.getLessonsBySectionId(sectionId);
    }

    @Override
    public Lesson createLesson(Lesson lesson) throws Exception {
        if (lesson == null) {
            throw new Exception("Lesson can not be null");
        }
        lesson.setStatus(LessonStatus.ACTIVE);
        return lessonDAO.createLesson(lesson);
    }

    @Override
    public Lesson updateLesson(Lesson lesson) throws Exception {
        Lesson updatedLesson = lessonDAO.updateLesson(lesson);
        if (updatedLesson == null) {
            throw new Exception("Updated lesson failed!");
        }
        return updatedLesson;
    }

    @Override
    public Lesson updateLessonOrderNumber(Integer lessonId, Integer previousLessonId, Integer nextLessonId) throws Exception {
        Lesson currentLesson = lessonDAO.getLesson(lessonId);
        if (currentLesson == null) {
            throw new Exception("Lesson is invalid!");
        }
        Float previousLessonOrderNumber = 0f, nextLessonOrderNumber = null;
        if (previousLessonId != null) {
            Lesson previousLesson = lessonDAO.getLesson(previousLessonId);
            if (!previousLesson.getSectionId().equals(currentLesson.getSectionId())) {
                throw new Exception("Previous lesson is not in the same section!");
            }
            previousLessonOrderNumber = previousLesson.getOrderNumber();
        }
        if (nextLessonId != null) {
            Lesson nextLesson = lessonDAO.getLesson(nextLessonId);
            if (!nextLesson.getSectionId().equals(currentLesson.getSectionId())) {
                throw new Exception("Next lesson is not in the same section!");
            }
            nextLessonOrderNumber = nextLesson.getOrderNumber();
        } else {
            nextLessonOrderNumber = lessonDAO.getNewOrderNumberBySectionId(currentLesson.getSectionId());
        }
        Float currentLessonNewOrderNumber = (previousLessonOrderNumber + nextLessonOrderNumber) / 2f;
        currentLesson.setOrderNumber(currentLessonNewOrderNumber);
        Lesson updatedLesson = updateLesson(currentLesson);
        return updatedLesson;
    }

    @Override
    public Lesson deleteLesson(Lesson lesson) throws Exception {
        Lesson deletedLesson = lessonDAO.deleteLesson(lesson);
        if (deletedLesson == null) {
            throw new Exception("Deleted lesson failed!");
        }
        return deletedLesson;
    }

}
