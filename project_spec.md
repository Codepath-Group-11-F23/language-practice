# **TalkFlix!**

## Table of Contents

1. [App Overview](#App-Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
1. [Build Notes](#Build-Notes)

## App Overview

### Description 

**When learning a language, it’s extremely helpful to watch movies and tv shows in that language to practice listening and expand your vocabulary. Having a list of movies/tv shows that are in that language would help a user to find ideas for what to watch.**

### App Evaluation

<!-- Evaluation of your app across the following attributes -->

- **Category:**
    Edu-Tainment
    
- **Mobile:**
    How uniquely mobile is the product experience?
    *Admittedly, this is not uniquely mobile; however, as a tool to reinforce the learning of a new language this would absolutely be an asset!*

    What makes your app more than a glorified website?
    *The ability for this to be used on a mobile device means opportunities for continued learning become mobile as-well! For instance, if a user wants to plan their learning experience while they’re on the go, they can do so before they forget.*
    
- **Story:**
    How compelling is the story around this app once completed?
    *This app’s story is quite compelling given that language learning is all about communicating with real people and movies are created to appeal to real people. The app would help learners connect the formal aspects of language learning with real use cases from everyday life.*

    *Users can input their desired language of choice and genre and receive a list of movies with appropriate subtitles that exist meeting their criteria. This allows users to hear authentic accents and slang in their chosen language and gain a more genuine command of the language.*

    How clear is the value of this app to your audience?
    *This is an already well-established method of reinforcing the learning of new languages, so the value already exists. We’re simply providing a straight-forward method of obtaining media and content that is available for the consumer.*

    How well would your friends or peers respond to this product idea?
    *I think this app has a huge potential of being liked by our peers albeit it would probably only be directly consumed by those learning a new language. A big part of their response would be dependent on the usability of the app so we will be focusing heavily on making the UI beautiful and accessible.*
    
- **Market:**
    How large or unique is the market for this app?
    *Language-based applications faced a surge during the pandemic as a means to facilitate remote communication, support virtual learning, bridge social and cultural gaps, and keep individuals' minds occupied with engaging content. However, there are only a few alternatives to supplemental learning platforms to reinforce what is being taught via these education applications. Additionally, learners could google to find a list of movies in their target language but it would likely only include the most popular movies. So we believe our app would fill in a critical gap in the learning journey given that it would allow users to search through thousands of titles.*

    What's the size and scale of your potential user base?
    *Applications that model sympathetic, language-learning ideas (such as duolingo) have user bases that extend well beyond 200 million users, with 21.4 million daily users (see hyperlink reference). We can estimate that some percentage of the millions of language learners would be interested in reinforcing their learning with movies and thus might download our app!*

    Does this app provide huge value to a niche group of people?
    *Absolutely! Similar applications such as Lingopie have seen as many as 100k+ downloads.*

    Do you have a well-defined audience of people for this app?
    *Yes! Our audience is anyone attempting to learn a new language.*
- **Habit:**
    How habit-forming or addictive is this app?
    *Considering it is not atypical for users of DuoLingo to practice their language of choice daily, I would say an application that provides supplemental exposure to users by relatable content that is known and well-enjoyed would be well-used! However, we are considering adding a ‘streak’ component where users can track how many weeks in a row they have watched a movie with their target language. The desire to not break the streak would help the app be habit-forming.*

    How frequently would an average user open and use this app?
    *Because we are asking users to listen / watch content they already know and enjoy, the act of using this application could be used as often as daily without feeling like “work”.*

    Does an average user just consume your app, or do they use it to create something?
    *Users using this app would just consume the movie in their target language. One could argue they are using it to create new knowledge!* 
    
- **Scope:**
    How well-formed is the scope for this app?
    *The scope of this app is moderately well-formed. We have an idea of the different roles for each team member, how we will use the API, and some user-stories decided. We would still need to decide on how ambitious we want to be in building the app since we have many ideas for extra features!*

    How technically challenging will it be to complete this app by the deadline?
    *This application will present us with a deliverable product by the deadline without issues. It is fairly straightforward, all things considered, but does allow us ample opportunity to stretch in ways we feel appropriate.*

    Is a stripped-down version of this app still interesting to build?
    *I think so. I think getting practice with android studio in whatever capacity we ultimately decide will be beneficial – especially if we take that knowledge towards creating something practical!*

    How clearly defined is the product you want to build?
    *This particular product does not necessarily require us to reinvent the wheel on what is being delivered, so creating a clean, user-friendly UX / UI will be the key.*

## Product Spec

### 1. User Features (Required and Optional)

Required Features:

- **User can search for movies by title**
- **User can filter search with Subtitle & Original Language & Genre Filters**
- **Returned RecyclerView List of Movies**

Stretch Features:

- **Scorecard/Streak**
- **Memory Persistent Backlog of Selected Films & Favoriting** 
- **Seperate Detail Oriented View of a Selected Movie (Requires Fragment or Changing View)** 

### 2. Chosen API(s)

- GET details (https://developer.themoviedb.org/reference/movie-details) and see ‘original_langage’ in response
- GET translation (https://developer.themoviedb.org/reference/movie-translations) and see ‘overview’ in specific language

### 3. User Interaction

Required Feature

- **Specific movie in database**
  - => **EditText box where user can type key words/titles**
  
- **Available genres**
  - => **Drop down list near search bar**

- **Available movie-langages**
  - => **Drop down list near search bar**

- **Available movie-language subtitles**
  - => **Drop down list near search bar**

- **Movies matching search criteria**
    - => **User presses 'Find Movies' button**
    - => **Queried movies are displayed in RecyclerView that user can scroll through**
        - Movies are displayed with Movie Image and Title

Stretch Feature

- **Specific movies' additional details (year created, cast, etc.)**
  - => **User selects movie from list**

- **User can mark selected movie as "Watched"**
  - => **Selection is stored in list on client side**
  - => **Individual Movie is "tagged" with "star" or other relevant icon**

- **User can maintain a 'streak'**
  - => **When movie is marked as 'watched', that week is counted towards streak. User is encouraged to maintain their streak by watching a movie in their target language each week.**  
  
- **User can see percentage of movies watched**
  - => **# of movies marked as 'watched' is compared to number of available movies in database to show user percentage**

- **User can save a query for re-use**
    - => **User clicks 'Save search' button near RecyclerView list of results**

## Wireframes

<!-- Add picture of your hand sketched wireframes in this section -->
Ryan's hand-sketched wireframe
<img src="https://i.imgur.com/CK1TGmt.png" width=600>

Michelle's hand-sketched wireframe
<img src="https://i.imgur.com/7K69lme.jpg" width=600>

Monika's hand-sketched wireframe
<img src="https://i.imgur.com/2boiwGW.png" width=600>

Lawrence's hand-sketched wireframe
<img src="https://i.imgur.com/zTo9Cwk.jpg" width=600>


### [BONUS] Digital Wireframes & Mockups
Wireframe created in Figma
<img src="https://i.imgur.com/aV5Whml.png" width=600>

### [BONUS] Interactive Prototype
Interactive prototype created in Figma, recorded with ScreenToGif
<img src="https://i.imgur.com/5bHPMZ4.gif" width=600>

## Build Notes

Here's a place for any other notes on the app, it's creation 
process, or what you learned this unit!  

For Milestone 2, include **2+ Videos/GIFs** of the build process here!

Process Demo #1: recorded with ScreenToGif
<img src="https://i.imgur.com/pY1TzaW.gif" width=600>

Process Demo #2: recorded with ScreenToGif
<img src="https://i.imgur.com/zYioHSH.gif" width=600>

Process Demo #3: recorded with ScreenToGif


## License

Copyright **2023**, **Noah Dean, Lawrence Kwok, Michelle Mann, Monica Marek, Ryan Speese**

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.