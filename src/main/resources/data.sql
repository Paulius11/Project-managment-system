
INSERT INTO PROJECT
(ID, PROJECT_NAME, PROJECT_DESCRIPTION, PROJECT_STATUS)
VALUES
							  (42, 'Labai įdomus projektas', 'Labai įdomus apibūdinimas', 'COMPLETED'),
((SELECT MAX(ID)+1  FROM PROJECT), 'Projektas2', 'Apibudinimas2', 'COMPLETED'),
((SELECT MAX(ID)+1  FROM PROJECT), 'Projektas3', 'Apibudinimas3', 'ACTIVE'),
((SELECT MAX(ID)+1  FROM PROJECT), 'Projektas3', 'Apibudinimas3', 'ACTIVE'),
((SELECT MAX(ID)+1  FROM PROJECT), 'Projektas3', 'Apibudinimas3', 'ACTIVE'),
((SELECT MAX(ID)+1  FROM PROJECT), 'Projektas3', 'Apibudinimas3', 'ACTIVE'),
((SELECT MAX(ID)+1  FROM PROJECT), 'Projektas3', 'Apibudinimas3', 'ACTIVE'),
((SELECT MAX(ID)+1  FROM PROJECT), 'Projektas3', 'Apibudinimas3', 'ACTIVE'),
((SELECT MAX(ID)+1  FROM PROJECT), 'Projektas3', 'Apibudinimas3', 'ACTIVE'),
((SELECT MAX(ID)+1  FROM PROJECT), 'Projektas3', 'Apibudinimas3', 'ACTIVE'),
((SELECT MAX(ID)+1  FROM PROJECT), 'Projektas3', 'Apibudinimas3', 'ACTIVE'),
((SELECT MAX(ID)+1  FROM PROJECT), 'Projektas3', 'Apibudinimas3', 'ACTIVE'),
((SELECT MAX(ID)+1  FROM PROJECT), 'Programavimo kalbos', 'Kalbos kurias mokysiuosi', 'ACTIVE')
;

INSERT INTO TASK (ID, PROJECT_ID, TASK_NAME, TASK_DESCRIPTION, TASK_PRIORITY, TASK_CREATE_TIME, TASK_MODIFY_TIME, TASK_STATE) 
VALUES 
				            (1, 42, 'Backend', 'Sukurti backendą', 'NORMAL', current_timestamp(), current_timestamp(), 'IN_PROGRESS' ),
((SELECT MAX(ID)+1  FROM TASK), 42, 'FrontEnd', 'Sukurti frontendą', 'LOW', current_timestamp(), current_timestamp(), 'TO_DO' ),
((SELECT MAX(ID)+1  FROM TASK), 42, 'React', 'Išmokti React', 'NORMAL', current_timestamp(), current_timestamp(), 'TO_DO' ),
((SELECT MAX(ID)+1  FROM TASK), 44, 'Java', 'Išmokti Java', 'HIGH', current_timestamp(), current_timestamp(), 'DONE' )
;


