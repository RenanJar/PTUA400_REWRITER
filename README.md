# PTU A400 REEWRITTER

## 📌 Descrição do Projeto
O **PTU A400 REEWRITTER** é um sistema desenvolvido para facilitar a manipulação de arquivos A400. 
Ele permite que o usuário envie um arquivo desconfigurado e reescreve esse arquivo de forma organizada seguindo algumas regras exigidas no manual **PTU16.2**.

### 📜 Regras de Reescrita:
- Reescreve o arquivo para a data atual.
- Reescreve toda a numeração contida no arquivo (`NR_SEQ`).
- Refaz toda a contagem dos campos (`R402`, `R403`, `R404`, `R405`, `R406`, `R407`, `R408`, `R410`), que são exigidos no campo `R499`.
- Refaz o hash de integridade do arquivo.
- Remove a linha `R999`.

O objetivo do projeto é permitir que o usuário consiga editar o conteúdo dentro do arquivo de forma que seja eficaz copiar e colar (`CTRL-C CTRL-V`) qualquer campo de outro arquivo e colar no arquivo atual para envio ao sitema.

## 🚀 Tecnologias Utilizadas
O projeto foi desenvolvido utilizando as seguintes tecnologias:

- **Java 21**
- **Swing** *(para interface gráfica)*

## 📦 Como Rodar o Projeto
### 🖥️ Requisitos
Antes de iniciar, certifique-se de ter os seguintes itens instalados:

- [Java 21+](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

### 🚀 Executando o Projeto
1. **Clone o repositório:**
   ```sh
   git clone https://github.com/RenanJar/PTUA400_REWRITER.git
   cd PTUA400_REWRITER
   ```
2. **Compile o projeto:**
   ```sh
   mvn clean install
   ```
3. **Execute a aplicação:**
   ```sh
   java -cp target/PTU_A400_REEWRITER-1.0.jar com.ptuwriter.App
   ```

