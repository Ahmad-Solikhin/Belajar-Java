# User API Spec

## Register User
Endpoint : POST /api/users

Request Body :

```json
{
  "username" : "",
  "password" : "",
  "name" : ""
}
```

Response Body (Success) :
```json
{
  "data" : "Oke"
}
```

Response Body (Failed) :
```json
{
  "errors" : "[]"
}
```

## Login User

Endpoint : POST /api/auth/login

Request Body :

```json
{
  "username" : "",
  "password" : ""
}
```

Response Body (Success) :
```json
{
  "data" : {
    "token" : "TOKEN",
    "expire_at" : 7070707021 // miliseconds
  }
}
```

Response Body (Failed, 401) :
```json
{
  "errors" : "username or password wrong"
}
```

## Get User

Endpoint : GET /api/users/current

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data" : {
    "username" : "",
    "name" : ""
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "Unauthorized"
}
```

## Update User
Endpoint : PATCH /api/users/current

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "password" : "",
  "name" : ""
}
```

Response Body (Success) :
```json
{
  "data" : {
    "username" : "",
    "name" : ""
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "Unauthorized"
}
```

## Logout User

Endpoint : DELETE /api/auth/logout

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data" : "Ok"
}
```