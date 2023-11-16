package com.onlinelearning.DAL;

import com.onlinelearning.Enums.SectionStatus;
import com.onlinelearning.Models.Section;
import java.util.List;

public interface SectionDAO {

    Float getNewOrderNumberByCourseId(Integer courseId);

    Section createSection(Section section);

    Section getSection(Integer id);

    List<Section> getSectionsByCourseId(Integer courseId);

    List<Section> getSectionsByCourseIdAndStatus(Integer courseId, SectionStatus status);

    Section updateSection(Section section);

    Section deleteSection(Section section);

}
