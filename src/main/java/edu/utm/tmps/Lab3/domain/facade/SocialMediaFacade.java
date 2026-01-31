package edu.utm.tmps.Lab3.domain.facade;

import edu.utm.tmps.Lab3.domain.command.CommandInvoker;
import edu.utm.tmps.Lab3.domain.command.LikePostCommand;
import edu.utm.tmps.Lab3.domain.command.LikePostService;
import edu.utm.tmps.Lab3.domain.model.Post;
import edu.utm.tmps.Lab3.domain.model.User;
import edu.utm.tmps.Lab3.domain.decorator.FilterDecorator;
import edu.utm.tmps.Lab3.domain.decorator.ResizeDecorator;
import edu.utm.tmps.Lab3.domain.factory.ImagePostFactory;
import edu.utm.tmps.Lab3.domain.factory.PostFactory;
import edu.utm.tmps.Lab3.domain.factory.TextPostFactory;
import edu.utm.tmps.Lab3.domain.factory.VideoPostFactory;
import edu.utm.tmps.Lab3.domain.service.*;
import edu.utm.tmps.Lab3.domain.strategy.SortByFollowingOnlyStrategy;
import edu.utm.tmps.Lab3.domain.strategy.SortByNewestStrategy;

import java.util.*;

public class SocialMediaFacade {

    private static SocialMediaFacade instance;

