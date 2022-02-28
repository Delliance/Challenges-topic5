Challenges topic 5

Still in process, right now only one Entity has been created.

You can use the application either with the Test or with the REST controllers

Here is a list of the available controllers:

All of them use the following url: 

http://localhost:8080

**GET:**\
Get the list of all students:       /studentApp/v1/student\
Get students by name:               /studentApp/v1/student/search/{firstName}\
Get students by name containing:    /studentApp/v1/student/search/contain/{firstName}
Get students by guardian name:      /studentApp/v1/student/search/guardianName/{name}
Get student by name and lastname:   /search/singleStudent/{firstName}&{lastName}


**POST:**\
post a new student with format Json, e.g.\
{
"firstName": "Daniel",
"lastName": "Gon",
"emailId" : "dan1@gmail.com",
"guardian": {
"name": "Who",
"email":"who@gmail.com",
"mobile":"123456789"
}
}

**DEL:**\
Delete a student by ID:         /studentApp/v1/student/{studentId}

**PUT:**\
Edit student: studentApp/v1/student/{studentId}?firstName={firstName}&lastName={lastName}&email={email}\
Note: You can edit either firstName, lastName, or Email. not necessary at the same time