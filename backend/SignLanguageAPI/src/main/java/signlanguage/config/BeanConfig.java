package signlanguage.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import signlanguage.dao.UserDao;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {
    private final UserDao userDao;

    @Bean
    public UserDetailsService userDetailsService()  {

        return uid-> userDao.findById(uid).orElseThrow(() -> new UsernameNotFoundException("not found user!"));

    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public FirebaseApp initializeFirebase() {
        try {
            Resource resource = new ClassPathResource("signlanguage-firebase-adminsdk.json");
            InputStream serviceAccount = resource.getInputStream();
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    // .setStorageBucket("gs://sign-language-f4752.appspot.com")
                    // .setDatabaseUrl("https://your-project-id.firebaseio.com") // Thay thế bằng URL của Firebase Realtime Database hoặc Cloud Firestore của bạn
                    .build();
            return FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }
    @Bean
    public Storage firebaseStorage() throws IOException {
        Resource resource = new ClassPathResource("signlanguage-firebase-adminsdk.json");
        InputStream serviceAccount = resource.getInputStream();
        return StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
                .getService();
    }
}