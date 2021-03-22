### Building the project

* Clone the project, the master branch has the latest code.
* This App uses the private api key for getting top news. Get the API key from the [newsapi.org](https://newsapi.org/)
and put that key in the local.properties file in your project: Your local.properties will look like below:

```
sdk.dir=PATH_TO_ANDROID_SDK_ON_YOUR_LOCAL_MACHINE
apiKey=YOUR_API_KEY
```

### About the project

* Architecture of the app is designed using MVVM architecture pattern
* App is architect as single activity multiple fragment. Navigation graph is used for navigation and transition animation is added
* Koin is used as DI framework and databinding is used.
* Coroutine is used for threading
* Documentation is written for NewsListFragment class
* Unit test is written for NewsViewModel class

### About the app

* When app is launched it loads part of news list item, as user scroll down the items are
fetched as and when required. On selecting item user is navigated to next page where user
can read the the complete news story and also see additional info such as Likes and Comments.