    private final IUserService userService;
    private final IPostService postService;
    private final IFeedService feedService;
    private final INotificationService notificationService;
    private final LikePostService likeService = new LikePostService();
    private final CommandInvoker commandInvoker = new CommandInvoker();
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
        commands.put("7", this::editProfileDetails);
        commands.put("8", this::viewProfile);
        commands.put("9", this::followUser);
        commands.put("10", this::likePost);
        commands.put("11", this::undoLastAction);
        commands.put("12", this::showActivity);
        commands.put("13", this::changeFeedSorting);
        commands.put("14", this::unfollowUser);
    }

    private void initializePostFactories() {
        postFactories.put("1", new TextPostFactory());
        postFactories.put("2", new ImagePostFactory());
        postFactories.put("3", new VideoPostFactory());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\nLogin with an existing username or register with new one:");
            String username = scanner.nextLine();

            User existing = userService.login(username);

            if (existing != null) {
                loggedInUser = existing;
                System.out.println("Welcome back, " + loggedInUser.getUsername());
            } else {
                try {
                    loggedInUser = userService.register(username);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    continue;
                }
            }

            notificationService.sendNotification(loggedInUser, "Welcome to the app!");
            viewFeed();

            while (true) {
                System.out.println("""
                    \nChoose an option:
                    1. Create Post
                    2. View My Posts
                    3. Search Post
                    4. View Feed
                    5. Logout
                    6. Edit Profile Picture
                    7. Edit Profile Details
                    8. View Profile Info
                    9. Follow User
                    10. Like a Post
                    11. Undo Last Like
                    12. View History of Liked Posts
                    13. Change feed sorting
                    14. Unfollow User
                    0. Exit
                    """);

                String choice = scanner.nextLine();

                if (choice.equals("0")) {
                    return;
                }

                if (choice.equals("5")) {
                    System.out.println("Goodbye " + loggedInUser.getUsername());
                    loggedInUser = null;
                    break;
                }

                Runnable command = commands.get(choice);
                if (command != null) {
                    command.run();
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        }
    }

    private void createPost() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
            Choose post type:
            1. Text post
            2. Image post
            3. Video post
            0. Go back
            """);

            String typeChoice = scanner.nextLine();

            if (typeChoice.equals("0")) {
                return;
            }

            PostFactory factory = postFactories.get(typeChoice);

            if (factory == null) {
                System.out.println("Invalid post type. Try again.\n");
                continue;
            }

            System.out.println("Enter post content (text or image/video path):");
            String content = scanner.nextLine();

            try {
                Post post = postService.createPost(loggedInUser.getId(), content, factory);
                notificationService.sendNotification(loggedInUser, "Your post was published!");
                loggedInUser.getFollowManager().notifyObservers(
                        loggedInUser.getUsername() + " published a new post: " + post.getContent()
                );
                post.displayPost();
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            return;
        }
    }

    private void viewOwnPosts() {
        System.out.println("Your posts:");
        ArrayList<Post> posts = feedService.retrievePostsUser(loggedInUser.getId());
        posts.forEach(p -> System.out.println(" - " + p.getContent()));
    }

    private void searchPost() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter post ID (example: post-text-1/post-vid-1/post-img-1):");
        String id = scanner.nextLine();

        Post post = feedService.searchPost(id);
        if (post != null) {
            System.out.println("Post found: " + post.getContent());
        }
    }

    private void viewFeed() {
        System.out.println("Your feed:");
        ArrayList<Post> posts = feedService.retrieveFeed(loggedInUser);
        posts.forEach(p -> System.out.println(p.getUserId() + ": " + p.getContent()));
    }

    private void logout() {
        System.out.println("Goodbye " + loggedInUser.getUsername());
        loggedInUser = null;
    }

    private void editProfilePicture() {
        Scanner scanner = new Scanner(System.in);

        IProfilePictureService baseProfile = loggedInUser.getProfileService();
        FilterDecorator filterDecorator = new FilterDecorator(baseProfile);
        ResizeDecorator resizeDecorator = new ResizeDecorator(filterDecorator);

        System.out.println("Enter profile picture filename:");
        String picture = scanner.nextLine();

        try {
            resizeDecorator.addProfilePicture(picture);
            notificationService.sendNotification(loggedInUser, "Profile picture updated.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        while (true) {
            System.out.println("""
            Resize options:
            1. Make smaller
            2. Make bigger
            3. Keep default size
            """);

            String resizeChoice = scanner.nextLine();

            Map<String, Runnable> resizeMap = new HashMap<>();

            resizeMap.put("1", () -> {
                resizeDecorator.resizeSmaller();
                notificationService.sendNotification(loggedInUser, "Profile picture resized to SMALL.");
            });

            resizeMap.put("2", () -> {
                resizeDecorator.resizeBigger();
                notificationService.sendNotification(loggedInUser, "Profile picture resized to BIG.");
            });

            resizeMap.put("3", () -> {
                System.out.println("Keeping default size.");
            });

            Runnable resizeAction = resizeMap.get(resizeChoice);

            if (resizeAction != null) {
                if (resizeChoice.equals("0")) break;
                resizeAction.run();
                break;
            }

            System.out.println("Invalid option. Try again.\n");
        }

        List<String> filters = List.of("sepia", "black-and-white", "smooth", "retro", "warm");

        while (true) {
            System.out.println("Available filters:");
            for (int i = 0; i < filters.size(); i++) {
                System.out.println((i + 1) + ". " + filters.get(i));
            }
            System.out.println("6. Keep default filter");

            String input = scanner.nextLine();

            if (input.equals("6")) {
                System.out.println("Keeping default filter.");
                break;
            }

            Integer filterIndex = readIntegerOrNull(input);

            if (filterIndex == null || filterIndex < 1 || filterIndex > filters.size()) {
                System.out.println("Invalid filter. Try again.\n");
                continue;
            }

            String chosenFilter = filters.get(filterIndex - 1);
            filterDecorator.applyFilter(chosenFilter);
            notificationService.sendNotification(loggedInUser, "Filter applied: " + chosenFilter);
            break;
        }

        notificationService.sendNotification(loggedInUser, "Profile picture updated successfully.");
    }

    private void editProfileDetails() {
        Scanner scanner = new Scanner(System.in);

        IProfileDetailsService detailsService =
                (IProfileDetailsService) loggedInUser.getProfileService();

        System.out.println("""
        What would you like to update?
        1. Age
        2. Biography
        3. Location
        0. Go back
        """);

        String choice = scanner.nextLine();
        if (choice.equals("0")) return;

        Map<String, Runnable> detailCommands = new HashMap<>();

        detailCommands.put("1", () -> {
            while (true) {
                System.out.println("Enter new age:");
                Integer age = readIntegerOrNull(scanner.nextLine());

                if (age == null) {
                    System.out.println("Age must be a number. Try again.");
                    continue;
                }

                try {
                    detailsService.addAge(age);
                    notificationService.sendNotification(loggedInUser, "Age updated successfully.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    continue;
                }
                break;
            }
        });

        detailCommands.put("2", () -> {
            while (true) {
                System.out.println("Enter new biography:");
                String biography = scanner.nextLine();
                try {
                    detailsService.addBiography(biography);
                    notificationService.sendNotification(loggedInUser, "Biography updated successfully.");
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        });

        detailCommands.put("3", () -> {
            while (true) {
                System.out.println("Enter new location:");
                String location = scanner.nextLine();
                try {
                    detailsService.addLocation(location);
                    notificationService.sendNotification(loggedInUser, "Location updated successfully.");
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        });

        Runnable action = detailCommands.get(choice);

        if (action != null) {
            action.run();
        } else {
            System.out.println("Invalid option.");
        }
    }

    private void viewProfile() {
        System.out.println("\n=== Your Profile Info ===");
        loggedInUser.getProfileService().displayProfile();
        System.out.println("==========================\n");
    }

    private Integer readIntegerOrNull(String s) {
        try { return Integer.parseInt(s); }
        catch (Exception e) { return null; }
    }

    private void followUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username to follow:");
        String targetUsername = scanner.nextLine();

        User target = userService.getUserByUsername(targetUsername);

        if (target == null) {
            System.out.println("User not found.");
            return;
        }

        if (target.getUsername().equals(loggedInUser.getUsername())) {
            System.out.println("You cannot follow yourself.");
            return;
        }

        loggedInUser.getFollowManager().follow(target.getFollowManager());
        notificationService.sendNotification(loggedInUser, "You are now following " + target.getUsername());
    }

    private void likePost() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter post ID to like:");
        String id = scanner.nextLine();

        Post post = feedService.searchPost(id);
        if (post == null) {
            System.out.println("Post not found.");
            return;
        }

        LikePostCommand cmd = new LikePostCommand(post, loggedInUser, likeService);
        commandInvoker.executeCommand(loggedInUser.getId(), cmd);
        if (cmd.wasExecuted()) {
            notificationService.sendNotification(loggedInUser, "The post was liked successfully.");
        }
    }

    private void undoLastAction() {
        commandInvoker.undoLast(loggedInUser.getId());
    }

    private void showActivity() {
        commandInvoker.showHistory(loggedInUser.getId());
    }

    private void changeFeedSorting() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
    Choose feed sorting:
    1. Newest posts
    2. Only posts from users you follow
    """);

        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> feedService.setSortingStrategy(new SortByNewestStrategy());
            case "2" -> feedService.setSortingStrategy(new SortByFollowingOnlyStrategy());
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        System.out.println("Sorting updated!");
    }

    private void unfollowUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username to unfollow:");
        String username = scanner.nextLine();

        User target = userService.getUserByUsername(username);

        if (target == null) {
            System.out.println("User not found.");
            return;
        }

        if (!loggedInUser.getFollowManager().getFollowing()
                .contains(target.getFollowManager())) {
            System.out.println("You are not following this user.");
            return;
        }

        loggedInUser.getFollowManager().unfollow(target.getFollowManager());
        notificationService.sendNotification(loggedInUser, "You unfollowed " + target.getUsername());
    }

}
