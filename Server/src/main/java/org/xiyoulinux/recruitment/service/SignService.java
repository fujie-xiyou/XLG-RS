package org.xiyoulinux.recruitment.service;

import org.xiyoulinux.recruitment.model.po.Join;
import org.xiyoulinux.recruitment.untils.ConnectJWGL;
import org.xiyoulinux.recruitment.untils.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface SignService {
    public ResponseResult signUp(HttpServletRequest request,String mobile);
    public ResponseResult checkUser(HttpServletRequest request , Join join);
    public ResponseResult isCanSign(HttpServletRequest request);
    public ResponseResult getInfo(HttpServletRequest request);
    public ResponseResult modifyMobile(HttpSession session, String mobile);
}
