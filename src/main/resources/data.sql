INSERT INTO PROJECT
(ID, PROJECT_NAME, PROJECT_DESCRIPTION, PROJECT_STATE)
VALUES
							  (42, 'Labai įdomus projektas', 'Labai įdomus apibūdinimas', true),
((SELECT MAX(ID)+1  FROM PROJECT), 'Projektas2', 'Apibudinimas2', true),
((SELECT MAX(ID)+1  FROM PROJECT), 'Projektas3', 'Apibudinimas3', true),
((SELECT MAX(ID)+1  FROM PROJECT), 'Programavimo kalbos', 'Kalbos kurias mokysiuosi', true)
;

INSERT INTO TASK (ID, PROJECT_ID, TASK_NAME, TASK_DESCRIPTION, TASK_PRIORITY, TASK_CREATE_TIME, TASK_MODIFY_TIME, TASK_STATE) 
VALUES 
				            (1, 42, 'Backend', 'Sukurti backendą', 'NORMAL', sysdate(), sysdate(), 'IN_PROGRESS' ),
((SELECT MAX(ID)+1  FROM TASK), 42, 'FrontEnd', 'Sukurti frontendą', 'LOW', sysdate(), sysdate(), 'TO_DO' ),
((SELECT MAX(ID)+1  FROM TASK), 42, 'React', 'Išmokti React', 'NORMAL', sysdate(), sysdate(), 'TO_DO' ),
((SELECT MAX(ID)+1  FROM TASK), 44, 'Java', 'Išmokti Java', 'HIGH', sysdate(), sysdate(), 'DONE' )
;

