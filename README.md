# Minha solução para o Projeto de Pedidos

Projeto desenvolvido para o processo seletivo da Contabilizei

## Tecnologias utilizadas

- Back-End 
	* Java EE
	* Jersey Framework
	* Tomcat 8
	* MySQL

-Front-End
	* HTML5
	* Bootstrap ~3.3.7
	* JavaScript
	* AngularJS ~1.5.8

## Estruturas de Pastas
- O projeto Back-End está localizado na pasta servico_rest
- A Single Page Application está localizada na pasta app_pedidoscontabilizei

## Como executar o projeto 

Foi desenvolvido um Maven Project no Eclipse utilizando Java 8 e como Server Runtime o Tomcat 8, 
assim que você importar o projeto para a IDE, o adicione ao Tomcat e execute o comando Maven Update Project 
para atualização das dependêcias.

Crie uma banco de dados no MySQL com o nome "pedidoscontabilizei" e altere os dados de acesso ao MySQL no arquivo
META-INF/persistence.xml dentro da pasta resources do projeto.

Certifique que o Tomcat esteja sendo executado na porta padrão 8080, pois todos as requisições da Single Page 
estão sendo executadas nessa porta.

No desenvolvimento da Single Page Application, foi utilizado o NPM como gerenciador de pacotes após clonar o 
projeto execute ```npm install [opcional --save]``` para baixar suas dependências.

Para evitar problemas com Access-Control-Allow-Origin dos Browsers, deve ser executado o projeto em cima de um
servidor HTTP local, pode ser o do node http-server que é instalado através ```npm install http-server -g``` ou o 
do Python caso tenha instalado na máquina. 

Exemplos de como executar a SPA, dentro da pasta app_pedidoscontabilizei execute:

- Utilizando Python: ```python -m http.server 8888```
- Utilizando node: ```http-server -p 8888```