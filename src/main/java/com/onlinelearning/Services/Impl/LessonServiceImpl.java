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
    public List<Lesson> getActiveLessonsBySectionId(Integer sectionId) {
        if (sectionId == null) {
            return null;
        }
        return lessonDAO.getLessonsBySectionIdAndStatus(sectionId, LessonStatus.ACTIVE);
    }

    @Override
    public List<Lesson> getHiddenLessonsBySectionId(Integer sectionId) {
        if (sectionId == null) {
            return null;
        }
        return lessonDAO.getLessonsBySectionIdAndStatus(sectionId, LessonStatus.HIDDEN);
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
    public Lesson updateLessonOrderNumber(Integer lessonId, Integer previousLessonId, Integer nextLessonId, Integer sectionId) throws Exception {
        //Get current moving lesson
        Lesson currentLesson = lessonDAO.getLesson(lessonId);

        //If current lesson is null, return invalid lesson
        if (currentLesson == null) {
            throw new Exception("Lesson is invalid!");
        }

        //Default value for previousLessonOrderNumber is 0 
        //and for nextLessonOrderNumber is a new getOrderNumber()
        Float previousLessonOrderNumber = 0f, nextLessonOrderNumber = null;

        //Declare previous and next lession to check section later if both is not null
        Lesson previousLesson = null, nextLesson = null;

        if (previousLessonId != null) {
            //Get previous lesson
            previousLesson = lessonDAO.getLesson(previousLessonId);
            previousLessonOrderNumber = previousLesson.getOrderNumber();
        }

        if (nextLessonId != null) {
            //Get next lesson
            nextLesson = lessonDAO.getLesson(nextLessonId);
            nextLessonOrderNumber = nextLesson.getOrderNumber();
        } else {
            //If next lesson is null, get default value for nextLessonOrderNumber
            nextLessonOrderNumber = lessonDAO.getNewOrderNumberBySectionId(currentLesson.getSectionId());
        }

        //If both previous and next lesson is not null, we must check if 
        //these are in the same section or not, if not it is illegal
        if (previousLesson != null && nextLesson != null) {
            if (!previousLesson.getSectionId().equals(nextLesson.getSectionId())) {
                throw new Exception("Previous and next lesson must be in the same section!");
            } else {
                //If move to the new section, update id to the new section id
                currentLesson.setSectionId(previousLesson.getSectionId());
            }
        }

        //Update the order number by getting the average of previous and next section
        Float currentLessonNewOrderNumber = (previousLessonOrderNumber + nextLessonOrderNumber) / 2f;

        //If both previous and next lesson are null, set new lesson id
        if (previousLesson == null && nextLesson == null) {
            currentLesson.setSectionId(sectionId);
            //If both null, set order number to new getOrderNumber()
            currentLessonNewOrderNumber = nextLessonOrderNumber;
        } else {
            //If just previous lesson not null, set current lession section id to previous lesson id
            if (previousLesson != null && !previousLesson.getSectionId().equals(currentLesson.getSectionId())) {
                currentLesson.setSectionId(previousLesson.getSectionId());
            }

            //If just next lesson not null, set current lession section id to next lesson id
            if (nextLesson != null && !nextLesson.getSectionId().equals(currentLesson.getSectionId())) {
                currentLesson.setSectionId(nextLesson.getSectionId());
            }
        }

        //Set new order number
        currentLesson.setOrderNumber(currentLessonNewOrderNumber);

        //Update to db
        Lesson updatedLesson = updateLesson(currentLesson);

        return updatedLesson;
    }

    @Override
    public Lesson deleteLesson(Lesson lesson) throws Exception {
        lesson.setStatus(LessonStatus.DELETED);
        Lesson deletedLesson = lessonDAO.updateLesson(lesson);
        if (deletedLesson == null) {
            throw new Exception("Deleted lesson failed!");
        }
        return deletedLesson;
    }

}
