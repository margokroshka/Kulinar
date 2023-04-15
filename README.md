# Culinary site
Project for TeachMeSkills

## Description:

The site offers recipes for all tastes, food habits and religious beliefs, as well as comments on the interests of those present with the recipes.

## Database.

A Postgres SQL database is connected to the project. It contains 5 tables. User - stores information about users. At the time of its creation, it had 3 users. Recipe - stores data about recipes that users are interested in. There were 3 recipes at the time of creation. Feedback - stores comments on recipes. Products - stores basic product data for use in recipes, in the recipe table of basic product consumption. Role - stores access data for resource use.

## Registration.

To register, you can log in through OAuth (Google) or go to "http://localhost:8080/registration". When collecting the first successful user in the database with ROLE_USER.
is assigned the second method, we have to pass the json format (POST method:{"id": __, "first_name": "", "last_name": "", "email": "", "phone": "", "password": "", "login": "",}) to "http://localhost:8080/registration", after which the user was created and placed in the database.
Authentication
Access to endpoints (except: "http://localhost:8080/registration", "http://localhost:8080/h2-console/", "http://localhost:8080/", "http:/ / localhost:8080/login", "http://localhost:8080/logout" ) we only use an authentication pass. After authentication the user gets the USER role.

### User functions

ROLE_USER is a user who does not use paid add-ons.

Available endpoints:
"http://localhost:8080/feedback/getAll" - View all comments.
Example: (GET method: "localhost:8080/feedback/getAll")

"http://localhost:8080/feedback/{id}" - Get a comment by ID.
Example: (GET method: "http://localhost:8080/feedback/{id}" )

"http://localhost:8080/feedback/createFeedback" - write a comment to the recipe.
Example: (POST method: "http://localhost:8080/feedback/createFeedback")

"http://localhost:8080/feedback/update/{id}" - edit an existing comment.
Example: (PUT method: "http://localhost:8080/feedback/update/{id}")

"http://localhost:8080/feedback/delete" - delete the comment.
Example: (DELETE method: "http://localhost:8080/feedback/delete" )

"http://localhost:8080/products/getAll" - display all products.
Example: (GET method: "http://localhost:8080/products/getAll" )

"http://localhost:8080/products/{id}" - display the product by ID.
Example: (GET method: "http://localhost:8080/products/23")

"http://localhost:8080/products/create" - create a product.
Example: (POST method: "localhost:8080/products/create")

"http://localhost:8080/products/update/{id}" - change the product.
Example: (method PUT: "localhost:8080/products/update/23")

"http://localhost:8080/products/delete" - delete the product.
Example: (DELETE method: "http://localhost:8080/products/delete" )


"http://localhost:8080/recipe/getAll" - display all recipes.
Example: (GET method: "http://localhost:8080/recipe/getAll" )

"http://localhost:8080/recipe/{id}" - display the recipe by ID.
Example: (GET method: "http://localhost:8080/recipe/23")

"http://localhost:8080/ recipe /create" - creating a recipe.
Example: (POST method: "localhost:8080/ recipe /create")

"http://localhost:8080/ recipe /update/{id}" - change the recipe.
Example: (PUT method: "localhost:8080/ recipe /update/23")

"http://localhost:8080/ recipe /delete" - delete the recipe.
Example: (DELETE method: "http://localhost:8080/ recipe /delete " )

### User functions

ROLE_ADMIN - A user who does not use paid add-ons.

Available endpoints:
"http://localhost:8080/user/getAll" - display all users.
Example: (GET method: "http://localhost:8080/user/getAll")

"http://localhost:8080/user/{id}" - display user by ID.
Example: (GET method: "http://localhost:8080/user/23")

"http://localhost:8080/user/createUser" - create user.
Example: (POST method: "localhost:8080/user/createUser")

"http://localhost:8080/user/update/{id}" - change the user.
Example: (PUT method: "localhost:8080/user/update/23")

"http://localhost:8080/user/delete" - delete a user.
Example: (DELETE method: "http://localhost:8080/user/delete" )
