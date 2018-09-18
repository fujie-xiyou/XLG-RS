利用学号、密码创建实例，尝试获取数据。
如果登录成功(result中为flag为true)，然后就可以解析data域（json）中所需的数据；
否则data域中的是错误信息。

	ConnectJWGL connectJWGL = new ConnectJWGL(stuNum,password);
        ResponseResult result = connectJWGL.init();
        if(result.getFlag()){
            //解析想要的数据
            /*部分参数
            JSONObject jsonObject = JSON.parseObject(result.getData().toString());
            jsonObject.getString("xh_id");
            学号：xh_id     性别：xbm     民族：mzm         学院：jg_id  班级：bh_id
            专业：zszyh_id  状态：xjztdm  入学年份：njdm_id  证件号码：zjhm
            政治面貌：zzmmm  生日：csrq
            */
        }else {
            // 提示错误信息
            System.out.println(result.getData());
        }
