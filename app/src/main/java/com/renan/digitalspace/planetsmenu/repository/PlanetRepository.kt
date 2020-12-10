package com.renan.digitalspace.planetsmenu.repository

import com.renan.digitalspace.R
import com.renan.digitalspace.planetsmenu.model.Planet

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
                "O sol é uma estrela. A mais próxima da Terra e a que assegurou as condições necessárias de vida deste planeta. Ele é basicamente uma bola de gás incandescente a temperaturas inimagináveis (5.785 K, temperatura efetiva) e, embora esteja a milhões de quilômetros da terra (1,496 x 10³³ km) tem fortes influências sobre nós.",
                "•\tA composição do Sol é de 74% de hidrogênio e 24% de hélio, sendo o percentual restante formado principalmente por oxigênio, carbono e ferro.\n\n" +
                        "•\tToda a energia produzida pelo Sol é proveniente do processo de fusão nuclear decorrente das grandes temperaturas de seu núcleo (cerca de 15 milhões de kelvin) e de sua enorme pressão.\n\n" +
                        "•\tAo todo, o Sol consome cerca de 4 milhões de toneladas de sua massa por segundo, uma taxa mais do que suficiente para mantê-lo brilhando pelos próximos 6 ou 7 bilhões de anos, devido à sua grande massa, que é de aproximadamente 1,98.1031 kg, mais de 330 mil vezes a massa da Terra.\n\n" +
                        "•\tPor conta de sua enorme massa, a gravidade na superfície do Sol chega a 274 m/s², 27,4 vezes maior que a massa da Terra. Isso faz com que a velocidade de escape por lá chegue aos 617 km/s, mais de 2 milhões de quilômetros por hora.\n\n" +
                        "•\tO período de rotação do Sol em torno do seu próprio eixo é de 27 dias para o seu equador, que gira a 7189 km/h, e de 35 dias para os seus polos. Essa diferença de período rotacional produz uma rotação diferencial (chamada de dínamo solar), responsável por sua grande atividade magnética, uma vez que toda a matéria presente na estrela encontra-se ionizada (no estado plasmático), dando origem às tempestades solares, erupções coronárias e manchas solares.\n\n",

                "•\tUma viagem da Terra com destino ao Sol, em uma espaçonave, levaria cerca de 124 dias.\n\n" +
                        "•\tA quantidade de energia liberada pelo Sol em um segundo é superior a produzida pelo ser humano em toda a história.\n\n" +
                        "•\tSua temperatura ultrapassa 5.500 C. Calcula-se que em seu centro, o Sol tenha uma temperatura de 15 milhões de graus centígrados.\n\n" +
                        "•\tO Sol possui 99,86% de toda a massa do Sistema Solar, e é 300 mil vezes mais pesado do que a Terra.\n\n" +
                        "•\tUma pessoa de 70 quilos pesaria cerca de 1.900 quilos no Sol.\n" +
                        "•\tA luz do Sol demora 8 minutos para chegar na Terra. Isso significa que se o Sol explodisse agora, nós só perceberíamos daqui a 8 minutos.\n\n" +
                        "•\tA cada segundo de brilho, o Sol libera uma quantidade de energia equivalente a 1 milhão de bombas de hidrogênio.\n\n" +
                        "•\tSe o Sol tivesse apenas um centímetro de diâmetro, a estrela mais próxima estaria a 285 quilômetros de distância.\n\n",

                "Atualmente a raça humana estuda o sol por meio da sonda Parker, uma sonda  com revestimento de carbono de 11 centímetros de grossura que envolve a máquina. Por causa dele, a sonda é capaz de enfrentar temperaturas de quase 1,4 mil graus Celsius — muito inferiores  às da superfície solar, mas suficientes para orbitar o astro. O maior avanço obtido pela sonda até agora foi relativo ao vento solar, nome dado a uma corrente de partículas energizadas que são emitidas pelo Sol e flutuam pelo espaço. A Parker Probe capturou a melhor visão que se tem até o momento do local de nascimento do vento solar, na superfície do astro.\n Consulte na íntegra: "
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

                "•\tDuas naves espaciais da missão BepiColombo da ESA-JAXA (missão conjunta entre a Agência Espacial Europeia e a Agência Japonesa de Exploração Aeroespacial) estão a caminho de Mercúrio para estudá-lo.\n\n",
            ),

            Planet(
                R.drawable.venus,
                "Vênus",
                "Vênus é o segundo planeta mais próximo ao Sol e o segundo objeto mais brilhante no céu noturno, atrás apenas da lua. Devido às muitas semelhanças com nosso planeta, Vênus também é considerado um planeta irmão da Terra, por conta de sua massa densidade e volume, mas apesar disso, ele tem características únicas e impressionantes.",
                "•\tDiâmetro: 12.104 km;\n\n" +
                        "•\tMassa: 4,87 x 10 ^ 24 kg (81,5% do tamanho da Terra);\n\n" +
                        "•\tLuas: nenhuma;\n\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n\n" +
                        "•\tPeríodo de órbita: 225 dias;\n\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n\n",

                "•\tVênus é o planeta mais próximo da Terra.\n\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n\n",

                "Cientistas acreditam na descoberta de vida em Vênus\n",
            ),
            Planet(
                R.drawable.terra,
                "Terra",
                "A Terra é o terceiro planeta mais próximo do sol, o mais denso e o quinto maior dos oito planetas do sistema solar. É também o maior dos quatro planetas telúricos. É por vezes designada como Mundo ou Planeta Azul. Lar de milhões de espécies de seres vivos, incluindo os humanos, a Terra é o único corpo celeste onde é conhecida a existência de vida.\n",

                "•\tDiâmetro: 12,104 km;\n\n" +
                        "•\tDistância do Sol: 149.600.000 km\n\n" +
                        "•\tVelocidade orbital média: 29,69 km/s\n\n" +
                        "•\tDiâmetro equatorial: 12.756,3 km\n\n" +
                        "•\tÁrea da superfície: 5,10072×10 8 km²\n\n" +
                        "•\tMassa: 5,9742×10 24 kg\n\n" +
                        "•\tTemperatura à superfície: 15 ºC\n\n" +
                        "•\tTranslação: 365,2564 dias\n\n" +
                        "•\tRotação: 23,9345 horas",

                "•\tVocê sabia que a Terra não é plana? Os cientistas utilizaram várias técnicas para chegarem a essa conclusão. Atualmente, a geodésia é a ciência que faz estudos a respeito das dimensões, forma e gravidade do planeta e nos permite afirmar que a Terra tem o formato arredondado.\n\n" +
                        "•\tA rotação da Terra diminui gradualmente, contudo, de maneira praticamente imperceptível aos seres humanos. Essa diminuição é de aproximadamente 17 milissegundos a cada 100 anos e provoca o aumento da duração do dia.\n\n" +
                        "•\tA Terra não foi nomeada segundo o método romano de designação, ao contrário dos outros sete planetas do Sistema Solar.\n\n",

                "Asteroide do tamanho de um carro passou perto da Terra\n"
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
                "•\tA NASA lançou a próxima geração de rovers, o Perseverance  para Marte em 30 de julho de 2020.\n\n",
            ),

            Planet(
                R.drawable.jupiter,
                "Júpiter",
                "Júpiter é o maior planeta do Sistema Solar, estando situado entre Marte e Saturno. Seu tamanho rende-lhe vários satélites naturais orbitando ao seu redor, cerca de 70. Durante a noite, esse planeta pode ser visto a olho nu, sendo a segunda estrela mais brilhante, atrás apenas de Vênus, o segundo planeta na ordem usando-se o Sol como referência.",
                "•\tDiâmetro equatorial: 142,984 km\n\n" +
                        "•\tÁrea da superfície: 6.14 x 10 ^ 10 km²\n\n" +
                        "•\tMassa: 1.899x10 ^ 27 kg\n\n" +
                        "•\t Distância do Sol: 778.330.000 km\n\n" +
                        "•\tLuas: estima-se que Júpiter tenha entre 60 e 70 satélites conhecidos. Quatro foram apelidados de Luas Galileanas, pois foram descobertas, em 1610, por Galileu Galilei. Ganímedes, Calisto, Io e Europa são seus nomes. A primeira é um pouco maior que Mercúrio, e as outras três são semelhantes à nossa Lua.\n\n" +
                        "•\tPeríodo de rotação: aproximadamente 10 horas\n\n" +
                        "•\tPeríodo de translação: aproximadamente 12 anos\n\n" +
                        "•\tTemperatura média: -121,1 °C\n" +
                        "Composição atmosférica: a atmosfera de Júpiter é composta, basicamente, por dois gases: 86% de hidrogênio e 14% de hélio. Há, de forma ínfima, a presença de metano, amoníaco, vapor d’água e sulfureto de hidrogênio.",

                "•\tO campo magnético de Júpiter é muito mais forte que o da Terra. Esse fato pode ajudar-nos a entender a grande quantidade de satélites naturais em sua órbita.\n\n" +
                        "•\tSe morássemos em Júpiter, faríamos aniversário de 12 em 12 anos, tempo de translação do planeta.\n\n" +
                        "•\tEuropa, uma das Luas Galileanas, pode ter um oceano líquido em sua superfície.\n\n" +
                        "•\tVentos de 600 km/hora são comuns em Júpiter.\n\n" +
                        "•\tA massa de Júpiter é 2,5 vezes maior do que os outros sete planetas do Sistema Solar juntos. Um gigante.\n\n" +
                        "•\tCaso o interior de Júpiter fosse oco, caberiam 1300 Terras dentro dele.\n\n" +
                        "•\tÉ um planeta gasoso, como Saturno, Urano e Netuno.n" +
                        "•\tJúpiter possui a maior velocidade de rotação entre os planetas do Sistema Solar.\n\n" +
                        "•\tDesde a década de 1970, sete sondas já visitaram Júpiter\n\n",
                "Lua de Júpiter, além da possibilidade de abrigar vida, pode brilhar no escuro \n\n"
            ),
            Planet(
                R.drawable.saturno,
                "Saturno",
                "Saturno é o sexto planeta a partir do Sol, e o segundo maior do sistema solar. O primeiro é Júpiter. É conhecido pelo complexo sistema de anéis formados principalmente por gelo e poeira cósmica e possui 53 luas conhecidas e outras nove em pesquisa.",
                "•\tDistância do Sol: 1.429.400.000 km\n\n" +
                        "•\tVelocidade orbital média: 9,67 km/s \n\n" +
                        "•\tDiâmetro equatorial: 120.536 km;\n\n" +
                        "•\tÁrea da superfície: 4,38×10^10 km².\n\n" +
                        "•\tMassa: 5,688×10^26 kg\n\n" +
                        "•\tTemperatura de superfície: -125 ºC;\n\n" +
                        "•\tTranslação: 29,4 anos\n\n" +
                        "•\tRotação: \n\n",
                "•\tÉ um planeta extremamente frio (para os padrões da Terra). Lá, a temperatura média é de 170ºC negativos.\n\n" +
                        "•\t A distância entre o planeta Saturno e a Terra é de 1,657 bilhão de quilômetros.\n\n" +
                        "•\tO interior de Saturno é composto por rochas, gelos e uma camada de hidrogênio metálico.\n\n" +
                        "•\tOs ventos em Saturno podem atingir a velocidade de quase 2.000 km/h.\n\n" +
                        "•\tEste planeta possui um formato esferoide com achatamento nas regiões polares.\n\n",
                "Crateras de impacto revelam detalhes do desgaste dinâmico da superfície de Titã(Lua de Saturno)\n\n"
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
                "•\tEm 2011 A New Horizons passou pela órbita de Urano no seu caminho para Plutão, sendo a primeira nave que passou da órbita de Urano desde a Voyager 2.)\n\n",

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

                "•\t2013: Estudando uma imagem feito pelo Hubble foi descoberta o 14º satélite.\n\n" +
                        "•\t2016: Estudando mais imagens do Hubble, foi descoberto um Vortex escuro que se acredita ser a primeiro novo vortex atmosférico visto no século 21.\n"
            )
        )
    }
}