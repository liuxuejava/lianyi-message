import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Stu on 2018/8/9.
 */
public class TestJmail {
    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", "true");//设置访问smtp服务器需要认证
            properties.setProperty("mail.transport.protocol", "smtp"); //设置访问服务器的协议

            Session session = Session.getDefaultInstance(properties);
            session.setDebug(true); //打开debug功能

            Message msg = new MimeMessage(session);
            //这里填你登录163邮箱所用的用户名
            msg.setFrom(new InternetAddress("liuxuejava@163.com")); //设置发件人，163邮箱要求发件人与登录用户必须一致（必填），其它邮箱不了解
            msg.setSubject("测试邮件"); //设置邮件主题
            msg.setText("hello java zhangpeng"); //设置邮件内容


            Transport trans = session.getTransport();
            //下面四个参数，前两个可以认为是固定的，不用变，后两个参数分别是登录163邮箱的用户名以及客户端授权密码（注意，不是登录密码）
            trans.connect("smtp.163.com", 25, "liuxuejava@163.com", "liuxue808642"); //连接邮箱smtp服务器，25为默认端口
            //要发送到哪个邮箱，这里以qq邮箱为例
            trans.sendMessage(msg, new Address[]{new InternetAddress("531633936@qq.com")}); //发送邮件

            trans.close(); //关闭连接
        }catch (Exception e){

        }

    }
}
