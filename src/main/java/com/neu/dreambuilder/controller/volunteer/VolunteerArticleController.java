package com.neu.dreambuilder.controller.volunteer;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.dreambuilder.common.utils.BaseContext;
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
import io.swagger.models.auth.In;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/volunteer/article")
@PreAuthorize("hasAuthority('VOLUNTEER')")
@Api(tags = "志愿者端文章相关信息接口")
public class VolunteerArticleController {

    @Resource
    private VolunteerArticleService volunteerArticleService;

    /**
     *获取志愿者界面滚动文章
     * @return
     */
    @GetMapping(value = "/random",produces = "application/json; charset=utf-8")
    @ApiOperation(value = "志愿者页面滑动的文章")
    public Result<ArticleDto> getRandomArticle(){
        return volunteerArticleService.getRandomArticle();
    }

    @PostMapping(value = "/list",produces = "application/json; charset=utf-8")
    @ApiOperation("文章列表（分页）")
    public Result<PageInfo<ArticleDto>> getAllArticle(@RequestBody PageExample<Article> articlePage){
        PageHelper.startPage(articlePage.getPageNum(),articlePage.getPageSize());
        List<ArticleDto> articleDtos;
       if (articlePage.getExample()==null){
           articleDtos =  volunteerArticleService.getAllArticle("");
       }else {

            articleDtos = volunteerArticleService.getAllArticle(articlePage.getExample().getTitle());
       }
        PageInfo<ArticleDto> pageInfo =new PageInfo<>(articleDtos);
        return Result.success(pageInfo);
    }



    @GetMapping(value = "/detail/{id}", produces = "application/json; charset=utf-8")
    @ApiOperation("一篇文章的详细信息")
    public Result<Article> getArticleDetail(@ApiParam(name = "id",value = "文章的id") @PathVariable String id){
        long idt = Long.parseLong(id);
        return volunteerArticleService.getArticleDetails(idt);
    }

    @GetMapping(value = "/detail/{id}/comment",produces = "application/json; charset=utf-8")
    @ApiOperation("一篇文章的评论")
    public Result<List<CommentDto>> getArticleDetailComment(@ApiParam(name = "id",value = "文章的id")  @PathVariable String id){
        long idt = Long.parseLong(id);
        return volunteerArticleService.getArticleComments(idt);
    }

    @PostMapping(value = "/comment",produces = "application/json; charset=utf-8")
    @ApiOperation("被收藏的文章")
    public Result<PageInfo<ArticleDto>> getCollectedArticle(@RequestBody PageExample<Object> articlePage){
        PageHelper.startPage(articlePage.getPageNum(),articlePage.getPageSize());

        IUserDetails currentIUserDetails = BaseContext.getCurrentIUserDetails();
        List<ArticleDto> articleColleted = volunteerArticleService.getArticleColleted(currentIUserDetails.getId());
        PageInfo<ArticleDto> pageInfo  = new PageInfo<>(articleColleted);

        return Result.success(pageInfo);
    }

    @GetMapping(value = "/love",produces = "application/json; charset=utf-8")
    @ApiOperation("详细文章内容点赞")
    public Result<Boolean> ifLove(String articleId){

        return Result.success(volunteerArticleService.getIfLove(articleId));
    }

    @GetMapping(value = "/lovecount",produces = "application/json; charset=utf-8")
    @ApiOperation("详细文章内容的点赞数")
    public Result<Integer> articleLoveCount(String articleId){
        return Result.success( volunteerArticleService.getLoveCount(articleId));

    }


    @PostMapping(value = "/loveput/{articleId}",produces = "application/json; charset=utf-8")
    @ApiOperation("给文章点赞")
    public Result<String> putArticleLove(@PathVariable String articleId){
        volunteerArticleService.putArticeLove(articleId);
        return Result.success("点赞成功");
    }

    @DeleteMapping(value = "/love/delete/{articleId}",produces = "application/json; charset=utf-8")
    @ApiOperation("取消点赞")
    public Result<String> deleteArticlelove(@PathVariable Long articleId){
        volunteerArticleService.deleteArticleLove(articleId);
        return Result.success("取消成功");
    }

    @PostMapping(value = "/putlike/{articleId}",produces = "application/json; charset=utf-8")
    @ApiOperation("添加文章收藏")
    public Result<String> putArticleLike(@PathVariable String articleId){
        Long artId = Long.valueOf(articleId);
        volunteerArticleService.putArticleLike(artId);
        return Result.success("收藏成功");
    }



}
