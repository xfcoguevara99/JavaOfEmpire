# ️ Java of Empires - Projeto POO 2

Este projeto é a implementação do "Projeto 2: Java of Empires" para a disciplina de Programação Orientada a Objetos, focado na aplicação de conceitos de Herança, Polimorfismo, Interfaces e Padrões de Design em um contexto de Jogo de Estratégia em Tempo Real (RTS).

---

## 1. Execução do Projeto

O projeto utiliza o Gradle como sistema de build.

### Pré-requisitos
* Java Development Kit (JDK) 21 ou superior.

### Como Compilar e Executar
1.  **Clone o Repositório:** `git clone https://github.com/xfcoguevara99/JavaOfEmpire.git/`
2.  **Abra o terminal na pasta do projeto**
3.  **Execute via Gradle:**
    ```bash
    ./gradlew run
    ```
O comando executará a classe principal (`App.java`) e iniciará a interface gráfica.

---

## 2. Funcionalidades Implementadas (34/76 Pontos)

O projeto atinge a pontuação mínima obrigatória de **32 pontos** e inclui todos os requisitos base, focando em Arquitetura, Controles e Sistema Básico de Coleta.

### 2.1 Requisitos Obrigatórios
Os requisitos obrigatórios foram integralmente cumpridos

* **Classes de Personagem:** Implementação das classes `Arqueiro` e `Cavaleiro`, herdando de `Personagem`.
* **Polimorfismo:** Uso de uma coleção genérica de `Personagem` para movimentação e ataque.
* **Interfaces:** Implementação das interfaces `Guerreiro` (para Arqueiro e Cavaleiro), `Coletador` (para Aldeão) e `ComMontaria` (para Cavaleiro e Aldeão).

### 2.2 Galhos da Árvore de Requisitos
| Funcionalidade | Pontos | Descrição                                                                                                                                                                                                     | Status |
| :--- | :--- |:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------| :--- |
| **Filtro por Tipo** | 4 | Implementação de Radio Buttons para filtrar qual tipo de personagem será afetado pelos comandos de movimento, montaria e ataque.                                                                              | **Desbloqueado** |
| **Controle de Montaria** | 5 | Adição do botão "Montar/Desmontar" para alternar o estado `montado`/`desmontado` nos personagens que implementam `ComMontaria` (Aldeão e Cavaleiro).                                                          | **Desbloqueado** |
| **Atalhos de Teclado** | 6 | Controles avançados ativados: Teclas de Seta para movimento, teclas numéricas (1, 2, 3) para criação e a tecla **M** para Montar/Desmontar. Foi corrigido o problema de consumo da tecla **TAB** para o jogo. | **Desbloqueado** |
| **Sistema de Coleta** | 4 | Implementação de uma mecânica de coleta de recursos.                                                                                                                                                          | **Desbloqueado** |
| **Arquivo de Configurações** | 3 | Centralização de valores constantes em uma classe `Constantes` para fácil manutenção.                                                                                                                         | **Desbloqueado** |
| **Fábrica de Personagens** | 6 | Criação do padrão Factory Method para instanciar personagens de forma desacoplada.                                                                                                                            | **Desbloqueado** |
| **Cache de Recursos** | 6 | Otimização do carregamento de imagens de personagem usando a classe `ImageCache`.                                                                                                                             | **Desbloqueado** |
| **TOTAL** | **34 Pontos** |                                                                                                                                                                                                               | |

### 2.3 Detalhes dos Controles de Teclado

A tabela abaixo detalha todos os atalhos implementados no jogo:

| Ação | Tecla | Comando Chamado |
| :--- | :--- | :--- |
| **Movimento CIMA** | $\text{W}$ | `moverPersonagens(..., Direcao.CIMA)` |
| **Movimento BAIXO** | $\text{S}$ | `moverPersonagens(..., Direcao.BAIXO)` |
| **Movimento ESQUERDA** | $\text{A}$ | `moverPersonagens(..., Direcao.ESQUERDA)` |
| **Movimento DIREITA** | $\text{D}$ | `moverPersonagens(..., Direcao.DIREITA)` |
| **Cria Aldeão** | $\text{1}$ | `chamarCriarAldeao()` |
| **Cria Arqueiro** | $\text{2}$ | `chamarCriarArqueiro()` |
| **Cria Cavaleiro** | $\text{3}$ | `chamarCriarCavaleiro()` |
| **Ataque** | $\text{ESPAÇO}$ | `atacarGuerreiros(...)` |
| **Coletar Recursos** | $\text{R}$ | `chamarColetarRecursos()` |
| **Montar/Desmontar** | $\text{M}$ | `montarNoCavalo(...)` |
| **Alternar Filtro** | $\text{TAB}$ | Ciclagem entre Aldeão, Arqueiro, Cavaleiro e Todos. |

---

## 3. Decisões de Design Importantes

O projeto foi construído sobre princípios de POO e padrões de projeto para garantir uma arquitetura escalável e manutenível.

### A. Polimorfismo e Hierarquia de Classes

* **Hierarquia Central:** A classe abstrata `Personagem` (localizada em `core`) serve como a base, contendo o estado comum (vida, coordenadas X/Y) e métodos como `mover()`.
* **Interfaces de Capacidade:** As interfaces (`Guerreiro`, `Coletador`, `ComMontaria`) foram usadas para implementar herança múltipla de *tipo*, definindo capacidades que as classes concretas (`Aldeao`, `Arqueiro`, `Cavaleiro`) implementam seletivamente. Por exemplo, o `Cavaleiro` implementa `Guerreiro` e `ComMontaria`.

### B. Padrão Factory Method (Classe `PersonagemFactory`)

* **Problema Resolvido:** Para evitar o uso de grandes blocos `if/else` ou `switch` nos métodos de criação da `Tela`, foi implementado o padrão **Factory Method** na classe `PersonagemFactory`.
* **Desacoplamento:** A `PersonagemFactory` é responsável por receber o `TipoPersonagem` (Enum) e retornar a instância correta (ex: `new Cavaleiro()`), garantindo que a `Tela` não precise saber como cada personagem é instanciado.

### C. Cache de Recursos (Classe `ImageCache`)

* **Otimização:** Para resolver problemas de desempenho causados pela recarga constante de imagens (especialmente em `repaint()`), a classe estática `ImageCache` foi introduzida.
* **Funcionamento:** Na inicialização, todas as imagens de personagens são carregadas do *classpath* e armazenadas em um `Map<TipoPersonagem, Image>`, garantindo que o carregamento de arquivos seja feito apenas uma vez.

### D. Centralização de Controles

* **Lógica de Filtro:** O `PainelControles` é o único responsável por traduzir a entrada do usuário (teclas, rádio botões) para comandos de jogo. Ele utiliza o `ActionCommand` do `ButtonGroup` para obter o tipo de personagem (`"ALDEAO"`, `"CAVALEIRO"`) e repassa essa *string* para a `Tela`, que executa o filtro polimórfico na lista de personagens.

