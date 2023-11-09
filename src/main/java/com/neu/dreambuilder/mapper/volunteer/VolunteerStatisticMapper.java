package com.neu.dreambuilder.mapper.volunteer;

import com.neu.dreambuilder.dto.CommentDto;
import com.neu.dreambuilder.dto.kid.KidVieDto;
import com.neu.dreambuilder.dto.volunteer.ArticleDto;
import com.neu.dreambuilder.entity.kid.Kid;
import com.neu.dreambuilder.entity.kid.Mission;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface VolunteerStatisticMapper {

    /**
     * 查询该志愿者对应的的所有任务的分别提交总数
     * @param volunId
     * @return
     */
    @MapKey("missionId")
    Map<Long,Integer> submitCount(Long volunId);

    /**
     * 查询该志愿者最新的任务信息
     * @param volunId
     * @return
     */
    Mission volunRandomMission(Long volunId);

    /**
     * 查询所对应任务的（孩子已完成）的，老师已经批改的作业数
     * @param
     * @return
     */
    @MapKey("missionId")
    Map<Long,Integer> hasCheck();

    /**
     * 查询该志愿者对应的所有任务
     * @param volunId
     * @return
     */
    List<Mission> volunAllMission(Long volunId);

    List<KidVieDto>  volunMissionKid(Long missionId);

    /**
     * 查询所有社区文章
     * @param title
     * @return
     */
    List<ArticleDto> allArticle(String title);

    /**
     * 根据文章id查询文章的所有评论
     * @param articleId
     * @return
     */
    List<CommentDto> articleComment(Long articleId);

    /**
     * 查询该志愿者所收藏的文章
     * @param volunId
     * @return
     */
    List<ArticleDto> allArticleLike(Long volunId);

    /**
     * 查询该志愿者所对应的孩子
     * @param volunId
     * @return
     */
    List<Kid> volunKid(Long volunId);


    /**
     * 查询出最近获的善款捐助的孩子
     * @param kidId
     * @return
     */
    @MapKey("kid_id")
    Map<String,Object> newDonation(List<Long> kidId);


    /**
     * 查询出最近获得物品捐助的孩子
     * @param kidId
     * @return
     */
    @MapKey("kid_id")
    Map<String,Object> newThingDonation(List<Long> kidId);





}
