## SI-LA-BA-DOR
O projeto consiste em um jogo para identificar a palavra dentre um número de sílabas e ordenar as sílabas corretamente. O projeto foi feito para disciplina de Programação Orientada a Objetos (SSC0103) e visa aplicar conceitos de Programação Orientada a Objetos: objetos, herança, enums, etc. e padronização de arquivos MVC.

### Niveis
O sistema possui 3 niveis de jogabilidades diferentes:
- Level 1: até 3 silabas e será apresentado 6 sílabas para escolha.
- Level 2: até 4 sílabas e será apresentado 6 sílabas para escolha.
- Level 3: acima de 4 sílabas e será apresentado 8 sílabas para escolha.

### Arquivos
O SI-LA-BA-DOR utiliza dois tipos de arquivos: arquivos de níveis e o arquivo administrativo.

#### Arquivo de nível
Os arquivos serão por níveis, ou seja, cada nível possui um arquivo. 
O arquivo de nível possui nome nivel<número do nível>.txt como `nivel1.txt`.
O arquivo irá conter um cabeçalho que informa a quantidade de palavras e quantas linhas deve-se pular para chegar numa determinada quantidade de sílabas. Além das palavras e as síalbas adicionais.
O formato dos arquivos será:

```
[cabeçalho]
[silabas separadas por -] [silabas adicionais que irão aparecer separadas por espaço]
```

Exemplo: 
```
3
0
1
2
ma-te-ma-ti-ca va pri za
in-te-li-gên-ci-a o co
re-la-ci-o-na-men-to pa
.
```

`3`: Quantidade de palavras
`0`: Ao pular 0 linhas, chega-se a palavras de 5 silabas
`1`: Ao pular 1 linha, chega-se a palavra de 6 silabas
`2`: Ao pular 2 linhas, chega-se a palavra de 7 silabas

#### Arquivo administrativo
O arquivo administrativo simula um "banco de dados" simples. A ideia é conter os nomes de usuários que são administradores. Esses nomes, ao serem colocados na tela de Menu, são levados para uma página administrativa, não de jogo. 
O arquivo administrativo possui nome `administradores.txt`.
