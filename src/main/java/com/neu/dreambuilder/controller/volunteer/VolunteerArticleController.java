package com.neu.dreambuilder.controller.volunteer;


import com.neu.dreambuilder.dto.PageExample;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.volunteer.VolunteerArticleDto;
import com.neu.dreambuilder.entity.volunteer.Article;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteer/article")
@PreAuthorize("hasAuthority('VOLUNTEER')")
@Api(value = "志愿者端文章相关信息接口")
public class VolunteerArticleController {

    /**
     *获取志愿者界面滚动文章
     * @return
     */
    @GetMapping("/random")
    @ApiOperation(value = "志愿者页面滑动的文章")
    public Result<VolunteerArticleDto> getRandomArticle(){
        return null;
    }

    @PostMapping("/list")
    @ApiOperation("文章列表（分页）")
    public Result<PageExample<VolunteerArticleDto>> getAllArticle(@RequestBody PageExample<Object> articlePage){
        return null;
    }

    @PostMapping("/search")
    @ApiOperation("搜索文章（分页）")
    public Result<PageExample<VolunteerArticleDto>> getSearchArticle(@RequestBody PageExample<Article> articlePage){
        return null;
    }

    @GetMapping("/detail/{id}")
    @ApiOperation("一篇文章的详细信息")
    public Result<Article> getArticleDetail(@ApiParam(name = "id",value = "文章的id") Long id){
        return null;
    }

    @GetMapping("/detail/{id}/comment")
    @ApiOperation("一篇文章的详细信息")
    public Result<CommentDto> getArticleDetailComment(@ApiParam(name = "id",value = "文章的id") Long id){
        return null;
    }

    @PostMapping("/comment")
    @ApiOperation("被收藏的文章")
    public Result<PageExample<VolunteerArticleDto>> getCollectedArticle(@RequestBody PageExample<Object> articlePage){
        return null;
    }


}
