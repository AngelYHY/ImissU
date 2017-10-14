package freestar.factory;

import freestar.bean.db.User;
import freestar.util.Hib;
import freestar.util.TextUtil;

public class UserFactory {

    // 通过Phone找到User
    public static User findByPhone(String phone) {
        return Hib.query(session -> (User) session
                .createQuery("from User where phone=:inPhone")
                .setParameter("inPhone", phone)
                .uniqueResult());
    }

    /**
     * 应用注册
     * 注册的操作需要写入数据库，并返回数据库中的 User 信息
     *
     * @param phone    手机号码
     * @param password 密码
     * @return user
     */
    public static User register(String phone, String password) {
        // 去除手机号中的首尾空格
        phone = phone.trim();
        // 处理密码
        password = encodePassword(password);
//        User user = createUser(phone, password);
//        if (user != null) {
//            user = login(user);
//        }
        return createUser(phone, password);
    }

//    private static User login(User user) {
//        return null;
//    }

    /**
     * 注册部分的新建用户逻辑
     *
     * @param phone    手机号
     * @param password 加密后的密码
     * @return 返回用户
     */
    private static User createUser(String phone, String password) {
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);

        // 数据库存储
        return Hib.query(session -> {
            session.save(user);
            return user;
        });
    }

    private static String encodePassword(String password) {
        // 密码去除首尾空格
        password = password.trim();
        // 进行 MD5 非对称加密，加盐会更安全，盐也需要存储
        password = TextUtil.getMD5(password);
        // 再进行一次对称的 Base64 加密，当然可以采取加盐的方案
        return TextUtil.encodeBase64(password);
    }
}
