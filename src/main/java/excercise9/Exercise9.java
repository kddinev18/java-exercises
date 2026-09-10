package excercise9;

import excercise8.Exercise8;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/** Solutions for the tasks in "javafile.docx". */
public final class Exercise9 {
    private Exercise9() {
    }

    public static final class FileUserRegistry {
        private final Path directory;

        public FileUserRegistry(Path directory) throws IOException {
            this.directory = directory.toAbsolutePath().normalize();
            Files.createDirectories(this.directory);
        }

        public void register(String username, String password) throws IOException {
            Path userFile = userFile(username, ".txt");
            Files.writeString(userFile, password, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
        }

        public boolean login(String username, String password) throws IOException {
            Path userFile = userFile(username, ".txt");
            return Files.isRegularFile(userFile)
                    && Files.readString(userFile, StandardCharsets.UTF_8).equals(password);
        }

        private Path userFile(String username, String suffix) {
            validateUsername(username);
            Path result = directory.resolve(username + suffix).normalize();
            if (!result.getParent().equals(directory)) {
                throw new IllegalArgumentException("Invalid username");
            }
            return result;
        }
    }

    public record User(String nickname, String password) implements Serializable {
        public User {
            validateUsername(nickname);
            if (password == null) throw new IllegalArgumentException("Password must not be null");
        }
    }

    public static final class SerializedUserRegistry {
        private final Path directory;

        public SerializedUserRegistry(Path directory) throws IOException {
            this.directory = directory.toAbsolutePath().normalize();
            Files.createDirectories(this.directory);
        }

        public void register(User user) throws IOException {
            Path userFile = directory.resolve(user.nickname() + ".ser");
            if (Files.exists(userFile)) throw new IOException("User already exists");
            serializeObject(userFile, user);
        }

        public boolean login(String nickname, String password) throws IOException, ClassNotFoundException {
            validateUsername(nickname);
            Path userFile = directory.resolve(nickname + ".ser");
            if (!Files.isRegularFile(userFile)) return false;
            User user = deserializeObject(userFile, User.class);
            return user.password().equals(password);
        }
    }

    /** Serializes any Serializable object, including models from earlier packages. */
    public static void serializeObject(Path file, Serializable object) throws IOException {
        Path absolute = file.toAbsolutePath().normalize();
        Path parent = absolute.getParent();
        if (parent != null) Files.createDirectories(parent);
        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(absolute))) {
            output.writeObject(object);
        }
    }

    public static <T> T deserializeObject(Path file, Class<T> expectedType)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(file))) {
            Object object = input.readObject();
            return expectedType.cast(object);
        }
    }

    private static void validateUsername(String username) {
        if (username == null || !username.matches("[A-Za-z0-9._-]+")) {
            throw new IllegalArgumentException(
                    "Username may contain only Latin letters, digits, dot, underscore and hyphen");
        }
    }

    public static void main(String[] args) throws Exception {
        Path demoDirectory = Files.createTempDirectory("java-exercise-users-");

        FileUserRegistry textRegistry = new FileUserRegistry(demoDirectory.resolve("text"));
        textRegistry.register("ivan", "example-password");
        System.out.println("Text login: " + textRegistry.login("ivan", "example-password"));

        SerializedUserRegistry objectRegistry = new SerializedUserRegistry(demoDirectory.resolve("objects"));
        objectRegistry.register(new User("maria", "another-password"));
        System.out.println("Serialized login: " + objectRegistry.login("maria", "another-password"));

        Path catFile = demoDirectory.resolve("cat.ser");
        serializeObject(catFile, new Exercise8.SiameseCat("Luna", 3));
        Exercise8.SiameseCat restored = deserializeObject(catFile, Exercise8.SiameseCat.class);
        restored.show();
    }
}

