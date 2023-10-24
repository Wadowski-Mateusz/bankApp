# Simple web application for banking system

## Technologies

### Backend 

* Java 17
* Spring boot 3.0.5
* Spring Data
* Spring security 
* Spring Web
* Hibernate
* Jakarta
* JWT
* Lombok
* Jackson

### Database
* PostgreSQL

### Frontend
* React
* Bootstrap

## Set Up

### Requirements
* Docker
* Internet connection
* Browser

### Setting up docker
```docker build -t bank-server .```

```docker build -t bank-web -f Dockerfile-web .```

```docker compose up```


## Running the app
Go to: http://localhost:5173

### Login data
Data in form: `login` : `password`
- Employees: 
    - `empl` : `empl`
- Clients (approved):
    - `JohnS` : `JohnS`
    - `DavidG` : `DavidG`
- Clients (unapproved - have to be approved by an employee to log in):
    - `SarahJ` : `SarahJ`
    - `EmilyL` : `EmilyL`

## Walkthrough

Login page:
![Alt text](docs/image.png)

Let's create a new account:

![Alt text](docs/image-5.png)

![Alt text](docs/image-6.png)

Please skip this page
![Alt text](docs/image-8.png)

![Alt text](docs/image-1.png)

And done!
![Alt text](docs/image-2.png)


Now, log in to employee account
![Alt text](docs/image-3.png)

![Alt text](docs/image-4.png)

I will move to smaller screen

![Alt text](docs/image-7.png)

Click "Verify clients"

![Alt text](docs/image-9.png)

Choose to `ban` (and delete) account, or to positively `verify` them.\
! There is a bug - id will disappear, but data will stay in the window

Go back to panel and try to `add` new `announcement` - it will be visible from login page.\
! Too long  announcements will not be added 

![Alt text](docs/image-11.png)

If you want, you can delete announcement. It will stay in the database, and will be marked with employee ID.
![Alt text](docs/image-12.png)

Log in to client account

![Alt text](docs/image-13.png)

![Alt text](docs/image-14.png)

![Alt text](docs/image-15.png)

![Alt text](docs/image-16.png)

Let's take a loan! Don't You worry, I haven't implemented interest charging.
![Alt text](docs/image-18.png)

Copy account number and login to another account.

Paste account number\
![Alt text](docs/image-20.png)

![Alt text](docs/image-21.png)


Setting, here you would be able to delete your account, but it is broken. Switch do, surprise, nothing.
![Alt text](docs/image-19.png)

## Database ERD diagram

![Alt text](docs/database_diagram.png)
