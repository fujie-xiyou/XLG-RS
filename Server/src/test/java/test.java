import org.springframework.beans.factory.annotation.Autowired;
import org.xiyoulinux.recruitment.dao.JoinDAO;
import org.xiyoulinux.recruitment.model.Join;

public class test {
    @Autowired
    public JoinDAO joinDAO;
    public static void main(String[] args){
        Join join = new Join();
        join.setProcessID(1);
        join.setSname("fujie");
        new test().joinDAO.insert(join);
    }
}
