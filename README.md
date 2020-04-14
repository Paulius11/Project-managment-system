# Project-managment-system
Projektų užduočių valdymo programa

### Svetaine prieinama
http://localhost:9090/api/project

###  Kaip pasipulint info
https://gist.github.com/CristinaSolana/1885435


### Mappingas

#### Projects
Gauti visus projektus   - GET /project  
Gauti konkretu projekta - GET /project/{id}  
Sukurti projekta 		- POST 	/project  
Redaguoti projektą 	    - PUT	/project/{id}   

#### Tasks
Gauti visus taskus - GET /project/{id}/task  
Gauti kontretu taską - GET /project/{id}/task/{taskID}  
Sukurti taską  - POST /project/{id}/task/  
Ištrinti taską - DELETE /project/{id}/task/{taskID}  
Pakeisti taską - PUT  /project/{id}/task/{taskID}
