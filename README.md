# Culinary site
Project for TeachMeSkills

## Description:

The site allows you to search for a recipe for every taste, taking into account food habits and religious beliefs, as well as leave comments on the interests of those present with recipes.

## Database

The MySQL database is connected to the project. It contains 5 tables. User - stores information about users. At the time of creation, it had 3 users. Recipe - stores data about recipes that are of interest to users. At the time of creation, there are 3 recipes. Feedback - stores comments on recipes. Foods - Stores data on main products for use in recipes, in the main product consumption recipes table. Role - stores access data for resource usage.

## Registration

To register, you can either login via OAuth (Google) or follow the link "http://localhost:8080/registration". When collecting the first successful user in the DB with ROLE_USER.
second method is assigned, we have to pass json format (method POST:{"id": __, "first_name": "", "last_name": "", "email": "", "phone": "", "password ": "", "login": "",}) to "http://localhost:8080/registration", after which the user was created and placed in the database.
Authentication
Access to endpoints (except: "http://localhost:8080/registration", "http://localhost:8080/h2-console/", "http://localhost:8080/", "http:/ / localhost:8080/login", "http://localhost:8080/logout" ) we use only the authentication pass.After passing the authentication, the user gets the USER role.

### User Features

ROLE_USER - a user who does not use paid add-ons.

Available endpoints:
"http://localhost:8080/feedback/getAll" - View all comments.
Example: (GET method: "localhost:8080/feedback/getAll")

"http://localhost:8080/feedback/{id}" — get comment by ID
Example: (GET method: "http://localhost:8080/feedback/{id}" )

"http://localhost:8080/feedback/createFeedback" – write a comment to the recipe.
Example: (POST method: "http://localhost:8080/feedback/createFeedback")

"http://localhost:8080/feedback/update/{id}" - edit Existing comment
Example: (PUT method: "http://localhost:8080/feedback/update/{id}"")

"http://localhost:8080/feedback/delete" — delete a comment.
Example: (DELETE method: "http://localhost:8080/feedback/delete" )

"http://localhost:8080/products/getAll" - displays all products.
Example: (GET method: "http://localhost:8080/products/getAll" )

"http://localhost:8080/products/{id}" - product display by ID.
Example: (GET method: "http://localhost:8080/products/23")

"http://localhost:8080/products/create" - create a product.
Example: (POST method: "localhost:8080/products/create")

"http://localhost:8080/products/update/{id}" - change the product.
Example:(PUT method: "localhost:8080/products/update/23")

"http://localhost:8080/products/delete" - delete a product.
Example: (DELETE method: "http://localhost:8080/products/delete" )


"http://localhost:8080/recipe/getAll" - displays all recipes.
Example: (GET method: "http://localhost:8080/recipe/getAll" )

"http://localhost:8080/recipe/{id}" — output of the recipe by ID.
Example: (GET method: "http://localhost:8080/recipe/23")

"http://localhost:8080/ recipe /create" – create a recipe.
Example: (POST method: "localhost:8080/ recipe /create")

"http://localhost:8080/ recipe /update/{id}" – change recipe.
Example:(PUT method: "localhost:8080/ recipe /update/23")

"http://localhost:8080/ recipe /delete" - delete a recipe.
Example: (DELETE method: "http://localhost:8080/ recipe /delete " )

### User Features

ROLE_ADMIN - a user who does not use paid add-ons.

Available endpoints:
"http://localhost:8080/user/getAll" - display all users.
Example: (GET method: "http://localhost:8080/user/getAll")

"http://localhost:8080/user/{id}" - display user by ID.
Example: (GET method: "http://localhost:8080/user/23")

"http://localhost:8080/user/createUser" — create a user.
Example: (POST method: "localhost:8080/user/createUser")

"http://localhost:8080/user/update/{id}" — change the user.
Example:(PUT method: "localhost:8080/user/update/23")

"http://localhost:8080/user/delete" - delete a user.
Example: (DELETE method: "http://localhost:8080/user/delete" )

