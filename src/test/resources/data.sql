INSERT INTO users (id, password, email, first_name, last_name, is_active, role_,
                   project_id, created_at, updated_at)
VALUES (2, 'password', 'mail1@mail.com', 'User1', 'User1' , true, 'ADMIN', 1, current_date, current_date),
       (3, 'password', 'mail2@mail.com', 'User2', 'User2' , true, 'EMPLOYEE', 1, current_date, current_date),
       (4, 'password', 'mail3@mail.com', 'User3', 'User3' , true, 'LEAD', 1, current_date, current_date);