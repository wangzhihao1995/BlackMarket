package com.wangzhihao.blackmarket.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wangzhihao.blackmarket.domain.GoodsPost;
import com.wangzhihao.blackmarket.domain.Student;
import com.wangzhihao.blackmarket.domain.WechatUser;
import com.wangzhihao.blackmarket.dto.*;
import com.wangzhihao.blackmarket.exception.GoodsPostNotFoundException;
import com.wangzhihao.blackmarket.exception.UpdatePostException;
import com.wangzhihao.blackmarket.service.GoodsPostService;
import com.wangzhihao.blackmarket.service.StudentService;
import com.wangzhihao.blackmarket.service.WechatUserService;
import com.wangzhihao.blackmarket.utils.WechatUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
@RestController
@RequestMapping(value = "/api/goods/post")
public class GoodsPostController {

    @Autowired
    GoodsPostService goodsPostService;

    @Autowired
    StudentService studentService;

    @Autowired
    WechatUserService wechatUserService;

    @Autowired
    WechatUtils wechatUtils;


    private List<GoodsPostResp> buildGoodsPostResps(List<GoodsPost> goodsPosts) {
        List<GoodsPostResp> resps = Lists.newArrayList();
        for (GoodsPost goodsPost : goodsPosts) {
            GoodsPostResp goodsPostResp = new GoodsPostResp();
            goodsPostResp.setId(goodsPost.getId());
            Student student = studentService.getById(goodsPost.getStudentId());
            WechatUser wechatUser = wechatUserService.getByOpenId(student.getOpenId());

            StudentResp studentResp = new StudentResp();
            studentResp.setId(student.getId());
            studentResp.setUsername(wechatUser.getNickName());
            studentResp.setMobile(student.getMobile());
            studentResp.setType(student.getType());
            studentResp.setGrade(student.getGrade());
            studentResp.setStatus(student.getStatus());
            studentResp.setAvatarUrl(wechatUser.getAvatarUrl());
            studentResp.setCreateTime(student.getCreateTime());
            studentResp.setUpdateTime(student.getUpdateTime());

            goodsPostResp.setStudent(studentResp);
            goodsPostResp.setStatus(goodsPost.getStatus());
            goodsPostResp.setMobileSwitch(goodsPost.getMobileSwitch());
            goodsPostResp.setWechat(goodsPost.getWechat());
            goodsPostResp.setTitle(goodsPost.getTitle());
            goodsPostResp.setContent(goodsPost.getContent());
            goodsPostResp.setPv(goodsPost.getPv());
            goodsPostResp.setCreateTime(goodsPost.getCreateTime());
            goodsPostResp.setUpdateTime(goodsPost.getUpdateTime());

            resps.add(goodsPostResp);
        }
        return resps;
    }

