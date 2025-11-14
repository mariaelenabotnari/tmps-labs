package edu.utm.tmps.Lab3;

public class Client {
    public static void main(String[] args) {

        IPostService postService = new PostService();
        IFeedService feedService = new CachedPostsProxy(new FeedService(postService));
        IUserService userService = new UserService();
        INotificationService notificationService = new NotificationService();

        SocialMediaFacade app = new SocialMediaFacade(
                userService, postService, feedService, notificationService
        );

        app.run();
    }
}
