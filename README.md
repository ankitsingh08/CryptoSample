# Bitcoin sample to display bitcoin prices, no of transactions and other bitcoin related information with a graph to show the changes over time using block chain api's.

Features Implemented
- Display Bitcoin stats like Market price. Block Size, Number of transactions etc. 
- Display graph for prices, block size and transaction changes over time. 

# Unit Test Cases
Unit test cases are included for the following:
- ViewModel 
- UseCases 
- Repository

# Project Characteristics 
- 100% Kotlin<br />
- Architecture (Clean Architecture, MVVM)<br />
- Dependency Injection(Using HILT)<br />
- Coroutines<br />
- Kotlin Flow<br />
- Architecture Components(LiveData, ViewModel)<br />
- GSON for parsing<br />
- Navigation<br />
- Unit Testing(Junit, Mockito)<br />

# App Architecture Details:
![Untitled Diagram (1)](https://user-images.githubusercontent.com/16702310/103493822-8fd58100-4e01-11eb-8465-a443c394e83f.png)

 # View
 This layer mainly deals with the UI of the application and has its own presentation models.
 
 Components
 - **Activity/Fragment**: Presents data on the screen and pass user interaction to viewmodel
 - **ViewModel**: Executes use cases based on user interaction and updates ui using LiveData
 
 # Domain
 Contains all the business logic for the application.Domain layers is independent of other layers, has its own models, so that changes in other layers will have no effect on domain layer.
 
 Components
 - **UseCase** : Handles business logic
 - **Domain Models**: Represents structure of response data
 - **Repository Interface**: To keep domain layer independent from data layer.
 
 # Data
 Manages application data and exposes data to domain layer
 
 components
 - **Repository** : Gets the requested data using api service ans exposes data to domain layer. 
 - **Mapper** : Used to map data models to domains models to keep domain independent of data layer.
 - **DataModel** : Structure for data retrieved from remote data source. 

# Screenshots
 <img src="https://user-images.githubusercontent.com/16702310/121984122-6df10a80-cd60-11eb-8d44-17d5dbe16b6d.png" width="30%">  <img src="https://user-images.githubusercontent.com/16702310/121984126-71849180-cd60-11eb-9bf6-d3a582860eb3.png" width="30%"> <img src="https://user-images.githubusercontent.com/16702310/121984134-747f8200-cd60-11eb-8513-583a4bed493e.png" width="30%"> <img src="https://user-images.githubusercontent.com/16702310/121984141-76e1dc00-cd60-11eb-843f-d2001691123c.png" width="30%"> <img src="https://user-images.githubusercontent.com/16702310/121984145-79443600-cd60-11eb-8404-0b3d6890e1c3.png" width="30%">
 
 
 
