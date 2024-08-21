# barber schedule

## Descrição do Projeto
O Sistema de Agendamento para Salão de Beleza é uma aplicação desenvolvida para facilitar o gerenciamento de serviços em um salão de beleza, permitindo que clientes agendem serviços como barbearia de forma eficiente e organizada. A aplicação é projetada para ser usada tanto por clientes quanto pelos administradores do salão, oferecendo funcionalidades de agendamento, gerenciamento de horários disponíveis, e envio de notificações de lembrete aos clientes.

## Funcionalidades:

- Agendamento de Serviços: Clientes podem agendar serviços de barbearia e outros serviços oferecidos pelo salão, escolhendo horários disponíveis diretamente no sistema.
- Gerenciamento de Horários: O sistema permite o gerenciamento de horários disponíveis, evitando conflitos e garantindo que os profissionais estejam disponíveis para os serviços agendados.
- Notificações de Lembrete: O sistema envia notificações de lembrete aos clientes antes do horário agendado, reduzindo o número de faltas e cancelamentos de última hora.
- Administração de Serviços: Possibilidade de adicionar, editar e remover serviços, além de gerenciar a lista de profissionais disponíveis.

## Arquitetura e Tecnologias:

- Arquitetura: A aplicação é construída sobre uma arquitetura RESTful, facilitando a comunicação entre cliente e servidor e permitindo a integração com diferentes plataformas.
- Backend: O backend é desenvolvido em Spring Boot, utilizando Spring MVC para controle de requisições e Spring Data JPA para persistência de dados.
- Banco de Dados: O sistema utiliza um banco de dados relacional (MySQL) para armazenar informações sobre clientes, serviços, agendamentos e horários.
- Segurança: Implementação de Spring Security para autenticação e autorização, garantindo que apenas usuários autorizados tenham acesso a determinadas funcionalidades.

## Componentes:

- Controllers: Responsáveis por expor os endpoints REST, permitindo operações como criação, leitura, atualização e exclusão de agendamentos e serviços.
- Services: Contêm a lógica de negócios, como validação de dados, cálculos de horários disponíveis, e interação com o banco de dados.
- Repositórios: Interfaces que gerenciam a persistência dos dados, utilizando Spring Data JPA.
- Entidades: Representações dos objetos de domínio, como Client, Service, Barber, e Appointment.

## Testes:

- Testes de Integração: Testes de integração para garantir que todos os componentes do sistema funcionem corretamente juntos.
- Testes Unitários: Testes unitários desenvolvidos para garantir a funcionalidade de métodos específicos, especialmente na camada de serviços.
