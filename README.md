
# Package Structure
    
    com.hyprful.firstmvvm    # Root Package
    .
    ├── api                # For Server handling.
    │   ├── model           # Response Pojo Objects
    │   └── repository      # Single source of server data.
    |
    ├── background               # For background services
    |
    |── db                  # For Local database related classes
    |   ├── dao             # Data Access Object for Room   
    |   ├── entity          # Model classes Access Object for Room   
    |
    ├── di                  # Dependency Injection             
    │   ├── component       # DI Components       
    │   └── module          # DI Modules
    │   └── scope           # DI scope
    |
    ├── view                  # Activity/View layer
    │   ├── activity          # Main Screen Activity
    │   ├── adapter           # Adapters
    │   ├── fragment          # Fragments view
    │   ├── viewbinding       # Custom Viewbinding classes
    │   └── callbacks         # callbacks
    |
    └── ViewModel             # ViewModel classes


## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

