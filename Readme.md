Esse é um projeto para a disciplina de Sistemas Distribuídos que consome a pesquisa de nome da API
pública de YuGiOh https://ygoprodeck.com/api-guide/.

Vídeo de demonstração com cliente React: https://youtu.be/9ssn667zvsk

Link do SwaggerHub com descrição da OpenAPI: https://app.swaggerhub.com/apis/devsamuel/YuGiGo/1.0.0/

<h2>Sobre o que foi construído</h2>

A API pública de YuGiOh disponibiliza inicialmente uma grande lista com todas as cartas e suas respectivas informações necesárias,
além disso, também é possível parametrizar uma pesquisa com alguns dados relevantes ao jogo como, por exemplo, o nome da carta, seu
atributo, tipo, ração, etc.

A partir disso, essa API funciona como um CRUD de cartas. A partir do parâmetro de pesquisa "/name" da rota POST de CardController,
é possível pesquisar e tratar os dados retornados e então cadastrar num banco de dados. Também existe em CardController uma rota que retorna
cartas em formato ProtocolBuffer para compartilhamento com formato binário canônico.

Além disso, também é possível criar Decks e relacionar diretamente cartas e decks a partir de DeckController.