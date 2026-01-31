

# Laboratory Work Nr. 4: Behavioral Design Patterns

---

# Theory

Behavioral design patterns focus on how objects interact and communicate with each other. Instead of describing how objects are created or how they are structured, behavioral patterns deal with how responsibilities are divided, how requests are passed, and how actions are organized inside a system. These patterns help a program become more flexible by reducing direct connections between classes and letting actions change dynamically at runtime.

Behavioral patterns solve problems such as undoing previous actions, notifying related objects when something changes, choosing algorithms at runtime, and encapsulating operations so they can be reused or changed easily. Some well-known behavioral patterns include Strategy, Observer, Command, Iterator, Template Method, State, and Chain of Responsibility. Each pattern focuses on coordinating behavior rather than modifying structure.

In this project, several behavioral design patterns were implemented to improve how the social media system manages actions, notifications, and sorting of posts. The **Command pattern** was used to enable liking and unliking posts with undo support. The **Observer pattern** allowed users to follow each other and get automatic notifications when someone they follow publishes a new post. The **Strategy pattern** controlled how the feed is sorted, allowing users to switch between “Newest Posts” and “Posts Only From Users You Follow.” These patterns made the system more flexible, cleaner, and easier to extend.

---

# Objectives

1. Study and understand the main Behavioral Design Patterns.
2. Extend the social media system using **at least three** behavioral patterns.
3. Implement new features such as:

    * Undoing likes
    * Following/unfollowing notification system
    * Sorting the feed using different algorithms
4. Keep the application modular, reusable, and easy to extend.

---

# Implementation

## 1. Command Pattern — Liking and Undoing Likes

The Command pattern was used to turn the “like” action into an object that can be executed and undone. Instead of directly liking a post inside the facade, the action is wrapped inside a `LikePostCommand` object. This command stores everything needed to perform the action: the post, the user, and the service responsible for applying the like. The invoker, named `CommandInvoker`, receives these commands and executes them. If the like succeeded, the command is saved in the history so it can later be undone.

The ability to undo is useful because the user may change their mind. The invoker simply takes the last executed command for that user and calls its `undo()` method. This pattern keeps the logic clean and lets you extend the system later with other commands like “Delete Post” or “Edit Post” without modifying existing code.

```java
public class LikePostCommand implements ICommand {
    private final Post post;
    private final User user;
    private final PostLikeService likeService;
    private boolean executed = false;

    public LikePostCommand(Post post, User user, PostLikeService likeService) {
        this.post = post;
        this.user = user;
        this.likeService = likeService;
    }

    @Override
    public void execute() {
        if (!executed && likeService.addLike(post, user)) {
            executed = true;
        }
    }

    @Override
    public void undo() {
        if (executed && likeService.removeLike(post, user)) {
            executed = false;
        }
    }

    @Override
    public boolean wasExecuted() {
        return executed;
    }

    @Override
    public String getDescription() {
        return user.getUsername() + " liked post " + post.getId();
    }
}
```

The invoker stores the commands per user and calls `execute()` and `undo()`:

```java
public class CommandInvoker {
    private final Map<String, List<ICommand>> historyPerUser = new HashMap<>();

    public void executeCommand(String userId, ICommand command) {
        command.execute();
        if (command.wasExecuted()) {
            historyPerUser
                .computeIfAbsent(userId, k -> new ArrayList<>())
                .add(command);
        }
    }

    public void undoLast(String userId) {
        List<ICommand> history = historyPerUser.get(userId);

        if (history == null || history.isEmpty()) {
            System.out.println("No recent activity to undo.");
            return;
        }

        ICommand last = history.removeLast();
        last.undo();
    }
}
```

This pattern makes user actions reversible and keeps the system open for new commands in the future.

---

## 2. Observer Pattern — Following Users and Getting Notifications

The Observer pattern was used to notify users whenever someone they follow publishes a new post. Instead of manually messaging every follower, the system automatically informs all “observers” when a change happens.

A `FollowManager` acts as both a subject and an observer. It keeps lists of followers and of users the current user follows. When a user creates a new post, the `FollowManager` sends notifications to all their followers. When a user follows or unfollows someone, observers are added or removed.

```java
public class FollowManager implements ISubject, IObserver {
    private final User user;
    private final List<FollowManager> followers = new ArrayList<>();
    private final List<FollowManager> following = new ArrayList<>();

    public FollowManager(User user) {
        this.user = user;
    }

    @Override
    public void update(String message) {
        System.out.println(user.getUsername() + " received -> " + message);
    }

    @Override
    public void registerObserver(IObserver obs) {
        followers.add((FollowManager) obs);
    }

    @Override
    public void removeObserver(IObserver obs) {
        followers.remove(obs);
    }

    @Override
    public void notifyObservers(String message) {
        for (FollowManager f : followers) {
            f.update(message);
        }
    }

    public void follow(FollowManager target) {
        target.registerObserver(this);
        following.add(target);
    }

    public void unfollow(FollowManager target) {
        target.removeObserver(this);
        following.remove(target);
    }

    public List<FollowManager> getFollowing() {
        return following;
    }

    public User getUser() {
        return user;
    }
}
```

