package com.neu.dreambuilder.controller.volunteer;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.dreambuilder.common.utils.JwtUtil;
import com.neu.dreambuilder.dto.CommentDto;
import com.neu.dreambuilder.dto.PageExample;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.volunteer.ArticleDto;
import com.neu.dreambuilder.entity.user.IUserDetails;
import com.neu.dreambuilder.entity.volunteer.Article;

import com.neu.dreambuilder.service.volunteer.VolunteerArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;

@RestController
@RequestMapping("/volunteer/article")
@PreAuthorize("hasAuthority('VOLUNTEER')")
@Api(tags = "志愿者端文章相关信息接口")
public class VolunteerArticleController {

    @Resource
    private VolunteerArticleService volunteerArticleService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     *获取志愿者界面滚动文章
     * @return
     */
    @GetMapping("/random")
    @ApiOperation(value = "志愿者页面滑动的文章")
    public Result<ArticleDto> getRandomArticle(){
        return volunteerArticleService.getRandomArticle();
    }

    @PostMapping("/list")
    @ApiOperation("文章列表（分页）")
    public Result<PageInfo<ArticleDto>> getAllArticle(@RequestBody PageExample<Article> articlePage){
        PageHelper.startPage(articlePage.getPageNum(),articlePage.getPageSize());

        List<ArticleDto> articleDtos = volunteerArticleService.getAllArticle(articlePage.getExample().getTitle());
        PageInfo<ArticleDto> pageInfo =new PageInfo<>(articleDtos);
        return Result.success(pageInfo);
    }



    @GetMapping("/detail/{id}")
    @ApiOperation("一篇文章的详细信息")
    public Result<Article> getArticleDetail(@ApiParam(name = "id",value = "文章的id") @PathVariable String id){
        long idt = Long.parseLong(id);
        return volunteerArticleService.getArticleDetails(idt);
    }

    @GetMapping("/detail/{id}/comment")
    @ApiOperation("一篇文章的评论")
    public Result<List<CommentDto>> getArticleDetailComment(@ApiParam(name = "id",value = "文章的id")  @PathVariable String id){
        long idt = Long.parseLong(id);

        return volunteerArticleService.getArticleComments(idt);
    }

    @PostMapping("/comment")
    @ApiOperation("被收藏的文章")
    public Result<PageInfo<ArticleDto>> getCollectedArticle(@RequestBody PageExample<Object> articlePage, HttpServletRequest request){
        PageHelper.startPage(articlePage.getPageNum(),articlePage.getPageSize());
        String token = request.getHeader("token");
        String redisKey = JwtUtil.parseJWT(token).getBody().getSubject();
        IUserDetails userDetails = JSON.parseObject(stringRedisTemplate.opsForValue().get(redisKey), IUserDetails.class);
        List<ArticleDto> articleColleted = volunteerArticleService.getArticleColleted(userDetails.getId());
        PageInfo<ArticleDto> pageInfo  = new PageInfo<>(articleColleted);

        return Result.success(pageInfo);
    }


}
