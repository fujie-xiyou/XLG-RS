package org.xiyoulinux.recruitment.web;

import org.xiyoulinux.recruitment.untils.GetInfoFromJW;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "CheckCode",
        urlPatterns = {"/checkCode.gif"}
)
public class CheckCodeServlet extends HttpServlet {
    public GetInfoFromJW getJW(HttpServletRequest req){
           GetInfoFromJW getInfoFromJW = (GetInfoFromJW) req.getSession().getAttribute("JW");
            if(getInfoFromJW == null) {
                getInfoFromJW = new GetInfoFromJW();
                req.getSession().setAttribute("JW", getInfoFromJW);
            }
        return getInfoFromJW;
    }
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GetInfoFromJW jw = getJW(req);
        resp.setContentType("image/gif; charset=gb2312");
        byte[] img = jw.getCheckCode();
        ServletOutputStream os = resp.getOutputStream();
        if(img == null) System.out.println("img为空");
        os.write(img);
        os.flush();
        os.close();
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
