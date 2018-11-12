package org.xiyoulinux.recruitment.service;

import org.xiyoulinux.recruitment.model.po.Join;
import org.xiyoulinux.recruitment.untils.ConnectJWGL;
import org.xiyoulinux.recruitment.untils.ResponseResult;

import javax.servlet.http.HttpServletRequest;

public interface SignService {
    public ResponseResult signUp(HttpServletRequest request,String mobile);
    public ResponseResult checkUser(HttpServletRequest request , Join join);
}
