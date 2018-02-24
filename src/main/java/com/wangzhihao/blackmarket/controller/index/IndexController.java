package com.wangzhihao.blackmarket.controller.index;

import com.google.common.collect.Lists;
import com.wangzhihao.blackmarket.domain.Course;
import com.wangzhihao.blackmarket.domain.CourseSchedule;
import com.wangzhihao.blackmarket.dto.CourseRespDto;
import com.wangzhihao.blackmarket.dto.GetCourseListDto;
import com.wangzhihao.blackmarket.service.CourseScheduleService;
import com.wangzhihao.blackmarket.service.CourseService;
import com.wangzhihao.blackmarket.utils.WechatUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
@RestController
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getCourseList(GetCourseListDto getCourseListDto) {
        return new ResponseEntity<>("HI ALL,<br>" +
                "SORRY FOR THE DELAY.<br>" +
                "MY LAPTOP WAS LOST.<br>" +
                "I AM TRYING TO MAKE BLACK MARKET WORK AGAIN.<br>" +
                "2018.02.25<br>", HttpStatus.OK);
    }

}
