# Java_Jwt

Projeto criado para aprendizado de autenticação Spring Security com JWT.
Utilizei o Docker para criar dois servidores para aplicação o app-01 e app-02, na qual se conectam ao banco de dados postgresSQL(foi dependência flywaydb para as migrations). 
O Nginx faz o loadbalance entre as aplicações 01 e 02.


## Ferramentas e Tecnologias utilizadas

 - JDK 11
 - Spring boot, Security
 - Maven
 - PostgreSQL
 - Visual Studio Code
 - Docker
 - Nginx
 
 ## Features

 1 - Clone do projeto https://github.com/AlissonFerreiraEvangelista/Java_Jwt.git
 <br>
 2 - Abrir o terminal e dar cd no local do projeto <br>
 3 - docker compose build <br>
 4 - docker compose up <br>
 
# Endpoints

http://localhost:80/swagger-ui.html