    @ApiOperation(value = "Get Goods Post List")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getGoodsPostList(GetGoodsPostListDto getGoodsPostListDto) {
        List<GoodsPost> goodsPosts = goodsPostService.getGoodsPostList(getGoodsPostListDto);
        List<GoodsPostResp> resps = buildGoodsPostResps(goodsPosts);
        return new ResponseEntity<>(resps, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Goods Post List")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/mine", method = RequestMethod.GET)
    ResponseEntity getMyGoodsPostList(GetGoodsPostListDto getGoodsPostListDto) {
        WechatUser wechatUser = wechatUtils.requireWechatUser();
        Student student = studentService.getByOpenId(wechatUser.getOpenId());
        getGoodsPostListDto.setStudentId(student.getId());
        List<GoodsPost> goodsPosts = goodsPostService.getGoodsPostList(getGoodsPostListDto);
        List<GoodsPostResp> resps = buildGoodsPostResps(goodsPosts);
        return new ResponseEntity<>(resps, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Goods Post By ID")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity getGoodsPostById(@PathVariable("id") long id) {
        WechatUser currentWechatUser = wechatUtils.requireWechatUser();
        Student currentStudent = studentService.getByOpenId(currentWechatUser.getOpenId());

        GoodsPost goodsPost = goodsPostService.getById(id);
        if (goodsPost != null) {
            Long pv = goodsPost.getPv();
            if (!currentStudent.getId().equals(goodsPost.getStudentId())) {
                pv = goodsPostService.incrPv(goodsPost);
            }
            Student student = studentService.getById(goodsPost.getStudentId());
            WechatUser wechatUser = wechatUserService.getByOpenId(student.getOpenId());

            StudentResp studentResp = new StudentResp();
            studentResp.setId(student.getId());
            studentResp.setUsername(wechatUser.getNickName());
            studentResp.setMobile(student.getMobile());
            studentResp.setType(student.getType());
            studentResp.setGrade(student.getGrade());
            studentResp.setStatus(student.getStatus());
            studentResp.setAvatarUrl(wechatUser.getAvatarUrl());
            studentResp.setCreateTime(student.getCreateTime());
            studentResp.setUpdateTime(student.getUpdateTime());

            GoodsPostResp goodsPostResp = new GoodsPostResp();
            goodsPostResp.setId(goodsPost.getId());
            goodsPostResp.setStudent(studentResp);
            goodsPostResp.setStatus(goodsPost.getStatus());
            goodsPostResp.setMobileSwitch(goodsPost.getMobileSwitch());
            goodsPostResp.setWechat(goodsPost.getWechat());
            goodsPostResp.setTitle(goodsPost.getTitle());
            goodsPostResp.setContent(goodsPost.getContent());
            goodsPostResp.setPv(pv);
            goodsPostResp.setCreateTime(goodsPost.getCreateTime());
            goodsPostResp.setUpdateTime(goodsPost.getUpdateTime());
            goodsPostResp.setHasViewedContact(
                    goodsPostService.hasViewedPostContact(currentStudent.getId(), id));
            return new ResponseEntity<>(goodsPostResp, HttpStatus.OK);
        }
        throw new GoodsPostNotFoundException();
    }

    @ApiOperation(value = "Create New Goods Post")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "", method = RequestMethod.POST)
    ResponseEntity createNewGoodsPost(@RequestBody AddGoodsPostDto addGoodsPostDto) {
        WechatUser wechatUser = wechatUtils.requireWechatUser();
        Student student = studentService.getByWechatUserId(wechatUser.getId());
        addGoodsPostDto.setStudentId(student.getId());
        GoodsPost goodsPost = GoodsPost.getByAddGoodsPostDto(addGoodsPostDto);
        goodsPostService.add(goodsPost);
        return new ResponseEntity<>(Maps.newHashMap(), HttpStatus.OK);
    }

    @ApiOperation(value = "Update Goods Post")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updateGoodsPost(@PathVariable("id") long id,
                                   @RequestBody UpdateGoodsPostDto updateGoodsPostDto) {
        WechatUser wechatUser = wechatUtils.requireWechatUser();
        Student student = studentService.getByWechatUserId(wechatUser.getId());
        GoodsPost goodsPost = goodsPostService.getById(id);
        if (goodsPost != null) {
            if (goodsPost.getStudentId().equals(student.getId())) {
                updateGoodsPostDto.setId(id);
                goodsPostService.update(updateGoodsPostDto);
                Map<String, String> resp = Maps.newHashMap();
                resp.put("status", "ok");
                return new ResponseEntity<>(resp, HttpStatus.OK);
            }
            throw new UpdatePostException("Cannot update other user's post!");
        }
        throw new GoodsPostNotFoundException();
    }

    @ApiOperation(value = "Update Goods Post")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/viewcount", method = RequestMethod.GET)
    ResponseEntity getRemainingViewContactCount() {
        WechatUser wechatUser = wechatUtils.requireWechatUser();
        Student student = studentService.getByWechatUserId(wechatUser.getId());
        Integer remainingViewCount = studentService.getRemainingViewContactCount(student.getId());
        Map<String, Integer> resp = Maps.newHashMap();
        resp.put("remainingViewCount", remainingViewCount);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @ApiOperation(value = "Update Goods Post")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/viewcount", method = RequestMethod.PUT)
    ResponseEntity viewGoodsPostContact(@RequestBody UpdateGoodsPostViewCountDto updateGoodsPostViewCountDto) {
        WechatUser wechatUser = wechatUtils.requireWechatUser();
        Student student = studentService.getByWechatUserId(wechatUser.getId());
        goodsPostService.viewPostContact(student.getId(), updateGoodsPostViewCountDto.getPostId());
        studentService.incrViewContactCount(student.getId());
        return new ResponseEntity<>(Maps.newHashMap(), HttpStatus.OK);
    }
}
