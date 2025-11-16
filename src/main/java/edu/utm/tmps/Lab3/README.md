# Laboratory Work Nr. 3: Structural Design Patterns

---
# Theory

In software engineering, structural design patterns focus on how classes and objects are combined to build larger and more flexible structures. While creational patterns deal with how objects are created, structural patterns deal with how those objects are arranged, connected, and reused. They help developers avoid rigid class hierarchies and instead promote composition, which makes systems easier to extend and maintain.

Structural patterns make it possible to add new behaviors, control access to objects, simplify interactions between subsystems, or attach additional responsibilities at runtime. Because these patterns focus on the structure of the program, they allow complex systems to be built from smaller, simpler parts without tightly coupling those parts to each other. Some well-known patterns in this category include Adapter, Bridge, Composite, Decorator, Facade, Flyweight, and Proxy. Each of them solves a specific structural problem such as wrapping objects to extend their functionality, simplifying a complicated API into a single access point, or controlling the creation and use of expensive objects.

In this project, the structural design patterns were used to enhance the functionality of the social media system while keeping the code clean and extendable. The Decorator pattern allowed dynamic extension of profile picture features like filters and resizing. The Proxy pattern handled caching and controlled access to the feed and user posts, improving efficiency. The Facade pattern unified multiple subsystem operations into a single simplified interface for the user, making the application easier to use and understand.

---

# Objectives

1. Study and understand the Structural Design Patterns.
2. Extend the social media system by adding new functionalities that are implemented using structural patterns.
3. Use at least three structural design patterns to improve functionality, flexibility, and maintainability while keeping a single client entry point and a clean package structure.

---

# Implementation

The proxy design pattern is used in the project to control access to the feed of posts. Instead of letting the client directly request posts from the feed service, a proxy object is placed in front of it. This proxy behaves like the real service, but it adds an extra behavior: caching. When the feed is viewed for the first time, the proxy retrieves posts from the real service and stores them in local memory. The next time the client requests the same data, the proxy returns the cached version instead of asking the real service again. This reduces repeated work and improves the performance of the system. The proxy holds a reference to the real feed service so that it can delegate calls when needed, and it also stores cached results for all posts, posts by a specific user, and search results. Whenever a new post is created in the system, the post service clears the proxy’s cache, ensuring the data always stays correct.

```java
public class CachedPostsProxy implements IFeedService {
    private final IFeedService feedService;
    private ArrayList<Post> cachedAll = null;

    public CachedPostsProxy(IFeedService feedService) {
        this.feedService = feedService;
    }

    @Override
    public ArrayList<Post> retrieveAllPosts() {
        if (cachedAll == null) {
            cachedAll = feedService.retrieveAllPosts();
        }
        return cachedAll;
    }
}
```

---

The decorator design pattern is used to dynamically add extra behavior to profile-picture editing without modifying the original profile service. Instead of putting all features inside a single class, decorators wrap around the existing service and extend its behavior step by step. In this project, the decorator pattern allows resizing and applying filters to a profile picture. Each decorator implements the same interface as the original service, so it can be used in the same way, but it adds a new action before or after calling the method of the wrapped object. The `ResizeDecorator` adds resizing behavior and updates the saved image size inside the profile data, and the `FilterDecorator` applies a chosen filter and records it in the profile. Because decorators can wrap each other, the user can combine features such as applying a filter and then resizing the image without changing the core profile service.

```java
public abstract class ProfilePictureDecorator implements IProfilePictureService {
    protected IProfilePictureService wrapee;
    public ProfilePictureDecorator(IProfilePictureService wrapee) {
        this.wrapee = wrapee;
    }

    @Override
    public void addProfilePicture(String picture) {
        wrapee.addProfilePicture(picture);
    }
}
```

---

The facade design pattern provides a simple and unified interface for interacting with all parts of the social-media system. Instead of the client navigating through many different services such as user management, post creation, notification sending, and feed retrieval, the facade exposes a single class called `SocialMediaFacade` that organizes all operations in one place. The facade receives all services through its constructor and coordinates their usage when the user creates posts, edits their profile, views the feed, or searches for content. It manages the command flow, handles user sessions, and delegates the actual work to the correct service. This makes the system much easier to work with because the main program only interacts with the facade, while the complex interactions between services are hidden behind it. The facade also uses the Singleton principle to ensure that there is only one central entry point to the application.

```java
public static synchronized SocialMediaFacade getInstance(
        IUserService userService,
        IPostService postService,
        IFeedService feedService,
        INotificationService notificationService
) {
    if (instance == null) {
        instance = new SocialMediaFacade(userService, postService, feedService, notificationService);
    }
    return instance;
}
```

---

# Results 

The results show how the structural design patterns improved the flow and behavior of the social media system. The **Facade pattern** made the interaction very simple for the user by hiding all the complex actions behind one clear menu, so creating posts, editing the profile, and viewing the feed looked easy and organized. The **Proxy pattern** worked correctly to optimize performance by caching posts, which is why you see “Cache MISS” the first time data is loaded and “Cache HIT” on later requests. This proves that the system is avoiding unnecessary data processing. The **Decorator pattern** was used when editing the profile picture, allowing filters and resizing to be added dynamically without changing the original profile service. This made features like choosing filters, resizing images, and keeping default settings possible in a flexible way. Overall, the output demonstrates that the system became cleaner, easier to use, and more efficient thanks to these structural design patterns.


