package com.lswstudy.eduorder.client;

import com.lswstudy.commonutils.CourseWebVoOrder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lswstudy
 * @create 2022-02-28-15:09
 */
@Component
@FeignClient("service-edu")
public interface EduClient {
    @ApiOperation("根据课程的id来查询课程信息")
    @GetMapping("/eduservice/coursefront/getCourseInfoById/{id}")
    public CourseWebVoOrder getCourseInfoById(@PathVariable("id") String id);
}
