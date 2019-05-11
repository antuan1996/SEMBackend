
### /auth/signin
User authentication
Request:
~~~
POST /auth/signin
{
    "email": "test@gmail.com",
    "password": "secret
}
~~~
Response:
~~~
{
    "username": "test@gmail.com",
    "token": "token"
}
~~~
Use token value in `Authorization` header for other requests.
Header example: 
~~~
Authorization: Bearer token
~~~

### /auth/signup
Creates new user with default `customer` role.
Request:
~~~
POST /auth/signup
{
	"firstName":"first",
	"lastName": "last",
	"email": "test@gmail.com",
	"password": "password"
}
~~~
Response:
~~~
{
    "username": "test@gmail.com",
    "token": "token"
}
~~~

### /requests
CRUD for request entities
1. Create request

Required role: `customer`
~~~
POST /requests
{
    "title": "title",
    "description": "description",
    "contactEmail": "email@gmail.com"
}
~~~
2. List Requests

Required role: `admin` or `manager`
GET /requests
Response Example:
~~~
[
    {
        "id": 1,
        "title": "title",
        "description": "description",
        "expectedDueDate": null,
        "contactEmail": "email@gmail.com"
    }
]
~~~

### /orders

CRUD for order entities

1. Create order

Required role: `admin` or `manager`
~~~
POST /orders
{
    "title": "title",
    "description": "description",
    "status": 0
}
~~~

Order status format:
~~~
0 - CONFIRMED
1 - INPROGRESS
2 - DONE
~~~

2. List orders

Required role: `admin` or `manager`
GET /orders
Response Example:
~~~
[
    {
        "id": 2,
        "title": "title",
        "description": "description",
        "createdDatetime": "2019-05-03T13:12:01Z",
        "status": "CONFIRMED"
    }
]
~~~

3. Update order
~~~
PUT /orders/{id}
{
    "title": "title",
    "description": "description",
    "status": 1
}
~~~

### /tasks
CRUD for task entities

1. Create task

Required role: `admin` or `manager`
~~~
POST /tasks
{
    "title": "title",
    "description": "description",
    "taskStatus": 0,
    "assignedPersonId": 1,
    "orderId": 1
}
~~~

Task status format:
~~~
 0 - TODO
 1 - INPROGRESS
 2 - CHECKING
 3 - DONE
~~~

2. List tasks

Required role: `admin` or `manager` or `developer`
GET /tasks
Response Example:
~~~
[
    {
        "id": 1,
        "title": "title",
        "description": "description",
        "createdDate": "2019-05-03",
        "taskStatus": "TODO",
        "assignedPersonId": 1,
        "creatorId": null,
        "commentSet": null,
        "orderId": 1
    }
]
~~~

3. Update task

Required role: `admin` or `manager` or `developer`
~~~
PUT /tasks/{id}
{
    "title": "title",
    "description": "description",
    "taskStatus": 0,
    "assignedPersonId": 1,
    "orderId": 1
}
~~~

### /comments
1. Create comment

Required role: `admin` or `manager` or `developer`
~~~
POST /comments
{
    "text": "comment",
    "taskId": 1
}
~~~

### /users
1. List users

Required role: `admin` or `manager`
GET /users/
Response Example:
~~~
[
    {
        "id": 1,
        "firstName": null,
        "lastName": null,   
        "email": "admin@gmail.com",
        "enabled": true,
        "roleId": "admin",
    }
]
~~~