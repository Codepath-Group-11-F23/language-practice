(AND101) Mobile App Dev - Group 11
===

Chosen API: https://developer.themoviedb.org/docs

## Ideation:

**Monika:**

1. Idea #1: App that gives movie recs based on:
Actor 
https://developer.themoviedb.org/reference/search-person
Popularity
https://developer.themoviedb.org/reference/tv-series-popular-list
Genre
https://developer.themoviedb.org/reference/genre-movie-list
Where you can watch it:
https://developer.themoviedb.org/reference/movie-watch-providers


    Additional cool features: create a watchlist and have a vote button so that you and your partner can choose a movie without fighting LOL

    I’m pretty positive this is what could be used to achieve the rating feature 
    (Add) https://developer.themoviedb.org/reference/movie-add-rating
    (Remove) https://developer.themoviedb.org/reference/movie-delete-rating

    User Accounts so that multiple people can see and edit the watchlists (?)
    https://auth0.com/signup?utm_content=sitelink-signup&utm_source=google&utm_campaign=amer_mult_usa_all_ciam-all_dg-ao_auth0_search_google_text_kw_utm2&utm_medium=cpc&utm_term=auth%20providers-c&utm_id=aNK4z0000004GhIGAU&gad_source=1&gclid=Cj0KCQjwhfipBhCqARIsAH9msbl1gpQNUpvicH1hHxKXmCn41gfZU0A5AU-GJ5TfPEO2R_U6tQ12MjkaAq4gEALw_wcB

    Email invite incorporated so a person can be pinged about being added to watchlist (? this might be too much)

    SQL database to store the fetched information (?)
    https://dev.mysql.com/downloads/mysql/

    Get necessary permissions 

    My idea is to work only on movies and if we have time we can do the same for tv shows. 

2. Idea #2: Where to watch app:
     - App searches the database for the locations in which user can watch the movie. Nowadays certain streaming companies have exclusivity for movies and it might be handy to have an app that searches those companies quickly
    - Search for a genre/ actor/ name of movie
    - Get the streaming company name 
    - https://api.themoviedb.org/3/movie/{movie_id}/watch/providers

3. Idea #3: What is the Movie About
    - An app that lets you watch a little video from a movie and decide whether you want to watch it
    - Use a random function/ search by genre/actor/movie name/ etc. 
    - Get to know the movie by watching a little recap!
    - https://api.themoviedb.org/3/movie/{movie_id}/videos

**Michelle:**

1. Idea #1: Six Degrees of Kevin Bacon – the App!

    For those that don’t know the game: “Six degrees of Kevin Bacon”, it is     said that any actor can be traced back to Kevin Bacon within six means       of connection.

    I.e. [User Entered "Scarlet Johansen"] → Avengers Endgame → Chris Pratt → Guardians of the Galaxy Christmas Special (which had Kevin Bacon as a guest appearance)

    Enter an Actor’s name and it will tell you how many degrees it is to Kevin Bacon and displays the familial path to get there
    Pro – hilarious. A fun, unique way to think about data.
    Con – probably a giant puddle of recursion 🙂
    We’d probably need to be pretty specific about how this information is displayed. 

    Accesses the movies of a specific person:
    https://developer.themoviedb.org/reference/person-movie-credits
    Accesses the cast and crew of a specific movie:
    https://developer.themoviedb.org/reference/movie-credits


2. Idea #2: Movie Trivia App

    Displays for the user the cast and crew of the movie and year it came out and asks the user to name that movie. User is allowed to query for genres.

    Accesses the cast and crew of a specific movie:
    https://developer.themoviedb.org/reference/movie-credits
    Accesses the year a movie came out (or maybe a single-sentence promo phrase?):
    https://developer.themoviedb.org/reference/movie-details
    Displays an image of the movie’s poster on correct answer:
    https://developer.themoviedb.org/reference/movie-images
    
    
3. Idea #3: Top Rated Watch Logger

    Problem solved: For movie buffs, user can select their genre of choice and attempt to watch all X # of top-rated films in that genre. We can offer the user a completion task bar that logs how many films have been watched out of all total number of movies in the top-rated genre.
    
    Accesses the top-rated movies: 
    GET https://api.themoviedb.org/3/movie/top_rated
    Discover used to sort this data:
    GET https://api.themoviedb.org/3/discover/movie
    

