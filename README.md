# Project-managment-system
Projektų užduočių valdymo programa

### Svetaine prieinama
http://localhost:9090/api/project

###  Kaip pasipulint info
https://gist.github.com/CristinaSolana/1885435


 Būtinas padaryti dalykas pirma karta pasicloninus repozitoriją frontend aplanke iškviesti konsolę ir įrašyti komandą "npm install"

### Mappingas

#### Projects
Gauti visus projektus   - GET /projects  
Gauti konkretu projekta - GET /projects/{id} 
Sukurti projekta 		- POST 	/projects 
Redaguoti projektą 	    - PUT	/projects/{id}   

#### Tasks
Gauti visus taskus - GET /projects/{id}/tasks   
Gauti kontretu taską - GET /project/{id}/tasks/{taskID}  
Sukurti taską  - POST /projects/{id}/tasks/  
Ištrinti taską - DELETE /projects/{id}/tasks/{taskID}  
Pakeisti taską - PUT  /projects/{id}/tasks/{taskID}
