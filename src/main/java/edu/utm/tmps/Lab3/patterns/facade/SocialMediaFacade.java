package edu.utm.tmps.Lab3.patterns.facade;

import edu.utm.tmps.Lab3.model.Post;
import edu.utm.tmps.Lab3.model.User;
import edu.utm.tmps.Lab3.patterns.decorator.FilterDecorator;
import edu.utm.tmps.Lab3.patterns.decorator.ResizeDecorator;
import edu.utm.tmps.Lab3.patterns.factory.ImagePostFactory;
import edu.utm.tmps.Lab3.patterns.factory.PostFactory;
import edu.utm.tmps.Lab3.patterns.factory.TextPostFactory;
import edu.utm.tmps.Lab3.patterns.factory.VideoPostFactory;
import edu.utm.tmps.Lab3.service.*;

import java.util.*;

public class SocialMediaFacade {

    private static SocialMediaFacade instance;

    private final IUserService userService;
    private final IPostService postService;
    private final IFeedService feedService;
    private final INotificationService notificationService;
    private final Map<String, PostFactory> postFactories = new HashMap<>();

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
        initializePostFactories();
    }

    public static synchronized SocialMediaFacade getInstance(
            IUserService userService,
            IPostService postService,
            IFeedService feedService,
            INotificationService notificationService
    ) {
        if (instance == null) {
            instance = new SocialMediaFacade(
                    userService, postService, feedService, notificationService
            );
        }
        return instance;
    }

    private void initializeCommands() {
        commands.put("1", this::createPost);
        commands.put("2", this::viewOwnPosts);
        commands.put("3", this::searchPost);
        commands.put("4", this::viewFeed);
        commands.put("5", this::logout);
        commands.put("6", this::editProfilePicture);
    }

    private void initializePostFactories() {
        postFactories.put("1", new TextPostFactory());
        postFactories.put("2", new ImagePostFactory());
        postFactories.put("3", new VideoPostFactory());
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
                    \nChoose an option:
                    1. Create Post
                    2. View My Posts
                    3. Search Post
                    4. View Feed
                    5. Logout
                    6. Edit Profile Picture
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

        System.out.println("""
            Choose post type:
            1. Text post
            2. Image post
            3. Video post
            """);
        String typeChoice = sc.nextLine();

        PostFactory factory = postFactories.get(typeChoice);
        if (factory == null) {
            System.out.println("Invalid post type.");
            return;
        }

        System.out.println("Enter post content (text or image URL/path):");
        String content = sc.nextLine();

        Post post = postService.createPost(loggedInUser.getId(), content, factory);
        notificationService.sendNotification(loggedInUser, "Your post was published!");

        post.displayPost();
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

    private void editProfilePicture() {
        Scanner sc = new Scanner(System.in);

        IProfileService baseProfile = loggedInUser.getProfileService();

        FilterDecorator filterDecorator = new FilterDecorator(baseProfile);
        ResizeDecorator resizeDecorator = new ResizeDecorator(filterDecorator);

        System.out.println("Enter profile picture filename (e.g. pic.png):");
        String picture = sc.nextLine();

        resizeDecorator.addProfilePicture(picture);

        System.out.println("""
            Resize options:
            1. Make smaller
            2. Make bigger
            """);

        String resizeChoice = sc.nextLine();
        if (resizeChoice.equals("1")) {
            resizeDecorator.resizeSmaller();
        } else if (resizeChoice.equals("2")) {
            resizeDecorator.resizeBigger();
        }

        List<String> filters = List.of("sepia", "black-and-white", "smooth", "retro", "warm");
        System.out.println("Available filters:");
        for (int i = 0; i < filters.size(); i++) {
            System.out.println((i + 1) + ". " + filters.get(i));
        }

        System.out.println("Choose filter number:");
        int filterChoice = Integer.parseInt(sc.nextLine());
        String chosenFilter = filters.get(filterChoice - 1);

        filterDecorator.applyFilter(chosenFilter);

        System.out.println("\nFinal profile:");
        resizeDecorator.displayProfile();

        System.out.println("\nProfile updated successfully!");
    }

}
