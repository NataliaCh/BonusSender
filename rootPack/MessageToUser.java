package rootPack;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;

public class MessageToUser {

    private final User user;

    public MessageToUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return user.email;
    }

    public String createHtml() throws IOException {
        File io = new File("D:\\Education\\BonusSender\\rootPack\\template.html");
        try {
            Object[] args = user.toArgs().toArray();
            return MessageFormat.format(FileUtils.readFileToString(io), args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
