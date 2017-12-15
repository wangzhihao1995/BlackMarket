package com.wangzhihao.blackmarket.unittest;

import com.wangzhihao.blackmarket.data.MockDoamin;
import com.wangzhihao.blackmarket.domain.Course;
import com.wangzhihao.blackmarket.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseUnitTest {

    @Autowired
    CourseService courseService;

    @Test
    @Sql("/dev.sql")
    public void testCourseService() {
        Course mockCourse = MockDoamin.mockCourse();
        courseService.add(mockCourse);
        Course course = courseService.getById(mockCourse.getId());
        assertEquals(course.getId(), mockCourse.getId());
        assertEquals(course.getName(), mockCourse.getName());
        assertEquals(course.getTeacher(), mockCourse.getTeacher());
        assertEquals(course.getYear(), mockCourse.getYear());
        assertEquals(course.getSemester(), mockCourse.getSemester());

        mockCourse.setName("DNN");
        mockCourse.setYear(2017L);
        courseService.add(mockCourse);

        mockCourse.setName("GAN");
        mockCourse.setYear(2018L);
        courseService.add(mockCourse);

        List<Course> courseList = courseService.getListByYearAndSemester(2018L, "spring");
        assertEquals(courseList.size(), 2);
        assertEquals(courseList.get(0).getName(), MockDoamin.mockCourse().getName());
        assertEquals(courseList.get(1).getName(), mockCourse.getName());
    }
}
