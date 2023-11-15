package com.onlinelearning.Services;

import com.onlinelearning.Models.Section;
import java.util.List;

public interface SectionService {

    Section getSectionById(Integer id);

    List<Section> getSectionsByCourseId(Integer courseId);

    List<Section> getActiveSectionByCourseId(Integer courseId);

    List<Section> getHiddenSectionByCourseId(Integer courseId);

    Section createSection(Section section) throws Exception;

    Section updateSection(Section section) throws Exception;

    Section updateSectionOrderNumber(Integer sectionId, Integer previousSectionId, Integer nextSectionId) throws Exception;

    Section deleteSection(Section section) throws Exception;

}