**Ryan**
1. Idea #1: Basic Movie Review App

    User can search for movies by name (with the option to filter by genre) or browse through all movies (or by genre). User can ‘like’ certain movies. User can rate and review liked movies.

    Stretch feature could include sharing that info with friends and browsing their reviews/liked movies.

    Problem solved: User can keep track of their favorite and least favorite movies. And for us: it’s a very basic idea for an app.
    API: GET request to pull list of movies for searching functionality (https://developer.themoviedb.org/reference/movie-details and see ‘genre’). POST request to add a rating/review (https://developer.themoviedb.org/reference/movie-add-rating).

2. Idea #2: Movie Guessing Game (Basically the same as Michelle’s idea but offer multiple choices for user to guess)

    User can select Easy or Hard mode. Easy mode would give the user 5 facts about the movie (release date, revenue, budget, genre, etc.) and give the user 3 movie options to choose from. Hard mode would give the user 3 facts about the movie and give the user 4 movie options to choose from. 

    Stretch feature would be making accounts and having a leader board.

    Problem solved: Fun!
    API: GET details (https://developer.themoviedb.org/reference/movie-details)

3. Idea #3: Movies for Language Practice

    User can select the language that they want to learn and get a list of TV shows/movies that are in that language. Then they can select a title to read about it. Users can finally ‘like’ a movie to put it on their ‘To Watch’ list.

    Problem solved: When learning a language, it’s extremely helpful to watch movies and tv shows in that language to practice listening and expand your vocabulary. Having a list of movies/tv shows that are in that language would help a user to find ideas for what to watch.
    API:GET details (https://developer.themoviedb.org/reference/movie-details) and see ‘original_langage’ in response; GET translation (https://developer.themoviedb.org/reference/movie-translations) and see ‘overview’ in specific language


**Lawrence**

1. Idea #1: Movie Recommender Questionnaire (more of a gamified version of Monika’s app suggestion)

    Ask a list of questions where users are presented with 2+ choices and a prompt (You’re watching a character in a haunted house stare down a dark corridor with flickering lights. Do you (a) curl under a blanket in fear of a jumpscare (b) lean in eagerly in anticipation of what might come next) Idea is to narrow down list of films that they might like depending on their response. Could also choose between two different actors or neither as question options if we want to.
    . 
    Stretch Features: Add a [Give Me a Different Movie] button if the user has already seen the movie recommended and wanted to watch a different movie that fits their specifications. 

    API: GET https://developer.themoviedb.org/reference/movie-details 


2. Idea #2: Movie Dateline 

    List movies that you saw on a specific date and create a scrolling date showcasing the movies you watched at a specific time and genres you were interested in. <----movie------movie-------movie---->

    API: GET https://developer.themoviedb.org/reference/movie-details 

3. Idea #3: Movielde 

    Guess the movie based off a small section of the movie poster (cut off a small image of the original movie poster) with a set amount of guesses 

    https://image.tmdb.org/t/p/original/[poster_path]

**Noah**

1. Idea #1: App like Tindr where you can add movies to different lists like not interested, interested, watched and didn’t like, watched and liked, favorites. Can build off of Monika’s idea to help watch a movie. Can also recommend based on movies you liked.
    Problem solved: Can also help avoid re-watching movies if you forgot if you’ve watched     something, recommend based on movies you like, and help find movies to watch.

    API: https://api.themoviedb.org/3/movie/{movie_id} 

2. Idea #2: An app that lets you look up a watch provider and it filters the movies with the requested watch provider to give a list of all movies available. Can also search and filter based on ratings, genre, etc.
    Problem solved: If you only have a specific streaming platform, shows a list of all       movies are on there since many are hidden or hard to find without searching. Would         give visibility to movies you may not otherwise watch.
    
    API: https://api.themoviedb.org/3/movie/{movie_id} 

3. Idea #3: An app that lets users view a list of movies that are not released yet and gives the planned release date. User will enter a date range for release dates. Can add to a list and get notifications when it comes out.
    Problem solved: Helps bring less advertised movies into view to watch, also reminds       users when a movie they are anticipating watching is released. 
    
    API: https://api.themoviedb.org/3/movie/upcoming 
    
## Top 3 New App Ideas
1. Movies for Language Practice
2. Movie Tinder
3. Six Degrees of Kevin Bacon

## New App Ideas - Evaluate and Categorize

### Top 3 Candidate #1: Movies for Language Practice ###
**Mobile:** 
How uniquely mobile is the product experience?
*Admittedly, this is not uniquely mobile; however, as a tool to reinforce the learning of a new language this would absolutely be an asset!*

What makes your app more than a glorified website?
*The ability for this to be used on a mobile device means opportunities for continued learning become mobile as-well! For instance, if a user wants to plan their learning experience while they’re on the go, they can do so before they forget.*

**Story:** 
How compelling is the story around this app once completed?
*This app’s story is quite compelling given that language learning is all about communicating with real people and movies are created to appeal to real people. The app would help learners connect the formal aspects of language learning with real use cases from everyday life.*

*Users can input their desired language of choice and genre and receive a list of movies with appropriate subtitles that exist meeting their criteria. This allows users to hear authentic accents and slang in their chosen language and gain a more genuine command of the language.*

How clear is the value of this app to your audience?
*This is an already well-established method of reinforcing the learning of new languages, so the value already exists. We’re simply providing a straight-forward method of obtaining media and content that is available for the consumer.*

How well would your friends or peers respond to this product idea?
*I think this app has a huge potential of being liked by our peers albeit it would probably only be directly consumed by those learning a new language. A big part of their response would be dependent on the usability of the app so we will be focusing heavily on making the UI beautiful and accessible.*

**Market:** 
How large or unique is the market for this app?
*Language-based applications faced a surge during the pandemic as a means to facilitate remote communication, support virtual learning, bridge social and cultural gaps, and keep individuals' minds occupied with engaging content. However, there are only a few alternatives to supplemental learning platforms to reinforce what is being taught via these education applications. Additionally, learners could google to find a list of movies in their target language but it would likely only include the most popular movies. So we believe our app would fill in a critical gap in the learning journey given that it would allow users to search through thousands of titles.*

What's the size and scale of your potential user base?
*Applications that model sympathetic, language-learning ideas (such as duolingo) have user bases that extend well beyond 200 million users, with 21.4 million daily users (see hyperlink reference). We can estimate that some percentage of the millions of language learners would be interested in reinforcing their learning with movies and thus might download our app!*

Does this app provide huge value to a niche group of people?
*Absolutely! Similar applications such as Lingopie have seen as many as 100k+ downloads.*

Do you have a well-defined audience of people for this app?
*Yes! Our audience is anyone attempting to learn a new language.*

**Habit:** 
How habit-forming or addictive is this app?
*Considering it is not atypical for users of DuoLingo to practice their language of choice daily, I would say an application that provides supplemental exposure to users by relatable content that is known and well-enjoyed would be well-used! However, we are considering adding a ‘streak’ component where users can track how many weeks in a row they have watched a movie with their target language. The desire to not break the streak would help the app be habit-forming.*

How frequently would an average user open and use this app?
*Because we are asking users to listen / watch content they already know and enjoy, the act of using this application could be used as often as daily without feeling like “work”.*

Does an average user just consume your app, or do they use it to create something?
*Users using this app would just consume the movie in their target language. One could argue they are using it to create new knowledge!* 

**Scope:** 
How well-formed is the scope for this app?
*The scope of this app is moderately well-formed. We have an idea of the different roles for each team member, how we will use the API, and some user-stories decided. We would still need to decide on how ambitious we want to be in building the app since we have many ideas for extra features!*

How technically challenging will it be to complete this app by the deadline?
*This application will present us with a deliverable product by the deadline without issues. It is fairly straightforward, all things considered, but does allow us ample opportunity to stretch in ways we feel appropriate.*

Is a stripped-down version of this app still interesting to build?
*I think so. I think getting practice with android studio in whatever capacity we ultimately decide will be beneficial – especially if we take that knowledge towards creating something practical!*

How clearly defined is the product you want to build?
*This particular product does not necessarily require us to reinvent the wheel on what is being delivered, so creating a clean, user-friendly UX / UI will be the key.*


### Top 3 Candidate #2: Movie "Tinder" App ###
**Mobile:** 
*How uniquely mobile is the product experience? 
This app would have users swipe to categorize a movie into different categories. While swiping on a computer is possible, mobile gives a much better user experience for swiping to complete an action.*

What makes your app more than a glorified website?
*The application involves built-in mobile phone features such as swiping left and right. While the same can be accomplished with a computer, the mobile application interface is much more straightforward, less cluttered (only showing bare minimum information in two options) and more intuitive with swiping left and right similar to how tinder works.*

**Story:** 
How compelling is the story around this app once completed?
*A stretch feature for this app would be the ability to share your ‘liked’ or ‘interested in’ movie lists with friends. This feature would make the story quite compelling since it would be all about helping to connect people through entertainment. Friends, partners, and family members could even use it to decide on a movie to watch together! However, without that feature, the big appeal of this app is the novelty of swiping on movie titles and it’d be less so about the story.*

How clear is the value of this app to your audience?
*The value is fairly clear for the user given that it helps them discover (or re-discover) movies that they might be interested in. By presenting the user with one movie and asking them to decide if they are interested or not, it compels them to really think about what draws them to the art of film. When considering the stretch feature of sharing the list of movies they’re interested in with others, the value would come from bonding over shared interests.*

How well would your friends or peers respond to this product idea?
*Admittedly not everyone in our lives is interested in watching more movies so they might not be interested in this app at all. Additionally, many people rely on movie reviews from professional critics or others in their lives to decide on if they’re interested in a movie or not. They might not see the value in basing their interest on just the basic details of a movie which is what we’d show in our app.*

**Market:** 
How large or unique is the market for this app?
*The market for this app is anyone who likes to watch movies, likes to discover new movies, or struggles to find movies to watch. Thus, the market is potentially hundreds of millions of people!* 

What's the size and scale of your potential user base?
*According to the MPAA, the population of the United States and Canada aged two and over totaled 348.3 million people in 2017, with 76 percent, or 264.7 million, going to the movies at least once (source). We can assume the base on movie-watchers has since 2017 increased.*

Does this app provide huge value to a niche group of people?
*This app provides value to any group of people, not just a specific niche.*

Do you have a well-defined audience of people for this app?
*No, this is a more generalized application for people to scroll through movies and choose whether it's suitable for them or not. There is no one particular type of person we are targeting.*

**Habit:** 
How habit-forming or addictive is this app?
*The app can be somewhat habit forming if they use this as their main method of choosing a movie, but it depends on the effort of the user and how indecisive they are. We do not anticipate it to be highly addictive given the simplicity of it, just more of a log of movies that they might want to watch.*

How frequently would an average user open and use this app?
*An average user would open and use this app whenever they are looking for a movie to watch. This would widely vary from person to person. *

Does an average user just consume your app, or do they use it to create something?
*This app can be used to create a database of movies the user wants to watch, has watched and doesn’t like, has watched and does like, or movies that the user does not want to watch.The user would be able to access this database anytime.*

**Scope:** 
How well-formed is the scope for this app?
*The scope of this app is moderately well-formed. We have an idea of the different roles for each team member and how we will use the API. We would just need to decide on whether we wanted to try to implement the stretch feature or not as that would add complexity to the scope.*

How technically challenging will it be to complete this app by the deadline?
*It would be moderately challenging depending on the UI/UX implementation and how well-animated it has to be (like swiping gestures and making the cards move left and right). Storing the information and API calls would be the simpler tasks.*

Is a stripped-down version of this app still interesting to build?
*The app at its core is a yes/no decider. It’s not too interesting in terms of clicking yes and no without the addition of compelling stretch features..*

How clearly defined is the product you want to build?
*It is very clearly defined given that we have a model to base it off of (Tinder) and plan to make it much simpler than that.* 


### Top 3 Candidate #3: Six Degrees of Kevin Bacon ### 
**Mobile:** 
How uniquely mobile is the product experience?
*This is a very unique idea itself but not necessarily mobile-specific. It does give the users a remote option of playing this fun game. *

What makes your app more than a glorified website?
*We do think this app could just be a glorified website which is definitely part of our decision-making criteria for which app we will build.*

**Story:** 
How compelling is the story around this app once completed?
*Everyone likes a bit of fun. One day, Michelle was sitting in the shade of an oak by a lake, and contemplated ways to fill her free time. She came up with a great idea of playing Six Degrees of Separation but instead Kevin Bacon, we’re talking actors. Michelle is also a huge fan of recursion and wanted to incorporate her favorite part of programming into the project.*

How clear is the value of this app to your audience?
*It’s very clear. The value is simply to laugh and have fun.* 

How well would your friends or peers respond to this product idea?
*Fantastic. Who doesn’t like a good game? It’s a very clever way to learn new things about celebrities.*

**Market:** 
How large or unique is the market for this app?
*This app is suitable for everyone of any age. Perhaps only a subset of people might know who Kevin Bacon is but the rest can learn about him! Therefore, the market is quite large for this app.*

What's the size and scale of your potential user base?
*Theoretically, since anyone could play this game the user base is quite large. However, we know that fans of this game would be a niche audience.*

Does this app provide huge value to a niche group of people?
*Definitely. We believe the app would be great for nerdy people who are interested in random facts.* 

Do you have a well-defined audience of people for this app?
*This audience would be fans of Kevin Bacon or the real-life game (of the same name) or anyone who likes trivia-like games. Essentially nerds.*

**Habit:** 
*How habit-forming or addictive is this app?
We don’t predict this app to be habit-forming. It is fun but it wouldn’t make people addicted.*

How frequently would an average user open and use this app?
*We believe it would be most useful during a gathering or party. Since these are relatively rare, it would be used quite infrequently by most people.*

Does an average user just consume your app, or do they use it to create something?
*The user simply consumes the data the API is providing.* 

**Scope:** 
How well-formed is the scope for this app?
*The scope is quite poorly formed for this app since we know that generating the algorithm for finding the degrees of separation might involve recursion which we are fairly intimated by. We believe that implementing this app might require more than a few weeks' time.*

How technically challenging will it be to complete this app by the deadline?
*It might be pretty challenging taking into account the recursion algorithm. Then, creating a compelling UI would also be quite a challenge since we would want it to be very unique and funny.*

Is a stripped-down version of this app still interesting to build?
*Yes and that’s what we’re aiming for so far.*

How clearly defined is the product you want to build?
*Very well defined. It is based on a game that already exists but this would just be the accompanying mobile app. This model of a game helps us understand the goals of the app.*

## Our Final Idea: Movies for Language Practice ##
Movies for Language Practice