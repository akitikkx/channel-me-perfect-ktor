package com.example.models.android

enum class NotificationChannels(val title: String, val body: String) {
    // 10 random values for popular Android notification channels for a TV show and movie app
    NEW_EPISODE_AVAILABLE(
        "New Episode Available",
        "A new episode of your favorite TV show is now available!"
    ),
    RECOMMENDED_MOVIES(
        "Recommended Movies",
        "Check out these top-rated movies based on your viewing history."
    ),
    UPCOMING_RELEASES("Upcoming Releases", "Get ready for the latest movie and TV show releases!"),
    WATCHLIST_UPDATES("Watchlist Updates", "New additions and removals from your watchlist."),
    TRENDING_CONTENT("Trending Content", "Discover the most popular shows and movies right now."),
    ACTOR_NEWS("Actor News", "Stay up-to-date on the latest news about your favorite actors and actresses."),
    FESTIVAL_HIGHLIGHTS("Festival Highlights", "Get a recap of the best moments from major film festivals."),
    BOX_OFFICE_HITS("Box Office Hits", "See which movies are dominating the box office this week."),
    CRITIC_REVIEWS("Critic Reviews", "Read expert opinions on the latest films and TV shows."),
    INDUSTRY_INSIGHTS(
        "Industry Insights",
        "Get behind-the-scenes news and analysis from the entertainment industry."
    );
}