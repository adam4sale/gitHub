# Expense Reimbursement System (ERSPP)

## Overview
- The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while 
on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets 
and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in 
the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

## Tools and Technologies
- Javalin
- Gradle
- MongoDB
- Git 
- Log4J
- JUnit

## Features
- Users can login
- Managers and Employees are distinguished by an arbitrary rank
- Employees can post reimbursment requests and view their old requests
- Managers can view all reimbursment requests from all employees
- Managers can accept or deny requests
- Employees can check to see if their requests were approved by a manager
- Employees can update their passwords 

## Getting Started

To get this application setup:
1. Must have Java 8 runtime environment installed.

If the requirements are met, go ahead and clone the repo by using the command below:
```https://github.com/adam4sale/gitHub/tree/main/Project1/Project1```

Once the repository is cloned you will need to make sure the localhost:8080 and mongodb is running.
The backend is running at port 8080.


## To run the backend
```gradle clean```
```gralde build```
Run the main method in Project1Application

## Contributors
- Adam Bosch
