# Redis-User-Schema
user schema for redis in java


## Usage:
```Java
public class MainApp {
    public static void main(String[] args) {
        // Örnek kullanıcı oluşturup Redis'e ekleyelim
        User newUser = new User();
        newUser.setId("1");
        newUser.setUsername("exampleUser");
        newUser.setPassword("examplePassword");
        newUser.setEmail("example@example.com");
        newUser.setPhone("1234567890");
        newUser.setLastLoginIp("127.0.0.1");

        // Kullanıcıyı kaydet
        UserManager.saveUser(newUser);

        // ID'ye göre kullanıcıyı Redis'ten getirip yazdıralım
        User retrievedUserById = UserManager.getUserById("1");
        System.out.println("Retrieved User by ID: " + retrievedUserById);

        // Email'e göre kullanıcıyı Redis'ten getirip yazdıralım
        User retrievedUserByEmail = UserManager.getUserByEmail("example@example.com");
        System.out.println("Retrieved User by Email: " + retrievedUserByEmail);

        // Username'e göre kullanıcıyı Redis'ten getirip yazdıralım
        User retrievedUserByUsername = UserManager.getUserByUsername("exampleUser");
        System.out.println("Retrieved User by Username: " + retrievedUserByUsername);

        // Örnek kullanıcıyı Redis'ten silelim
        UserManager.deleteUser("1");
    }
}
```
