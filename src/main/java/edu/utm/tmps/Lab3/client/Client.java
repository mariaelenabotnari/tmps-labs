package edu.utm.tmps.Lab3.client;

import edu.utm.tmps.Lab3.domain.facade.SocialMediaFacade;
import edu.utm.tmps.Lab3.domain.proxy.CachedPostsProxy;
import edu.utm.tmps.Lab3.domain.service.*;
import edu.utm.tmps.Lab3.domain.strategy.FeedService;

public class Client {
    public static void main(String[] args) {

        IPostService postService = new PostService();

        CachedPostsProxy proxy = new CachedPostsProxy(new FeedService(postService));
        postService.setCacheProxy(proxy);

        IFeedService feedService = proxy;
        IUserService userService = new UserService();
        INotificationService notificationService = new NotificationService();

        SocialMediaFacade app = SocialMediaFacade.getInstance(
                userService, postService, feedService, notificationService
        );

        app.run();
    }
}
