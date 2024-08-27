USE barberschedule;

-- Inserção de dados na tabela service
insert into service (name, price, duration) values 
('Corte de Cabelo', 30.00, 30),
('Barba', 15.00, 15),
('Corte e Barba', 40.00, 45);

-- Inserção de dados na tabela barber
insert into barber (name, email, phone, password) values 
('João Silva', 'joao.silva@example.com', '123456789', 'password123'),
('Maria Oliveira', 'maria.oliveira@example.com', '987654321', 'password456');

-- Inserção de dados na tabela client
insert into client (name, email, phone, password) values 
('Pedro Costa', 'pedro.costa@example.com', '555123456', 'password789'),
('Ana Souza', 'ana.souza@example.com', '555987654', 'password012');

-- Inserção de dados na tabela appointment
insert into appointment (date, time, service_id, client_id, barber_id) values 
('2024-08-25', '10:00:00', 1, 1, 1), -- Corte de Cabelo com João Silva para Pedro Costa
('2024-08-26', '14:00:00', 2, 2, 2), -- Barba com Maria Oliveira para Ana Souza
('2024-08-27', '16:00:00', 3, 1, 2); -- Corte e Barba com Maria Oliveira para Pedro Costa

-- Inserção de dados na tabela barber_service
insert into barber_service (barber_id, service_id) values 
(1, 1), -- João Silva oferece Corte de Cabelo
(1, 3), -- João Silva oferece Corte e Barba
(2, 2), -- Maria Oliveira oferece Barba
(2, 3); -- Maria Oliveira oferece Corte e Barba
