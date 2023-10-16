package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.Impl.SectionDAOImpl;
import com.onlinelearning.DAL.SectionDAO;
import com.onlinelearning.Enums.SectionStatus;
import com.onlinelearning.Models.Section;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.SectionService;
import java.util.List;

public class SectionServiceImpl implements SectionService {

    private final SectionDAO sectionDAO = new SectionDAOImpl();

    @Override
    public Section getSectionById(Integer id) {
        if (id == null) {
            return null;
        }
        return sectionDAO.getSection(id);
    }

    @Override
    public List<Section> getSectionsByCourseId(Integer courseId) {
        if (courseId == null) {
            return null;
        }
        return sectionDAO.getSectionsByCourseId(courseId);
    }

    @Override
    public Section createSection(Section section) throws Exception {
        if (section == null) {
            throw new Exception("Section can not be null");
        }
        section.setStatus(SectionStatus.ACTIVE);
        return sectionDAO.createSection(section);
    }

    @Override
    public Section updateSection(Section section) throws Exception {
        Section updatedSection = sectionDAO.updateSection(section);
        if (updatedSection == null) {
            throw new Exception("Updated section failed!");
        }
        return updatedSection;
    }

    @Override
    public Section updateSectionOrderNumber(Integer sectionId, Integer previousSectionId, Integer nextSectionId) throws Exception {
        Section currentSection = sectionDAO.getSection(sectionId);
        if (currentSection == null) {
            throw new Exception("Section is invalid!");
        }
        Float previousSectionOrderNumber = 0f, nextSectionOrderNumber = null;
        if (previousSectionId != null) {
            Section previousSection = sectionDAO.getSection(previousSectionId);
            if (!previousSection.getCourseId().equals(currentSection.getCourseId())) {
                throw new Exception("Previous section is not in the same course!");
            }
            previousSectionOrderNumber = previousSection.getOrderNumber();
        }
        if (nextSectionId != null) {
            Section nextSection = sectionDAO.getSection(nextSectionId);
            if (!nextSection.getCourseId().equals(currentSection.getCourseId())) {
                throw new Exception("Next section is not in the same course!");
            }
            nextSectionOrderNumber = nextSection.getOrderNumber();
        } else {
            nextSectionOrderNumber = sectionDAO.getNewOrderNumberByCourseId(currentSection.getCourseId());
        }
        Float currentSectionNewOrderNumber = (previousSectionOrderNumber + nextSectionOrderNumber) / 2f;
        currentSection.setOrderNumber(currentSectionNewOrderNumber);
        Section updatedSection = updateSection(currentSection);
        return updatedSection;
    }

    @Override
    public Section deleteSection(Section section) throws Exception {
        Section deletedSection = sectionDAO.deleteSection(section);
        if (deletedSection == null) {
            throw new Exception("Deleted section failed!");
        }
        return deletedSection;
    }

}