This pattern lets notifications be automatic and keeps the system loosely coupled.

---

## 3. Strategy Pattern — Sorting the Feed Dynamically

The Strategy pattern was used to choose different sorting algorithms for the feed. Instead of hard-coding one sorting rule, the system allows the user to switch between:

* **Newest posts first**
* **Posts only from users you follow**

Each sorting rule is a separate strategy that implements the same interface:

```java
public interface IFeedSortingStrategy {
    List<Post> sort(List<Post> posts, User currentUser);
}
```

Sorting by newest:

```java
public class SortByNewestStrategy implements IFeedSortingStrategy {
    @Override
    public List<Post> sort(List<Post> posts, User currentUser) {
        posts.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));
        return posts;
    }
}
```

Sorting only by followed users:

```java
public class SortByFollowingOnlyStrategy implements IFeedSortingStrategy {
    @Override
    public List<Post> sort(List<Post> posts, User currentUser) {
        List<String> followingIds = currentUser.getFollowManager().getFollowing()
                .stream()
                .map(f -> f.getUser().getId())
                .toList();

        List<Post> filtered = new ArrayList<>();
        for (Post p : posts) {
            if (followingIds.contains(p.getUserId())) {
                filtered.add(p);
            }
        }
        return filtered;
    }
}
```

The `FeedService` acts as the **Context**, storing the chosen strategy and using it:

```java
public class FeedService implements IFeedService {
    private IFeedSortingStrategy sortingStrategy = new SortByNewestStrategy();

    @Override
    public List<Post> retrieveFeed(User user) {
        List<Post> all = postService.getPosts();
        return sortingStrategy.sort(all, user);
    }

    public void setSortingStrategy(IFeedSortingStrategy strategy) {
        this.sortingStrategy = strategy;
    }
}
```

This makes the feed flexible, customizable, and easy to extend with more rules in the future.

---

# Results

The results show that behavioral patterns improved how the system handles actions, notifications, and sorting. Because of the Command pattern, users can now like posts and undo their last like, and each like action is saved in a history per user. The output clearly shows when a like succeeds and when an undo removes it. The Observer pattern worked correctly by sending notifications to followers when someone published a new post. Users who follow others automatically receive updates without writing extra logic. The Strategy pattern allowed users to change how the feed is sorted, showing either all posts by date or only posts from followed users.

**Example: User follows another account and receives notifications when that user publishes new content**

````
Login with an existing username or register with new one:
anna
UserService: registered anna
anna got this notification: Welcome to the app!
Your feed:
Cache MISS: FEED

Choose an option:
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

5
Goodbye anna

Login with an existing username or register with new one:
maria
UserService: registered maria
maria got this notification: Welcome to the app!
Your feed:
Cache MISS: FEED

Choose an option:
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

9
Enter username to follow:
anna
maria got this notification: You are now following anna

Choose an option:
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

5
Goodbye maria

Login with an existing username or register with new one:
anna
Welcome back, anna
anna got this notification: Welcome to the app!
Your feed:
Cache MISS: FEED

Choose an option:
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

1
Choose post type:
1. Text post
2. Image post
3. Video post
0. Go back

1
Enter post content (text or image/video path):
Hello World
PostService: created post for user-1
anna got this notification: Your post was published!
maria received -> anna published a new post: Hello World
=== YOUR TEXT POST ===
User: user-1
Text: Hello World
Created at: 2025-11-28T13:41:25.913629900
````

**Example: User likes a post, removes the like, then reviews their like history**

````
Login with an existing username or register with new one:
maria
Welcome back, maria
maria got this notification: Welcome to the app!
Your feed:
Cache MISS: FEED
user-1: Hello World

Choose an option:
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

10
Enter post ID to like:
post-text-1
Cache MISS: post post-text-1
maria liked this post.
maria got this notification: The post was liked successfully.

Choose an option:
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

12

Recent Liking Activity: 
- maria liked post post-text-1

Choose an option:
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

11
maria unliked this post.

Choose an option:
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

12
No recent activity to show.
````

**Example: User views personalized feed containing only posts from followed accounts**
````
Choose an option:
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

4
Your feed:
Cache MISS: FEED
user-2: sunset.png
user-2: Hello
user-1: Hello World

Choose an option:
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

13
Choose feed sorting:
1. Newest posts
2. Only posts from users you follow

2
Sorting updated!

Choose an option:
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

4
Your feed:
Cache MISS: FEED
user-1: Hello World

````

Together, these patterns made the social media system more dynamic and responsive. They helped separate responsibilities, avoided duplicate code, and made every feature easy to extend.

---

# Conclusion

This laboratory work focused on applying behavioral design patterns to enhance the functionality and flexibility of a social-media application. The Command pattern introduced undoable actions and made user interactions reversible. The Observer pattern allowed automatic notifications when followed users published new posts, improving communication between objects without coupling them tightly. The Strategy pattern made the feed customizable by letting users choose how posts are sorted.

Through these implementations, the system became much easier to extend with new behaviors. Features now operate independently but still work together smoothly through well-defined patterns. This demonstrated the power of behavioral design patterns in building clean, organized, and maintainable software systems.

