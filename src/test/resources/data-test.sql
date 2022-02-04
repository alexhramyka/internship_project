
INSERT INTO users (id, password, email, first_name, last_name, is_active, role_, created_at, updated_at)
VALUES (2, 'password', 'mail1@mail.com', 'User1', 'User1' , true, 'ADMIN', current_date, current_date),
       (3, 'password', 'mail2@mail.com', 'User2', 'User2' , true, 'EMPLOYEE', current_date, current_date),
       (4, 'password', 'mail3@mail.com', 'User3', 'User3' , true, 'LEAD', current_date, current_date);

INSERT INTO projects(id, name, description, date_start, date_end, created_at, created_by, updated_at, updated_by)
VALUES (2, 'name2', 'description', '2022-02-01', '2022-02-01', '2022-02-01', 2, '2022-02-01', 2),
       (3, 'name3', 'description', '2022-02-01', '2022-02-01', '2022-02-01', 2, '2022-02-01', 2),
       (4, 'name4', 'description', '2022-02-01', '2022-02-01', '2022-02-01', 2, '2022-02-01', 2);

INSERT INTO employee_has_projects(employee_id, project_id)
VALUES (3, 4);