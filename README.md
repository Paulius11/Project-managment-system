# Project-managment-system
Projektų užduočių valdymo programa

ok viktoras

Labas

#Viliaus commit

#Andzejaus commitas

#Albert commit
#123123312

#Kaip pasipulint info
https://gist.github.com/CristinaSolana/1885435

#Mapping

Gauti visus projektus   - GET /projects  
Gauti konkretu projekta - GET /projects/{id}  
Sukurti projekta 		- POST 	/projects  
Redaguoti projektą 	    - PUT	/projects/{id}   


Gauti visus taskus - GET /projects/{id}/tasks  
Gauti kontretu taską - GET /projects/{id}/tasks/{taskID}  
Sukurti taską  - POST /projects/{id}/tasks/  
Ištrinti taską - DELETE /projects/{id}/tasks/{taskID}  
Pakeisti taską - PUT  /projects/{id}/tasks/{taskID}


Projektas(one) -> Tasks(Many)
