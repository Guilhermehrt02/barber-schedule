# barber schedule
## Descrição do Projeto
- config/: Contém classes de configuração do Spring, como configurações de segurança, CORS, ou outras configurações globais do aplicativo.
  - Exemplo: WebConfig.java, SecurityConfig.java

- controller/: Contém os controladores da aplicação, que lidam com as requisições HTTP e direcionam para os serviços apropriados. Inclui controladores REST e, se aplicável, controladores MVC.
  - Exemplo: AgendamentoController.java, ClienteController.java

- dto/: Contém Data Transfer Objects, que são classes utilizadas para transferir dados entre as camadas da aplicação. Essas classes são usadas para evitar expor diretamente as entidades do banco de dados.
  - Exemplo: AgendamentoDTO.java, ClienteDTO.java

- exception/: Contém classes para lidar com exceções customizadas e tratamento global de erros.
  - Exemplo: GlobalExceptionHandler.java, ResourceNotFoundException.java

- model/: Contém as classes de modelo, que representam as entidades do banco de dados.
  - Exemplo: Agendamento.java, Cliente.java

- repository/: Contém interfaces que estendem JpaRepository ou CrudRepository para manipulação das entidades no banco de dados.
  - Exemplo: AgendamentoRepository.java, ClienteRepository.java

- security/: Contém classes relacionadas à segurança da aplicação, como serviços de autenticação e autorização.
  - Exemplo: JwtTokenProvider.java, CustomUserDetailsService.java

- service/: Contém as classes de serviço, onde fica a lógica de negócio. Essas classes interagem com os repositórios e são chamadas pelos controladores.
  - Exemplo: AgendamentoService.java, ClienteService.java

- util/: Contém classes utilitárias que são usadas em várias partes da aplicação, como conversores, validadores, ou helpers.
  - Exemplo: DateUtil.java, EmailUtil.java

## Outras Pastas e Arquivos Importantes
- resources/static/: Contém arquivos estáticos como CSS, JavaScript, e imagens, se estiver desenvolvendo uma interface web.

- resources/templates/: Contém templates HTML ou arquivos Thymeleaf, caso esteja desenvolvendo uma aplicação Spring MVC.

- application.properties ou application.yml: Arquivo de configuração principal do Spring Boot, onde você define configurações de banco de dados, portas, etc.

- test/java/: Contém as classes de teste unitário e de integração. A estrutura de pacotes aqui deve espelhar a estrutura do código principal, facilitando a organização dos testes.
  - Exemplo: AgendamentoServiceTest.java, ClienteControllerTest.java
