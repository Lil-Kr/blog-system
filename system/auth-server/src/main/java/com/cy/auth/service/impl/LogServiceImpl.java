package com.cy.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.auth.dao.LogMapper;
import com.cy.auth.pojo.entity.Log;
import com.cy.auth.service.LogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-26
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
