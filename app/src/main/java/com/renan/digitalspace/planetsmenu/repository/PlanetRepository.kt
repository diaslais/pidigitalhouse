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
                "•\tA composição do Sol é de 74% de hidrogênio e 24% de hélio, sendo o percentual restante formado principalmente por oxigênio, carbono e ferro.\n" +
                        "•\tToda a energia produzida pelo Sol é proveniente do processo de fusão nuclear decorrente das grandes temperaturas de seu núcleo (cerca de 15 milhões de kelvin) e de sua enorme pressão.\n" +
                        "•\tAo todo, o Sol consome cerca de 4 milhões de toneladas de sua massa por segundo, uma taxa mais do que suficiente para mantê-lo brilhando pelos próximos 6 ou 7 bilhões de anos, devido à sua grande massa, que é de aproximadamente 1,98.1031 kg, mais de 330 mil vezes a massa da Terra.\n" +
                        "•\tPor conta de sua enorme massa, a gravidade na superfície do Sol chega a 274 m/s², 27,4 vezes maior que a massa da Terra. Isso faz com que a velocidade de escape por lá chegue aos 617 km/s, mais de 2 milhões de quilômetros por hora.\n" +
                        "•\tO período de rotação do Sol em torno do seu próprio eixo é de 27 dias para o seu equador, que gira a 7189 km/h, e de 35 dias para os seus polos. Essa diferença de período rotacional produz uma rotação diferencial (chamada de dínamo solar), responsável por sua grande atividade magnética, uma vez que toda a matéria presente na estrela encontra-se ionizada (no estado plasmático), dando origem às tempestades solares, erupções coronárias e manchas solares.\n",

                "•\tUma viagem da Terra com destino ao Sol, em uma espaçonave, levaria cerca de 124 dias.\n" +
                        "•\tA quantidade de energia liberada pelo Sol em um segundo é superior a produzida pelo ser humano em toda a história.\n" +
                        "•\tSua temperatura ultrapassa 5.500 C. Calcula-se que em seu centro, o Sol tenha uma temperatura de 15 milhões de graus centígrados.\n" +
                        "•\tO Sol possui 99,86% de toda a massa do Sistema Solar, e é 300 mil vezes mais pesado do que a Terra.\n" +
                        "•\tUma pessoa de 70 quilos pesaria cerca de 1.900 quilos no Sol.\n" +
                        "•\tA luz do Sol demora 8 minutos para chegar na Terra. Isso significa que se o Sol explodisse agora, nós só perceberíamos daqui a 8 minutos.\n" +
                        "•\tA cada segundo de brilho, o Sol libera uma quantidade de energia equivalente a 1 milhão de bombas de hidrogênio.\n" +
                        "•\tSe o Sol tivesse apenas um centímetro de diâmetro, a estrela mais próxima estaria a 285 quilômetros de distância.\n",

                "Atualmente a raça humana estuda o sol por meio da sonda Parker, uma sonda  com revestimento de carbono de 11 centímetros de grossura que envolve a máquina. Por causa dele, a sonda é capaz de enfrentar temperaturas de quase 1,4 mil graus Celsius — muito inferiores  às da superfície solar, mas suficientes para orbitar o astro. O maior avanço obtido pela sonda até agora foi relativo ao vento solar, nome dado a uma corrente de partículas energizadas que são emitidas pelo Sol e flutuam pelo espaço. A Parker Probe capturou a melhor visão que se tem até o momento do local de nascimento do vento solar, na superfície do astro.\n Consulte na íntegra: "
            ),
            Planet(
                R.drawable.mercurio,
                "Mercúrio",
                "Mercúrio - o menor planeta de nosso sistema solar e mais próximo do Sol - é apenas ligeiramente maior que a Lua da Terra. Mercúrio é o planeta mais rápido, girando em torno do Sol a cada 88 dias terrestres.Visto da superfície de Mercúrio, o Sol parece três vezes maior do que quando visto da Terra, e a luz do sol é até sete vezes mais brilhante. Apesar de sua proximidade com o Sol, Mercúrio não é o planeta mais quente do nosso sistema solar - esse título pertence a Vênus, graças à sua densa atmosfera.",
                "•\tDistância do Sol/ Distância da órbita : 0.39 AU (unidades astronômicas : distância entre o Sol e a Terra), equivalente a aproximadamente 58 milhões de quilômetros.\n" +
                        "•\tDuração do ano/Período de órbita: 88 dias terrestres.\n" +
                        "•\tTempo para incidência da luz solar: aproximadamente 3 minutos e 47 segundos.\n" +
                        "•\tLuas: Mercúrio não possui nenhuma lua.\n" +
                        "•\tAnéis: Mercúrio não possui nenhum anel ao seu redor.\n" +
                        "•\tDiâmetro:  4879 km\n" +
                        "•\tMassa: 3,30 x 1023 kg (5,5% da massa da Terra)\n" +
                        "•\tTemperatura da superfície: podem chegar a 430 °C de dia e cair para –180 °C à noite.\n" +
                        "•\tPrimeiro registro: Mercúrio é conhecido pelo menos desde o tempo dos Sumérios, por volta de 5000 anos atrás.\n\n",

                "•\tO planeta recebeu esse nome em homenagem a Mercúrio, deus romano dos viajantes e do comércio e dos ladrões, sendo também o mensageiro de Zeus e a personificação da inteligência, esperteza.\n" +
                        "•\tO dia em mercúrio dura 59 dias terrestres e um ciclo dia-noite completo dura 175,97 dias terrestres.\n" +
                        "•\tPor causa da órbita elíptica e rotação lenta, o Sol da manhã parece nascer brevemente, se pôr e nascer novamente em algumas partes da superfície do planeta. A mesma coisa acontece, porém ao contrário, ao pôr do sol.\n\n" +
                        "•\tMercúrio é um planeta rochoso, também conhecido como planeta terrestre. Ele tem uma superfície sólida com crateras, muito parecida com a lua da Terra.\n\n" +
                        "•\tNão foram encontradas evidências de vida em Mercúrio.\n" +
                        "•\tA sonda  Mariner 10 da NASA foi a primeira enviada em missão para explorar este planeta. A sonda MESSENGER da NASA foi a primeira a orbitar o planeta. \n\n" +
                        "•\tA fina atmosfera de mercúrio, ou exosfera, é composta principalmente de oxigênio (O2), sódio (Na), hidrogênio (H2), hélio (He) e potássio (K).\n" +
                        "•\tMercúrio possui água em forma de gelo em crateras polares.\n",

                "•\tDuas naves espaciais da missão BepiColombo da ESA-JAXA (missão conjunta entre a Agência Espacial Europeia e a Agência Japonesa de Exploração Aeroespacial) estão a caminho de Mercúrio para estudá-lo.\n\n",
            ),

            Planet(
                R.drawable.venus,
                "Vênus",
                "Vênus é o segundo planeta mais próximo ao Sol e o segundo objeto mais brilhante no céu noturno, atrás apenas da lua. Devido às muitas semelhanças com nosso planeta, Vênus também é considerado um planeta irmão da Terra, por conta de sua massa densidade e volume, mas apesar disso, ele tem características únicas e impressionantes.",
                "•\tDiâmetro: 12.104 km;\n" +
                        "•\tMassa: 4,87 x 10 ^ 24 kg (81,5% do tamanho da Terra);\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",

                "•\tVênus é o planeta mais próximo da Terra.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",

                "Cientistas acreditam na descoberta de vida em Vênus\n",
            ),
            Planet(
                R.drawable.terra,
                "Terra",
                "A Terra é o terceiro planeta mais próximo do sol, o mais denso e o quinto maior dos oito planetas do sistema solar. É também o maior dos quatro planetas telúricos. É por vezes designada como Mundo ou Planeta Azul. Lar de milhões de espécies de seres vivos, incluindo os humanos, a Terra é o único corpo celeste onde é conhecida a existência de vida.\n",

                "•\tDiâmetro: 12,104 km;\n" +
                        "•\tDistância do Sol: 149.600.000 km\n" +
                        "•\tVelocidade orbital média: 29,69 km/s\n" +
                        "•\tDiâmetro equatorial: 12.756,3 km\n" +
                        "•\tÁrea da superfície: 5,10072×10 8 km²\n" +
                        "•\tMassa: 5,9742×10 24 kg\n" +
                        "•\tTemperatura à superfície: 15 ºC\n" +
                        "•\tTranslação: 365,2564 dias\n" +
                        "•\tRotação: 23,9345 horas",

                "•\tVênus é o planeta mais próximo da Terra.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",

                "Vênus é um planeta da Rússia (segundo os russos)\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"
            ),
            Planet(
                R.drawable.marte,
                "Marte",

                "Marte é o quarto planeta a partir do Sol, o segundo menor do Sistema Solar. Batizado em homenagem ao deus romano da guerra, muitas vezes é descrito como o \"Planeta Vermelho\", porque o óxido de ferro predominante em sua superfície lhe dá uma aparência avermelhada.",
                "•\tDistância do Sol/ Distância da órbita:  1.52 AU, o equivalente a 228 milhões de quilômetros.\n" +
                        "•\tDuração do ano/Período de órbita: 687 dias terrestres\n" +
                        "•\tTempo para incidência da luz solar: aproximadamente 12 minutos.\n" +
                        "•\tLuas: Marte possui 2 luas, Fobos e Deimos.\n\n" +
                        "•\tDiâmetro:  6.792 km\n" +
                        "•\tMassa: 6,417 x 1023 kg (10,7% da massa da Terra)\n" +
                        "•\tTemperatura da superfície:  20°C a -153°C\n" +
                        "•\tPrimeiro registro:  Já era conhecido pelos antigos. Registros egípcios já citavam Marte, há mais de 4.000 anos.\n"+
                        "•\tIdade: 4,6 bilhôes de anos\n",

                "•\tO planeta recebeu esse nome em homenagem a Marte, deus romano da guerra.\n" +
                        "•\tMarte é conhecido como o Planeta Vermelho porque os minerais de ferro do solo marciano se oxidam, ou enferrujam, fazendo com que o solo e a atmosfera pareçam vermelhos.\n" +
                        "•\tMarte possui estações, calotas polares, cânions e vulcões extintos, além de evidências de que foi muito mais ativo no passado.\n" +
                        "•\tMarte possui a maior montanha do Sistema Solar, chamada de Monte Olimpo.\n" +
                        "•\tMarte é um planeta facilmente visível a olho nu.\n" +
                        "•\tUm dia em Marte leva pouco mais de 24 horas (24 horas e 39 minutos).\n" +
                        "•\tMarte tem uma atmosfera fina composta principalmente de dióxido de carbono (CO2), argônio (Ar), nitrogênio (N2) e uma pequena quantidade de oxigênio e vapor de água.\n" +
                        "•\tVárias missões visitaram este planeta, desde sondas “flybys” (sondas que passam próximo a um astro e o analisam sem entrar em órbita)  e orbitadores a rovers na superfície. O primeiro verdadeiro sucesso da missão a Marte foi o da sonda Mariner 4 em 1965.\n" +
                        "•\tNeste momento, a superfície de Marte não pode suportar a vida como a conhecemos. As missões atuais estão determinando o potencial passado e futuro de Marte para a vida.\n" +
                "•\tMarte é um dos corpos mais explorados em nosso sistema solar e é o único planeta para onde enviamos rovers para percorrer a paisagem alienígena ( Sojourner(1997), Spirit e Opportunity(2003), Curiosity(2011), Perseverance(2020)).\n",
                "Vênus é um planeta da Rússia (segundo os russos)\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"
            ),

            Planet(
                R.drawable.jupiter,
                "Júpiter",
                "Júpiter é o maior planeta do Sistema Solar, tanto em diâmetro quanto em massa, e é o quinto mais próximo do Sol.[11] Possui menos de um milésimo da massa solar, contudo tem 2,5 vezes a massa de todos os outros planetas em conjunto. É um planeta gasoso, junto com Saturno, Urano e Netuno. Estes quatro planetas são por vezes chamados de planetas jupiterianos ou planetas jovianos, e são os quatro gigantes gasosos, isto é, que não são compostos primariamente de matéria sólida.",
                "•\tDiâmetro: 12,104 km;\n" +
                        "•\tMassa: 4,87 x 10 ^ 24 kg (81,5% do tamanho da Terra);\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",
                "•\tVênus é o planeta mais próximo da Terra.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",
                "Vênus é um planeta da Rússia (segundo os russos)\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"
            ),
            Planet(
                R.drawable.saturno,
                "Saturno",
                "Saturno é o sexto planeta a partir do Sol e o segundo maior do Sistema Solar atrás de Júpiter. Pertencente ao grupo dos gigantes gasosos, possui cerca de 95 massas terrestres e orbita a uma distância média de 9,5 unidades astronômicas. Possui um pequeno núcleo rochoso, circundado por uma espessa camada de hidrogênio metálico e hélio.",
                "•\tDiâmetro: 12,104 km;\n" +
                        "•\tMassa: 4,87 x 10 ^ 24 kg (81,5% do tamanho da Terra);\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",
                "•\tVênus é o planeta mais próximo da Terra.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",
                "Vênus é um planeta da Rússia (segundo os russos)\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"
            ),
            Planet(
                R.drawable.urano,
                "Urano",
                "Urano é o sétimo planeta a partir do Sol, o terceiro maior e o quarto mais massivo dos oito planetas do Sistema Solar. Foi nomeado em homenagem ao deus grego do céu, Urano, o pai de Cronos e o avô de Zeus.",
                "•\tDiâmetro: 12,104 km;\n" +
                        "•\tMassa: 4,87 x 10 ^ 24 kg (81,5% do tamanho da Terra);\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",
                "•\tVênus é o planeta mais próximo da Terra.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",
                "Vênus é um planeta da Rússia (segundo os russos)\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"
            ),
            Planet(
                R.drawable.netuno,
                "Netuno",
                "Netuno ⁽ᵖᵗ⁻ᴮᴿ⁾ ou Neptuno ⁽ᵖᵗ⁾ é o oitavo planeta do Sistema Solar, o último a partir do Sol desde a reclassificação de Plutão para a categoria de planeta anão, em 2006. Pertencente ao grupo dos gigantes gasosos, possui um tamanho ligeiramente menor que o de Urano, mas maior massa, equivalente a 17 massas terrestres.",
                "•\tDiâmetro: 12,104 km;\n" +
                        "•\tMassa: 4,87 x 10 ^ 24 kg (81,5% do tamanho da Terra);\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",
                "•\tVênus é o planeta mais próximo da Terra.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",
                "Vênus é um planeta da Rússia (segundo os russos)\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"
            )
        )
    }
}