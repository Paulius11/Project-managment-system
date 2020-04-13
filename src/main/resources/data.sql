INSERT INTO PROJECT
(ID, PROJECT_NAME, PROJECT_DESCRIPTION, PROJECT_STATE)
VALUES
							  (42, 'Labai įdomus projektas', 'Labai įdomus apibūdinimas', true),
((SELECT MAX(ID)+1  FROM PROJECT), 'Projektas2', 'Apibudinimas2', true),
((SELECT MAX(ID)+1  FROM PROJECT), 'Projektas3', 'Apibudinimas3', true),
((SELECT MAX(ID)+1  FROM PROJECT), 'Programavimo kalbos', 'Kalbos kurias mokysiuosi', true)
;

INSERT INTO TASK (ID, PROJECT_ID, TASK_NAME, TASK_DESCRIPTION, PROJECT_PRIORITY, TASK_CREATE_TIME, TASK_MODIFY_TIME, TASK_STATE) 
VALUES 
				            (1, 42, 'Backend', 'Sukurti backendą', 1, sysdate(), sysdate(), 1 ),
((SELECT MAX(ID)+1  FROM TASK), 42, 'FrontEnd', 'Sukurti frontendą', 1, sysdate(), sysdate(), 1 ),
((SELECT MAX(ID)+1  FROM TASK), 42, 'React', 'Išmokti React', 1, sysdate(), sysdate(), 1 ),
((SELECT MAX(ID)+1  FROM TASK), 44, 'Java', 'Išmokti Java', 1, sysdate(), sysdate(), 1 )
;