````
Enter username (4–30 chars):
maria_elena
UserService: registered maria_elena
maria_elena got this notification: Welcome to the app!
Your feed:
Cache MISS: retrieving all posts...
No posts found

Choose an option:
1. Create Post
2. View My Posts
3. Search Post
4. View Feed
5. Logout
6. Edit Profile Picture
7. Edit Profile Details
8. View Profile Info
0. Exit

1
Choose post type:
1. Text post
2. Image post
3. Video post
0. Go back

3
Enter post content (text or image/video path):
cats.mp4
PostService: created post for user-1
maria_elena got this notification: Your post was published!
=== YOUR VIDEO POST ===
User: user-1
Video: cats.mp4
Created at: 2025-11-16T14:06:36.181763200

Choose an option:
1. Create Post
2. View My Posts
3. Search Post
4. View Feed
5. Logout
6. Edit Profile Picture
7. Edit Profile Details
8. View Profile Info
0. Exit

6
Enter profile picture filename:
sunset.png
Validating image size...
Checking picture quality...
maria_elena added profile picture sunset.png
Applying default enhancement filter...
Compressing image for faster loading...
maria_elena got this notification: Profile picture updated.
Resize options:
1. Make smaller
2. Make bigger
3. Keep default size

3
Keeping default size.
Available filters:
1. sepia
2. black-and-white
3. smooth
4. retro
5. warm
6. Keep default filter
2
Applied filter: black-and-white
maria_elena got this notification: Filter applied: black-and-white
maria_elena got this notification: Profile picture updated successfully.

Choose an option:
1. Create Post
2. View My Posts
3. Search Post
4. View Feed
5. Logout
6. Edit Profile Picture
7. Edit Profile Details
8. View Profile Info
0. Exit

7
What would you like to update?
1. Age
2. Biography
3. Location
0. Go back

1
Enter new age:
21
maria_elena added age 21
maria_elena got this notification: Age updated successfully.

Choose an option:
1. Create Post
2. View My Posts
3. Search Post
4. View Feed
5. Logout
6. Edit Profile Picture
7. Edit Profile Details
8. View Profile Info
0. Exit

8

=== Your Profile Info ===
Username: maria_elena
Profile Picture: sunset.png
Filter Applied to Profile Picture: black-and-white
Size of the Profile Picture: original
Biography: null
Age: 21
Location: null
==========================


Choose an option:
1. Create Post
2. View My Posts
3. Search Post
4. View Feed
5. Logout
6. Edit Profile Picture
7. Edit Profile Details
8. View Profile Info
0. Exit

5
Goodbye maria_elena

Enter username (4–30 chars):
anna
UserService: registered anna
anna got this notification: Welcome to the app!
Your feed:
Cache MISS: retrieving all posts...
user-1: cats.mp4

Choose an option:
1. Create Post
2. View My Posts
3. Search Post
4. View Feed
5. Logout
6. Edit Profile Picture
7. Edit Profile Details
8. View Profile Info
0. Exit

1
Choose post type:
1. Text post
2. Image post
3. Video post
0. Go back

2
Enter post content (text or image/video path):
rainbow.png
PostService: created post for user-2
anna got this notification: Your post was published!
=== YOUR IMAGE POST ===
User: user-2
Image: rainbow.png
Created at: 2025-11-16T14:10:13.810892

Choose an option:
1. Create Post
2. View My Posts
3. Search Post
4. View Feed
5. Logout
6. Edit Profile Picture
7. Edit Profile Details
8. View Profile Info
0. Exit

2
Your posts:
Cache MISS: retrieving posts for user user-2
 - rainbow.png

Choose an option:
1. Create Post
2. View My Posts
3. Search Post
4. View Feed
5. Logout
6. Edit Profile Picture
7. Edit Profile Details
8. View Profile Info
0. Exit

3
Enter post ID (example: post-text-1/post-vid-1/post-img-1):
post-vid-1
Cache MISS: searching post post-vid-1
Post found: cats.mp4

Choose an option:
1. Create Post
2. View My Posts
3. Search Post
4. View Feed
5. Logout
6. Edit Profile Picture
7. Edit Profile Details
8. View Profile Info
0. Exit

````

---

# Conclusion

During this laboratory work, the focus was on understanding and applying structural design patterns to extend and improve a social media system. These patterns helped organize how different parts of the system interact, allowing new features to be added without changing existing code or breaking existing behaviors. By working with patterns such as Facade, Decorator, and Proxy, the project demonstrated how structural patterns promote flexibility and clear organization in software architecture.

The Facade pattern simplified the interaction between subsystems by providing one unified interface for all user operations. This made the application easier to use and allowed all internal processes to remain hidden from the client. The Decorator pattern provided a way to attach additional behavior to objects at runtime, such as applying filters or resizing profile pictures, without modifying the original profile service. The Proxy pattern helped control access to data, especially through caching, improving performance by avoiding repeated expensive operations.

Through this implementation, it became clear how structural patterns can transform a simple system into a more robust and scalable one. They allowed features to grow naturally while keeping the codebase organized, maintainable, and easy to extend in the future. This laboratory work successfully demonstrated the importance of structural design patterns in real-world applications and showed how they contribute to building flexible and well-structured software systems.



