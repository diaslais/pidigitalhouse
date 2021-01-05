package com.nasinha.digitalspace.planetsmenu.repository

import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.planetsmenu.model.Planet

class PlanetRepository {


    fun getPlanets(callback: (planets: List<Planet>) -> Unit) {
        val planetsDataSet = setPlanetsList()
        callback.invoke(planetsDataSet)
    }

    private fun setPlanetsList(): List<Planet> {
        return listOf(
            Planet(
                R.drawable.sol,
                "Sol",
                "O Sol é a estrela mais próxima da Terra e a energia fornecida por ele é o que possibilita a vida em nosso planeta. É uma bola quente de gases no centro de nosso sistema solar e sua gravidade mantém tudo em sua órbita, desde os maiores planetas até pequenos fragmentos.",
                "•\tRaio: aproximadamente 696.000 km.\n\n" +
                        "•\tMassa: 1.989 x 1030kg.\n\n" +
                        "•\tVolume: 1.40927 x 1018km3.\n\n" +
                        "•\tO Sol é composto de gases: sua composição é de cerca de 70,6% de hidrogênio e 27,4% de hélio.\n\n" +
                        "•\tO Sol está a 150 milhões de km de distância da Terra.\n\n" +
                        "•\tEm sua fotosfera (a superfície visível) a temperatura é de aproximadamente 6.000 graus Celsius. Perto de seu centro, onde ocorrem reações de fusão nuclear que geram a luz e calor, atinge cerca de 15 milhões de graus Celsius.\n\n" +
                        "•\tNo equador, o Sol gira uma vez a cada 25 dias, mas em seus pólos o Sol gira uma vez em seu eixo a cada 35 dias terrestres.\n\n",
                "•\tOs cientistas observam constantemente as manchas solares, que aumentam e diminuem em um ciclo regular de cerca de 10,8 anos. A observação do Sol é fundamental para análise do clima espacial que pode afetar satélites e astronautas.\n\n" +
                        "•\tO Sol inspirou inúmeras histórias mitológicas em culturas ao redor do mundo, incluindo as do Egito antigo, dos astecas no México, de tribos nativas americanas, dos chineses e muitos outros.\n\n" +
                        "•\tO Sol é orbitado por oito planetas, pelo menos cinco planetas anões, dezenas de milhares de asteróides e até três trilhões de cometas e corpos gelados.\n\n" +
                        "•\tA conexão e as interações entre o Sol e a Terra impulsionam as estações, correntes oceânicas, clima, cinturões de radiação e auroras.\n\n" +
                        "•\tEmbora enorme, o Sol ainda não é tão grande quanto outros tipos de estrelas. É classificada como uma estrela anã amarela.\n\n" +
                        "•\tCorrentes elétricas no Sol geram um campo magnético que é conduzido através do sistema solar pelo vento solar - uma corrente de gás eletricamente carregado soprando do Sol em todas as direções.\n\n",
                "•\tAtualmente a raça humana estuda o sol por meio da sonda Parker, uma sonda  com revestimento de carbono de 11 centímetros de grossura que envolve a máquina. Por causa dele, a sonda é capaz de enfrentar temperaturas de quase 1,4 mil graus Celsius — muito inferiores  às da superfície solar, mas suficientes para orbitar o astro. O maior avanço obtido pela sonda até agora foi relativo ao vento solar, nome dado a uma corrente de partículas energizadas que são emitidas pelo Sol e flutuam pelo espaço. A Parker Probe capturou a melhor visão que se tem até o momento do local de nascimento do vento solar, na superfície do astro.\n\n Consulte na íntegra: "
                        + "https://veja.abril.com.br/ciencia/humanidade-comeca-a-explorar-o-sol/",
                "•\tNASA Science - Solar system exploration (em inglês). Consultado em 05/01/2021:\n" +
                        "https://solarsystem.nasa.gov/solar-system/sun\n\n" +
                        "•\tNASA Exploring the universe: Sun for kids (em inglês). Consultado em 05/01/2021:\n" +
                        "https://www.nasa.gov/vision/universe/solarsystem/sun_for_kids_main.html\n\n" +
                        "•\tInstituto Astronômico e Geofísico USP - Investigando a Terra 2000. Consultado em 05/01/2021:\n" +
                        "https://www.iag.usp.br/siae98/universo/universo.htm"
            ),
            Planet(
                R.drawable.mercurio,
                "Mercúrio",
                "Mercúrio - o menor planeta de nosso sistema solar e mais próximo do Sol - é apenas ligeiramente maior que a Lua da Terra. Mercúrio é o planeta mais rápido, girando em torno do Sol a cada 88 dias terrestres.Visto da superfície de Mercúrio, o Sol parece três vezes maior do que quando visto da Terra, e a luz do sol é até sete vezes mais brilhante. Apesar de sua proximidade com o Sol, Mercúrio não é o planeta mais quente do nosso sistema solar - esse título pertence a Vênus, graças à sua densa atmosfera.",
                "•\tDistância do Sol/ Distância da órbita : 0.39 AU (unidades astronômicas : distância entre o Sol e a Terra), equivalente a aproximadamente 58 milhões de quilômetros.\n\n" +
                        "•\tDuração do ano/Período de órbita: 88 dias terrestres.\n\n" +
                        "•\tTempo para incidência da luz solar: aproximadamente 3 minutos e 47 segundos.\n\n" +
                        "•\tLuas: Mercúrio não possui nenhuma lua.\n\n" +
                        "•\tAnéis: Mercúrio não possui nenhum anel ao seu redor.\n\n" +
                        "•\tDiâmetro:  4879 km\n\n" +
                        "•\tMassa: 3,30 x 1023 kg (5,5% da massa da Terra)\n\n" +
                        "•\tTemperatura da superfície: podem chegar a 430 °C de dia e cair para –180 °C à noite.\n\n" +
                        "•\tPrimeiro registro: Mercúrio é conhecido pelo menos desde o tempo dos Sumérios, por volta de 5000 anos atrás.\n\n",
                "•\tO planeta recebeu esse nome em homenagem a Mercúrio, deus romano dos viajantes e do comércio e dos ladrões, sendo também o mensageiro de Zeus e a personificação da inteligência, esperteza.\n\n" +
                        "•\tO dia em mercúrio dura 59 dias terrestres e um ciclo dia-noite completo dura 175,97 dias terrestres.\n\n" +
                        "•\tPor causa da órbita elíptica e rotação lenta, o Sol da manhã parece nascer brevemente, se pôr e nascer novamente em algumas partes da superfície do planeta. A mesma coisa acontece, porém ao contrário, ao pôr do sol.\n\n" +
                        "•\tMercúrio é um planeta rochoso, também conhecido como planeta terrestre. Ele tem uma superfície sólida com crateras, muito parecida com a lua da Terra.\n\n" +
                        "•\tNão foram encontradas evidências de vida em Mercúrio.\n\n" +
                        "•\tA sonda  Mariner 10 da NASA foi a primeira enviada em missão para explorar este planeta. A sonda MESSENGER da NASA foi a primeira a orbitar o planeta. \n\n" +
                        "•\tA fina atmosfera de mercúrio, ou exosfera, é composta principalmente de oxigênio (O2), sódio (Na), hidrogênio (H2), hélio (He) e potássio (K).\n\n" +
                        "•\tMercúrio possui água em forma de gelo em crateras polares.\n\n",
                "•\tDuas naves espaciais da missão BepiColombo da ESA-JAXA (missão conjunta entre a Agência Espacial Europeia e a Agência Japonesa de Exploração Aeroespacial) estão a caminho de Mercúrio para estudá-lo.\n\n"
                        + "Consulte na íntegra: https://gizmodo.uol.com.br/mercurio-bepicolombo-primeira-foto-espaco/",
                "•\tBritannica (em inglês). Consultado em 05/12/2020:\n" +
                        "https://www.britannica.com/place/Mercury-planet\n\n" +
                        "•\tInstituto Astronômico e Geofísico USP - Investigando a Terra 2000. Consultado em 05/12/2020:\n" +
                        "https://www.iag.usp.br/siae98/universo/sistsolar.htm\n\n" +
                        "•\tNASA Science - Solar system exploration (em inglês). Consultado em 05/12/2020:\n" +
                        "https://solarsystem.nasa.gov/planets/mercury/overview/\n\n" +
                        "•\tNasa Planetary Science (em inglês). Consultado em 05/12/2020:\n" +
                        "https://nssdc.gsfc.nasa.gov/planetary/factsheet/mercuryfact.html"
            ),

            Planet(
                R.drawable.venus,
                "Vênus",
                "Semelhante em tamanho e estrutura à Terra, Vênus foi chamado de gêmeo da Terra. No entanto, existem diferenças radicais entre os dois planetas. É um planeta conhecido desde tempos muito antigos pois pode ser visto facilmente a olho nu.",
                "•\tRaio: 6.052 km.\n\n" +
                        "•\tMassa: 4.8673 x 1024kg.\n\n" +
                        "•\tVolume: 9.28415 x 1011km3.\n\n" +
                        "•\tA atmosfera de Vênus consiste principalmente de dióxido de carbono, com nuvens de gotículas de ácido sulfúrico.\n\n" +
                        "•\tEstá a aproximadamente 108 milhões de km de distância do Sol.\n\n" +
                        "•\tA paisagem é empoeirada e as temperaturas da superfície chegam a escaldantes 880 graus Fahrenheit (471 graus Celsius).\n\n" +
                        "•\tEle completa uma rotação em 243 dias terrestres - o dia mais longo de qualquer planeta em nosso sistema solar, ainda mais longo do que um ano inteiro em Vênus.\n\n",
                "•\tA superfície sólida de Vênus é uma paisagem vulcânica coberta por extensas planícies com altas montanhas vulcânicas e vastos planaltos.\n\n" +
                        "•\tVênus é um entre apenas dois planetas que giram de leste a oeste. Apenas Vênus e Urano têm essa rotação \"para trás\".\n\n" +
                        "•\tVênus foi o primeiro planeta a ser explorado por uma espaçonave - o Mariner 2 da NASA voou com sucesso e escaneou o planeta coberto de nuvens em 14 de dezembro de 1962.\n\n" +
                        "•\tMais de 40 espaçonaves exploraram Vênus. A missão Magellan dos anos 90 mapeou a superfície do planeta e atualmente é orbitado pela sonda Akatsuki.\n\n" +
                        "•\tVênus tem o nome da antiga deusa romana do amor e da beleza, que era conhecida como Afrodite pelos antigos gregos.\n\n" +
                        "•\tAs temperaturas extremas de Vênus e as nuvens ácidas tornam-no um lugar impossível para a vida humana.\n\n",
                "•\tCientistas acreditam na descoberta de vida em Vênus\n\n" +
                        "Consulte na íntegra: " + "https://www.bbc.com/portuguese/geral-54153219",
                "•\tNASA Science - Solar system exploration (em inglês). Consultado em 05/01/2021:\n" +
                        "https://solarsystem.nasa.gov/planets/venus\n\n" +
                        "•\tNASA Science - Space Place (em inglês). Consultado em 05/01/2021:\n" +
                        "https://spaceplace.nasa.gov/all-about-venus/en/"
            ),
            Planet(
                R.drawable.terra,
                "Terra",
                "A Terra é o terceiro planeta mais próximo do Sol. É nosso planeta natal e o único lugar que conhecemos até então habitado por seres vivos. Embora a Terra seja apenas o quinto maior planeta do sistema solar, é o único com a presença de água líquida na superfície.",
                "•\tRaio: 6.371 km.\n\n" +
                        "•\tMassa: 5.972 x 1024 kg.\n\n" +
                        "•\tVolume: 1.083 x 1012 km3.\n\n" +
                        "•\tPerto da superfície, a Terra tem uma atmosfera que consiste em 78% de nitrogênio, 21% de oxigênio e 1% de outros gases, como dióxido de carbono.\n\n" +
                        "•\tA Terra está aproximadamente a 150 milhões de km de distância do Sol.\n\n" +
                        "•\tO núcleo interno da Terra é uma esfera sólida feita de metais de ferro e níquel com cerca de 1.221 km de raio. Lá, a temperatura chega a 9.800 graus Fahrenheit (5.400 graus Celsius).\n\n" +
                        "•\tConforme a Terra orbita o Sol, ela completa uma rotação a cada 23,9 horas. Leva 365,25 dias para completar uma viagem ao redor do sol. Esse quarto extra de dia representa um desafio para nosso sistema de calendário, que conta um ano como 365 dias.\n\n",
                "•\tA cadela Laika foi a primeira terráquea a orbitar a Terra a bordo do Sputnik 2 da União Soviética em 1957, porém não sobreviveu à viagem. Alguns anos depois, houve dois cães espaciais soviéticos - Belka e Strelka - os primeiros seres a retornar do espaço com vida, abrindo caminho para futuros exploradores humanos.\n\n" +
                        "•\tA Terra é um planeta rochoso com uma superfície sólida e dinâmica de montanhas, cânions, planícies e muito mais. A maior parte do nosso planeta está coberta de água (cerca de 70%).\n\n" +
                        "•\tMuitas espaçonaves em órbita estudam a Terra de cima como um sistema completo - observando a atmosfera, o oceano, as geleiras e a terra sólida.\n\n" +
                        "•\tNossa atmosfera nos protege de meteoróides que chegam, muitos dos quais se dividem em nossa atmosfera antes que possam atingir a superfície.\n\n",
                "•\tAsteroide do tamanho de um carro passou perto da Terra\n\n" +
                        "Consulte na íntegra: https://www.terra.com.br/noticias/ciencia/espaco/asteroide-do-tamanho-de-um-carro-passou-perto-da-terra,2eacbcb5dc3838db960f632729da08bev1kiyjac.html",
                "•\tNASA Science - Space Place (em inglês). Consultado em 05/01/2021:\n" +
                        "https://spaceplace.nasa.gov/all-about-earth/en/\n\n" +
                        "•\tNASA Science - Solar system exploration (em inglês). Consultado em 05/01/2021:\n" +
                        "https://solarsystem.nasa.gov/planets/earth"
            ),
            Planet(
                R.drawable.marte,
                "Marte",
                "Marte é o quarto planeta a partir do Sol, o segundo menor do Sistema Solar. Batizado em homenagem ao deus romano da guerra, muitas vezes é descrito como o \"Planeta Vermelho\", porque o óxido de ferro predominante em sua superfície lhe dá uma aparência avermelhada.",
                "•\tDistância do Sol/ Distância da órbita:  1.52 AU, o equivalente a 228 milhões de quilômetros.\n\n" +
                        "•\tDuração do ano/Período de órbita: 687 dias terrestres\n\n" +
                        "•\tTempo para incidência da luz solar: aproximadamente 12 minutos.\n\n" +
                        "•\tLuas: Marte possui 2 luas, Fobos e Deimos.\n\n" +
                        "•\tDiâmetro:  6.792 km\n\n" +
                        "•\tMassa: 6,417 x 1023 kg (10,7% da massa da Terra)\n\n" +
                        "•\tTemperatura da superfície:  20°C a -153°C\n\n" +
                        "•\tPrimeiro registro:  Já era conhecido pelos antigos. Registros egípcios já citavam Marte, há mais de 4.000 anos.\n\n" +
                        "•\tIdade: 4,6 bilhôes de anos\n\n",
                "•\tO planeta recebeu esse nome em homenagem a Marte, deus romano da guerra.\n\n" +
                        "•\tMarte é conhecido como o Planeta Vermelho porque os minerais de ferro do solo marciano se oxidam, ou enferrujam, fazendo com que o solo e a atmosfera pareçam vermelhos.\n\n" +
                        "•\tMarte possui estações, calotas polares, cânions e vulcões extintos, além de evidências de que foi muito mais ativo no passado.\n\n" +
                        "•\tMarte possui a maior montanha do Sistema Solar, chamada de Monte Olimpo.\n\n" +
                        "•\tMarte é um planeta facilmente visível a olho nu.\n\n" +
                        "•\tUm dia em Marte leva pouco mais de 24 horas (24 horas e 39 minutos).\n\n" +
                        "•\tMarte tem uma atmosfera fina composta principalmente de dióxido de carbono (CO2), argônio (Ar), nitrogênio (N2) e uma pequena quantidade de oxigênio e vapor de água.\n\n" +
                        "•\tVárias missões visitaram este planeta, desde sondas “flybys” (sondas que passam próximo a um astro e o analisam sem entrar em órbita)  e orbitadores a rovers na superfície. O primeiro verdadeiro sucesso da missão a Marte foi o da sonda Mariner 4 em 1965.\n\n" +
                        "•\tNeste momento, a superfície de Marte não pode suportar a vida como a conhecemos. As missões atuais estão determinando o potencial passado e futuro de Marte para a vida.\n\n" +
                        "•\tMarte é um dos corpos mais explorados em nosso sistema solar e é o único planeta para onde enviamos rovers para percorrer a paisagem alienígena ( Sojourner(1997), Spirit e Opportunity(2003), Curiosity(2011), Perseverance(2020)).\n\n",
                "•\tA NASA lançou a próxima geração de rovers, o Perseverance  para Marte em 30 de julho de 2020.\n\n" +
                        "Consulte na íntegra: https://canaltech.com.br/espaco/mars-2020-rover-perseverance-e-helicoptero-ingenuity-sao-lancados-rumo-a-marte-169033/",
                "•\tBritannica (em inglês). Consultado em 05/12/2020:\n" +
                        "https://www.britannica.com/place/Mars-planet\n\n" +
                        "•\tNASA Science - Solar system exploration (em inglês). Consultado em 05/12/2020:\n" +
                        "https://solarsystem.nasa.gov/planets/mars/overview/\n\n" +
                        "•\tNasa Planetary Science (em inglês). Consultado em 05/12/2020:\n" +
                        "https://nssdc.gsfc.nasa.gov/planetary/factsheet/marsfact.html\n\n" +
                        "•\tNúcleo de astronomia CCVA. Consultado em 05/12/2020:\n" +
                        "http://www.ccvalg.pt/astronomia/sistema_solar/marte.htm\n\n" +
                        "•\tNature (em inglês). Consultado em 05/12/2020:\n" +
                        "https://www.nature.com/articles/d41586-020-02751-1\n\n" +
                        "•\tPubMed (em inglês). Consultado em 05/12/2020:\n" +
                        "https://pubmed.ncbi.nlm.nih.gov/33257545/"
            ),

            Planet(
                R.drawable.jupiter,
                "Júpiter",
                "Júpiter é o maior planeta do nosso sistema solar. É um gigante gasoso e não tem uma superfície sólida, mas pode ter um núcleo interno sólido do tamanho da Terra. Possui vários anéis, mas são feitos de poeira e muito fracos, dificultando sua visibilidade.",
                "•\tRaio: 69.911 km.\n\n" +
                        "•\tMassa: 1.8981 x 1027kg.\n\n" +
                        "•\tVolume: 1.43128 x 1015km3.\n\n" +
                        "•\tA atmosfera de Júpiter é composta principalmente de hidrogênio (H2) e hélio (He).\n\n" +
                        "•\tJúpiter está a 778 milhões de km de distância do Sol.\n\n" +
                        "•\tPossui um núcleo central de material sólido ou, pode-se dizer, de uma sopa espessa, superquente e densa, podendo atingir até 90.032 graus Fahrenheit (50.000 graus Celsius) lá embaixo.\n\n" +
                        "•\tUm dia em Júpiter leva apenas cerca de 10 horas (o tempo que leva para Júpiter girar uma vez), e faz uma órbita completa ao redor do Sol (um ano no tempo de Júpiter) em cerca de 12 anos terrestres (4.333 dias terrestres).\n\n",
                "•\tNove espaçonaves visitaram Júpiter. Sete passaram voando e dois orbitaram o gigante gasoso. Juno, o mais recente, chegou a Júpiter em 2016.\n\n" +
                        "•\tOnze Terras poderiam caber no equador de Júpiter. Se a Terra fosse do tamanho de uma uva, Júpiter seria do tamanho de uma bola de basquete.\n\n" +
                        "•\tComo um gigante gasoso, Júpiter não tem uma superfície verdadeira. O planeta é composto principalmente de gases e líquidos. A camada superior é provavelmente feita de gelo de amônia, a do meio é provavelmente feita de cristais de hidrossulfeto de amônio e a mais interna pode ser feita de gelo e vapor de água.\n\n" +
                        "•\tA Grande Mancha Vermelha de Júpiter é uma tempestade gigantesca que tem cerca de duas vezes o tamanho da Terra e assola o planeta há mais de um século.\n\n" +
                        "•\tJúpiter tem 53 luas nomeadas e outras 26 aguardando nomes oficiais. Combinados, os cientistas agora pensam que Júpiter tem 79 luas.\n\n" +
                        "•\tAs quatro maiores luas de Júpiter - Io, Europa, Ganimedes e Calisto - foram observadas pela primeira vez pelo astrônomo Galileo Galilei em 1610 usando uma versão inicial do telescópio.\n\n" +
                        "•\tIo é o corpo mais vulcanicamente ativo do sistema solar.\n\n" +
                        "•\tGanimedes é a maior lua do sistema solar (ainda maior que o planeta Mercúrio).\n\n" +
                        "•\tAs poucas crateras pequenas de Calisto indicam um pequeno grau de atividade de superfície atual.\n\n" +
                        "•\tUm oceano de água líquida com os ingredientes para a vida pode estar sob a crosta congelada de Europa, tornando-a um lugar tentador para explorar.\n\n",
                "•\tLua de Júpiter, além da possibilidade de abrigar vida, pode brilhar no escuro \n\n" +
                        "Consulte na íntegra: https://www.hypeness.com.br/2020/11/lua-de-jupiter-alem-da-possibilidade-de-abrigar-vida-pode-brilhar-no-escuro/",
                "•\tInstituto Astronômico e Geofísico USP - Investigando a Terra 2000. Consultado em 05/01/2021:\n" +
                        "https://www.iag.usp.br/siae98/universo/universo.htm\n\n" +
                        "•\tNASA Science - Solar system exploration (em inglês). Consultado em 05/01/2021:\n" +
                        "https://solarsystem.nasa.gov/moons/jupiter-moons/\n" +
                        "https://solarsystem.nasa.gov/planets/jupiter/"
            ),
            Planet(
                R.drawable.saturno,
                "Saturno",
                "Saturno não é o único planeta a ter anéis, mas definitivamente tem os mais bonitos. É o sexto planeta a partir do Sol e o segundo maior do nosso sistema solar.  É um planeta visível a olho nu a partir da Terra.",
                "•\tRaio: 58.232km.\n\n" +
                        "•\tMassa: 5.6832 x 1026kg.\n\n" +
                        "•\tVolume: 8.2713 x 1014km3.\n\n" +
                        "•\tComo o gigante gasoso Júpiter, Saturno é uma bola enorme feita principalmente de hidrogênio e hélio.\n\n" +
                        "•\tSaturno está aproximadamente a 1,4 bilhões de km de distância do Sol.\n\n" +
                        "•\tSaturno leva cerca de 10,7 horas para girar em seu eixo uma vez (um “dia” de Saturno) e 29 anos terrestres para orbitar o Sol.\n\n",
                "•\tSaturno tem 53 luas conhecidas com 29 luas adicionais aguardando a confirmação de sua descoberta - ou seja, um total de 82 luas.\n\n" +
                        "•\tDuas vezes a cada 29 anos e meio, o grande planeta Saturno parece sem anéis. Trata-se de uma ilusão de ótica em que, devido a sua posição, os anéis não podem ser vistos da Terra e mal ficam visíveis mesmo com telescópios potentes.\n\n" +
                        "•\tQuando Galileu Galilei viu Saturno através de um telescópio em 1600, ele não tinha certeza do que estava vendo. A princípio ele pensou que estava olhando para três planetas, ou um planeta com alças. Agora sabemos que essas \"alças\" eram os anéis de Saturno.\n\n" +
                        "•\tQuatro espaçonaves robóticas visitaram Saturno. O Pioneer 11 da NASA em setembro de 1979, as naves gêmeas Voyager 1 e Voyager 2 da NASA fizeram sobrevôos com nove meses de intervalo em 1980 e 1981, e a missão Cassini orbitou Saturno durante 13 anos, antes de seus engenheiros o transformarem em uma sonda atmosférica em setembro de 2017. A Cassini também carregou a Sonda Huygens da ESA, que pousou na lua de Saturno, Titã, em 2005.\n\n" +
                        "•\tO satélite Titã é o único satélite do Sistema Solar com uma atmosfera; é composta basicamente de gás metano, que é um dos componentes da gasolina.\n\n" +
                        "•\tSaturno possui um magnífico sistema de anéis. São cerca de mil anéis concêntricos, compostos por pedras, recobertas ou não de gelo e de gelo seco (gelo de gás carbônico) e de poeira. Imagina-se que esses anéis se formaram de matéria que sobrou depois da formação do planeta. Podem ter, também, componentes de algum satélite que se fragmentou depois de sua formação.\n\n",
                "•\tCrateras de impacto revelam detalhes do desgaste dinâmico da superfície de Titã(Lua de Saturno)\n\n" +
                        "Consulte na íntegra: https://canaltech.com.br/espaco/novo-mapa-de-tita-revela-que-a-lua-de-saturno-e-coberta-por-material-organico-155599/",
                "•\tInstituto Astronômico e Geofísico USP - Investigando a Terra 2000. Consultado em 05/01/2021:\n" +
                        "https://www.iag.usp.br/siae98/universo/universo.htm\n\n" +
                        "•\tNASA Science - Solar system exploration (em inglês). Consultado em 05/01/2021:\n" +
                        "https://solarsystem.nasa.gov/planets/saturn\n\n" +
                        "•\tNASA Science - Space Place (em inglês). Consultado em 05/01/2021:\n" +
                        "https://spaceplace.nasa.gov/all-about-saturn/en/"
            ),
            Planet(
                R.drawable.urano,
                "Urano",
                "Urano é o sétimo planeta a partir do Sol, o terceiro maior e o quarto mais massivo dos oito planetas do Sistema Solar. Foi nomeado em homenagem ao deus grego do céu, Urano, o pai de Cronos e o avô de Zeus.",
                "•\tDensidade: 1.270g/cm³ (aproximadamente ¼ da terra).\n\n" +
                        "•\tDescoberto em 13 de março de 1781.\n\n" +
                        "•\tDistância orbital média: 2.870.658,186 km. (19 vezes a da terra).\n\n" +
                        "•\tInclinação equatorial: 97.8º.\n\n" +
                        "•\tGravidade no equador: 8.87m/s².\n\n" +
                        "•\tMassa: 86,813 x 1024 kg (14,54 vezes a da terra).\n\n" +
                        "•\tRaio equatorial: 25.362 km (4 vezes a da terra).\n\n" +
                        "•\tTemperatura em 1 bar: 76K ( -197 ºC).\n\n" +
                        "•\tVelocidade de órbita: 6,80 km/s. (0,228 vezes a da terra).\n\n" +
                        "•\tVolume: 6.833x1010 km3 (63 vezes a da terra).",
                "•\tConhecido como planeta “de lado”, por conta da sua rotação\n\n" +
                        "•\tFoi descoberto em 1781 por William Herschel.\n\n" +
                        "•\tFoi o primeiro planeta descoberto com uso de telescópio.\n\n" +
                        "•\tUrano é um gigante de gelo e é quase quatro vezes maior que a terra.\n\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n\n" +
                        "•\tOs satélites conhecidos de Urano totalizam 27, e na sua maioria são nomeados em relação a personagens literários, como: Ophelia, Ferdinand e Ariel.\n\n" +
                        "•\tAssim como Saturno, Júpiter e Netuno, Urano é um planeta com anéis.\n\n",
                "•\tUrano pode ter colidido com enorme planeta gelado durante sua formação\n\n" +
                        "Consulte na íntegra: https://revistagalileu.globo.com/Ciencia/Espaco/noticia/2020/04/urano-pode-ter-colidido-com-enorme-planeta-gelado-durante-sua-formacao.html",
                "•\tNASA Science - Space Place (em inglês). Consultado em 07/12/2020:\n" +
                        "https://spaceplace.nasa.gov/all-about-uranus/en/.\n\n" +
                        "•\tNASA Science - Solar system exploration (em inglês). Consultado em 07/12/2020:\n" +
                        "https://solarsystem.nasa.gov/planets/uranus\n" +
                        "https://solarsystem.nasa.gov/moons/uranus-moons\n\n" +
                        "•\tNASA (em inglês). Consultado em 07/12/2020:\n" +
                        "https://www.nasa.gov/uranus\n\n" +
                        "•\tSpace.com (em inglês). Consultado em 07/12/2020:\n" +
                        "https://www.space.com/topics/uranus"
            ),
            Planet(
                R.drawable.netuno,
                "Netuno",
                "Netuno é um gigante de gelo muito similar a Urano, e é o 8º planeta mais distante do sol. É um planeta escuro, frio e afetado por ventos supersônicos, que está 30 vezes mais distante do sol que a terra." +
                        "Netuno não tem uma superfície sólida, e sua atmosfera é formada basicamente por hidrogênio, hélio e metano que se estende por grandes profundidades, gradualmente se misturando com água e outros compostos, até chegar o núcleo sólido que é aproximadamente do tamanho da terra.\n",
                "•\tDensidade:  1.638 kg/m³ (aproximadamente ¼ a da terra).\n\n" +
                        "•\tDescoberto em 23 de setembro de 1846.\n\n" +
                        "•\tDistância orbital média: 2.870.658,186 km. (19 vezes a da terra)\n\n" +
                        "•\tGravidade no equador: 11.15 m/s²\n\n" +
                        "•\tMassa: 102,413 x 1024 kg (17.15 vezes a da terra).\n\n" +
                        "•\tRaio no equador: 24.764 (3,83 vezes a da terra).\n\n" +
                        "•\tTemperatura em 1 bar: 72 K (-201 ºC)\n\n" +
                        "•\tVelocidade de órbita: 5,43 km/s. (0,182 vezes a da terra).\n\n" +
                        "•\tVolume: 6.254 x 1010 km³ (57,74 vezes a da terra).\n\n",
                "•\tMais de 80% da sua massa é formada por fluídos densos de água, metano e amônia\n\n" +
                        "•\tNetuno possui 6 anéis.\n\n" +
                        "•\tUm dia em Netuno equivale a 16 horas na terra.\n\n" +
                        "•\tA jornada para percorrer uma rotação ao redor do Sol leva 165 anos terrestres.\n\n" +
                        "•\tNetuno tem 14 satélites confirmados.\n\n" +
                        "•\tApenas a Voyager 2 passou por Netuno, em 1989.\n\n",
                "•\tDe volta a Netuno: Muito se tem falado nas missões de exploração dos planetas mais distantes do sistema solar\n\n" +
                        "Consulte na íntegra: https://g1.globo.com/ciencia-e-saude/blog/cassio-barbosa/post/2020/01/03/de-volta-a-netuno-entenda-os-proximos-passos-na-exploracao-do-sistema-solar-exterior.ghtml",
                "•\tNASA Science - Space Place (em inglês). Consultado em 07/12/2020:\n" +
                        "https://spaceplace.nasa.gov/all-about-neptune/en/\n\n" +
                        "•\tNASA Science - Solar system exploration (em inglês). Consultado em 07/12/2020:\n" +
                        "https://solarsystem.nasa.gov/planets/neptune\n" +
                        "https://solarsystem.nasa.gov/moons/neptune-moons/\n\n" +
                        "•\tNasa Planetary Science (em inglês). Consultado em 07/12/2020:\n" +
                        "https://nssdc.gsfc.nasa.gov/planetary/factsheet/neptunefact.html"
            )
        )
    }
}