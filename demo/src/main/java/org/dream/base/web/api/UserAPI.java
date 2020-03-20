package org.dream.base.web.api;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.dream.base.dto.User;
import org.dream.base.dto.UserQueryCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：security
 * 类 名 称：UserAPI
 * 类 描 述：用户接口
 * 创建时间：2020/3/17 23:37
 * 创 建 人：Lance.WU
 */
@RestController
@RequestMapping("/api/1.0/user")
@Slf4j
public class UserAPI {

    /**
     * 创建用户信息
     *
     * @param user 添加用户
     * @return 用户对像
     * @RequestBody 使用post请求的时候，通过此注释转换成用户对像
     * BindingResult 与 @Validated 一起使用  返回错误信息
     */
    @PostMapping("")
    @JsonView(User.UserSimpleDetailView.class)
    public User create(@Valid @RequestBody User user) {

        log.info("请求User：{}", user);
        user.setId("1");
        return user;
    }

//    /**
//     * 创建用户信息
//     *
//     * @param user 添加用户
//     * @return 用户对像
//     * @RequestBody 使用post请求的时候，通过此注释转换成用户对像
//     * BindingResult 与 @Validated 一起使用  返回错误信息
//     */
//    @PostMapping("")
//    @JsonView(User.UserSimpleDetailView.class)
//    public User create(@Valid @RequestBody User user, BindingResult error) {
//
//        // 打错所有的错误信息
//        if (error.hasErrors()) {
//            error.getAllErrors().stream().forEach(e -> log.error(e.getDefaultMessage()));
//        }
//        log.info("请求User：{}", user);
//        user.setId("1");
//        return user;
//    }

    /**
     * 修改用户信息
     *
     * @param user 添加用户
     * @return 用户对像
     * @RequestBody 使用post请求的时候，通过此注释转换成用户对像
     * BindingResult 与 @Validated 一起使用  返回错误信息
     */
    @PutMapping("{id:\\d+}")
    @JsonView(User.UserSimpleDetailView.class)
    public User update(@Valid @RequestBody User user, BindingResult error) {

        // 打错所有的错误信息
        if (error.hasErrors()) {
            error.getAllErrors().stream().forEach(e -> {
                FieldError fieldError = (FieldError) e;
                log.error(fieldError.getField() + " " + e.getDefaultMessage());
            });
        }
        log.info("请求User：{}", user);
        user.setId("1");
        return user;
    }

    /**
     * 删除用户信息
     *
     * @param id 用户编号
     * @return 用户对像
     * @RequestBody 使用post请求的时候，通过此注释转换成用户对像
     * BindingResult 与 @Validated 一起使用  返回错误信息
     */
    @DeleteMapping("{id:\\d+}")
    public void update(@PathVariable("id") String id) {
        log.info("删除用户信息：{}", id);
    }

//    @GetMapping("")
//    public List<User> query(){
//
//        List<User> users = new ArrayList<>();
//        users.add(new User());
//        users.add(new User());
//        users.add(new User());
//        return users;
//    }

//    /**
//     * 查询所有的用户信息  @RequestParam 请求参数 是否必填 required = false 是否必填  defaultValue 默认值
//     *
//     * @param userName 名称
//     * @return
//     */
//    @GetMapping("")
//    public List<User> query(@RequestParam(required = false, defaultValue = "tom") String userName) {
//
//        log.info("请求参数：{}", userName);
//        List<User> users = new ArrayList<>();
//        users.add(new User());
//        users.add(new User());
//        users.add(new User());
//        return users;
//    }

//    /**
//     * 查询所有的用户信息  @RequestParam 请求参数 是否必填 required = false 是否必填  defaultValue 默认值
//     *
//     * @param userQueryCondition 查询参数
//     * @return
//     */
//    @GetMapping("")
//    public List<User> query(UserQueryCondition userQueryCondition) {
//
//        log.info("请求参数：{}", userQueryCondition);
//
//        List<User> users = new ArrayList<>();
//        users.add(new User());
//        users.add(new User());
//        users.add(new User());
//        return users;
//    }

    /**
     * 查询所有的用户信息  @RequestParam 请求参数 是否必填 required = false 是否必填  defaultValue 默认值  Pageable 分对像，可以使用 @PageableDefault设置默认参数
     *
     * @param userQueryCondition 查询参数
     * @return 用户列表
     */
    @GetMapping("")
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询服务")
    public List<User> query(UserQueryCondition userQueryCondition,
                            @PageableDefault(size = 10, page = 1, sort = "age,asc") Pageable pageable) {

        log.info("请求参数：{}", userQueryCondition);
        log.info("分页请求参数：{}", pageable);

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("{id:\\d+}")
    @JsonView(User.UserSimpleDetailView.class)
    public  User getInfo(@ApiParam(value = "用户编号") @PathVariable(required = false, value = "id") String id) {
        //   throw new RuntimeException("not found user");
        //  throw new UserNotExistsException(id);
        User user = new User();
        user.setId(id);
        user.setUserName("tom");
        return user;
    }

}
