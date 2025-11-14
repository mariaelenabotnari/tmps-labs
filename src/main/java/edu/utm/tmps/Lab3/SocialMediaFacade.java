package edu.utm.tmps.Lab3;

import java.util.*;

public class SocialMediaFacade {

    private final IUserService userService;
    private final IPostService postService;
    private final IFeedService feedService;
    private final INotificationService notificationService;

    private User loggedInUser;

    private final Map<String, Runnable> commands = new HashMap<>();

    public SocialMediaFacade(
            IUserService userService,
            IPostService postService,
            IFeedService feedService,
            INotificationService notificationService
    ) {
        this.userService = userService;
        this.postService = postService;
        this.feedService = feedService;
        this.notificationService = notificationService;

        initializeCommands();
    }

    private void initializeCommands() {
        commands.put("1", this::createPost);
        commands.put("2", this::viewOwnPosts);
        commands.put("3", this::searchPost);
        commands.put("4", this::viewFeed);
        commands.put("5", this::logout);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username:");
        String username = scanner.nextLine();
        loggedInUser = userService.register(username);

        notificationService.sendNotification(loggedInUser, "Welcome to the app!");
        viewFeed();

        while (loggedInUser != null) {
            System.out.println("""
                    Choose an option:
                    1. Create Post
                    2. View My Posts
                    3. Search Post
                    4. View Feed
                    5. Logout
                    """);

            String choice = scanner.nextLine();

            Runnable command = commands.get(choice);
            if (command != null) {
                command.run();
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void createPost() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter post content:");
        String content = sc.nextLine();

        Post post = postService.createPost(loggedInUser.getId(), content);
        notificationService.sendNotification(loggedInUser, "Your post was published!");

        System.out.println("Post created: " + post.getContent());
    }

    private void viewOwnPosts() {
        System.out.println("Your posts:");
        ArrayList<Post> posts = feedService.retrievePostsUser(loggedInUser.getId());
        posts.forEach(p -> System.out.println(" - " + p.getContent()));
    }

    private void searchPost() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter post ID (example: post-1):");
        String id = sc.nextLine();

        Post post = feedService.searchPost(id);
        if (post != null) {
            System.out.println("Post found: " + post.getContent());
        }
    }

    private void viewFeed() {
        System.out.println("Your feed:");
        ArrayList<Post> posts = feedService.retrieveAllPosts();
        posts.forEach(p -> System.out.println(p.getUserId() + ": " + p.getContent()));
    }

    private void logout() {
        System.out.println("Goodbye " + loggedInUser.getUsername());
        loggedInUser = null;
    }
}
