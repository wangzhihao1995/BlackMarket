package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.GoodsPost;
import com.wangzhihao.blackmarket.dto.GetGoodsPostListDto;
import com.wangzhihao.blackmarket.dto.UpdateGoodsPostDto;
import com.wangzhihao.blackmarket.mapper.GoodsPostMapper;
import com.wangzhihao.blackmarket.service.GoodsPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/30.
 *
 * @author Wang Zhihao.
 */
@Service
public class GoodsPostServiceImpl implements GoodsPostService {

    private static final String COURSE_POST_PV_CACHE_KEY = "goods:post:pv:id:%d";
    private static final String STUDENT_VIEWED_POST_CONTACT_CACHE_KEY = "student:%d:viewed:contact:goods:post:%d";

    @Autowired
    GoodsPostMapper goodsPostMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void add(GoodsPost goodsPost) {
        goodsPostMapper.insert(goodsPost);
    }

    @Override
    public GoodsPost getById(Long id) {
        return goodsPostMapper.findGoodsPostById(id);
    }

    @Override
    public List<GoodsPost> getGoodsPostList(GetGoodsPostListDto getGoodsPostListDto) {
        return goodsPostMapper.findGoodsPosts(getGoodsPostListDto);
    }

    @Override
    public void update(UpdateGoodsPostDto updateGoodsPostDto) {
        goodsPostMapper.update(updateGoodsPostDto);
    }

    @Override
    public Long incrPv(GoodsPost goodsPost) {
        Long pv = goodsPost.getPv() + 1;
        String key = String.format(COURSE_POST_PV_CACHE_KEY, goodsPost.getId());
        String val = stringRedisTemplate.opsForValue().get(key);
        if (val != null) {
            pv = Long.parseLong(val) + 1;
        }
        stringRedisTemplate.opsForValue().set(key, pv.toString());
        stringRedisTemplate.expire(key, 14, TimeUnit.DAYS);
        if (pv % 7 == 0) {
            goodsPostMapper.updatePv(goodsPost.getId(), pv);
        }
        return pv;
    }

    @Override
    public Boolean hasViewedPostContact(Long studentId, Long postId) {
        String key = String.format(STUDENT_VIEWED_POST_CONTACT_CACHE_KEY, studentId, postId);
        String val = stringRedisTemplate.opsForValue().get(key);
        return val != null;
    }

    @Override
    public void viewPostContact(Long studentId, Long postId) {
        String key = String.format(STUDENT_VIEWED_POST_CONTACT_CACHE_KEY, studentId, postId);
        stringRedisTemplate.opsForValue().set(key, "");
        stringRedisTemplate.expire(key, 3, TimeUnit.DAYS);
    }
}
