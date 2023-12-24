import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String lastLoginIp;

    // Getter ve Setter metotları
   //lombook kullanmayın !

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                '}';
    }
}
