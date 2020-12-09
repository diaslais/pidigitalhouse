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
                "O Sol (do latim sol, solis[12]) é a estrela central do Sistema Solar. Todos os outros corpos do Sistema Solar, como planetas, planetas anões, asteroides, cometas e poeira, bem como todos os satélites associados a estes corpos, giram ao seu redor. Responsável por 99,86% da massa do Sistema Solar, o Sol possui uma massa 332 900 vezes maior que a da Terra, e um volume 1 300 000 vezes maior que o do nosso planeta.[13] A distância da Terra ao Sol é cerca de 150 milhões de quilômetros ou 1 unidade astronômica (UA). Esta distância varia com o ano de um mínimo de 147,1 milhões de quilômetros (0,9833 UA) no perélio (ou periélio) a um máximo de 152,1 milhões de quilômetros (1,017 UA) no afélio, em torno de 4 de julho.[14] A luz solar demora aproximadamente 8 minutos e 18 segundos para chegar à Terra. Energia do Sol na forma de luz solar é armazenada em glicose por organismos vivos através da fotossíntese, processo do qual, direta ou indiretamente, dependem todos os seres vivos que habitam nosso planeta.[15] A energia solar também é responsável pelos fenômenos meteorológicos e o clima na Terra.",
                "•\tSol sol sol sol sol sol;\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",
                "•\tSol Sol Sol Sol Sol Sol Sol Sol Sol Sol Sol Sol Sol Sol.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",
                "Sol sol sol sol sol\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"),
            Planet(
                R.drawable.mercurio,
                "Mercúrio",
                "Mercúrio - o menor planeta de nosso sistema solar e mais próximo do Sol - é apenas ligeiramente maior que a Lua da Terra. Mercúrio é o planeta mais rápido, girando em torno do Sol a cada 88 dias terrestres.",
                "•\tDistância do Sol/ Distância da órbita : 0.39 AU (unidades astronômicas : distância entre o Sol e a Terra), equivalente a aproximadamente 58 milhões de quilômetros;\n" +
                        "•\tDuração do ano/Período de órbita: 88 dias terrestres;\n" +
                        "•\tTempo para incidência da luz solar: aproximadamente 3 minutos e 47 segundos;\n" +
                        "•\tLuas: nenhuma;" +
                        "•\tDiâmetro:  4879 km;\n" +
                        "•\tMassa: 3,30 x 1023 kg (5,5% da massa da Terra);\n" +
                        "•\tTemperatura da superfície: podem chegar a 430 °C de dia e cair para –180 °C à noite;\n" +
                        "•\tMassa: 3,30 x 1023 kg (5,5% da massa da Terra)\n" +
                        "•\tPrimeiro registro: Mercúrio é conhecido pelo menos desde o tempo dos Sumérios, por volta de 5000 anos atrás;\n" +
                        "•\tIdade: 4,6 bilhôes de anos ;\n",
                "•\tO planeta recebeu esse nome em homenagem a Mercúrio, deus romano dos viajantes e do comércio e dos ladrões, sendo também o mensageiro de Zeus e a personificação da inteligência, esperteza;\n" +
                        "•\tVisto da superfície de Mercúrio, o Sol parece três vezes maior do que quando visto da Terra, e a luz do sol é até sete vezes mais brilhante. Apesar de sua proximidade com o Sol, Mercúrio não é o planeta mais quente do nosso sistema solar - esse título pertence a Vênus, graças à sua densa atmosfera;\n" +
                        "•\tO dia em mercúrio dura 59 dias terrestres e um ciclo dia-noite completo dura 175,97 dias terrestres;\n" +
                        "•\tPor conta de sua órbita elíptica e rotação lenta, o Sol da manhã parece nascer brevemente, se pôr e nascer novamente em algumas partes da superfície do planeta. A mesma coisa acontece, porém ao contrário, ao pôr do sol;\n" +
                        "•\tMercúrio é um planeta rochoso, também conhecido como planeta terrestre. Ele tem uma superfície sólida com crateras, muito parecida com a lua da Terra;\n" +
                        "•\tNão foram encontradas evidências de vida em Mercúrio;\n" +
                        "•\tA sonda  Mariner 10 da NASA foi a primeira enviada em missão para explorar este planeta. A sonda MESSENGER da NASA foi a primeira a orbitar o planeta;\n" +
                        "•\tA fina atmosfera de mercúrio, ou exosfera, é composta principalmente de oxigênio (O2), sódio (Na), hidrogênio (H2), hélio (He) e potássio (K);\n" +
                        "•\tMercúrio possui água em forma de gelo em crateras polares;\n",
                "•\tDuas naves espaciais da missão BepiColombo da ESA-JAXA (missão conjunta entre a Agência Espacial Europeia e a Agência Japonesa de Exploração Aeroespacial) estão a caminho de Mercúrio para estudá-lo;"),
            Planet(
                R.drawable.venus,
                "Vênus",
                "Vénus ou Vênus é o segundo planeta do Sistema Solar em ordem de distância a partir do Sol, orbitando-o a cada 224,7 dias. Recebeu seu nome em homenagem à deusa romana do amor e da beleza Vénus, equivalente a Afrodite. Depois da Lua, é o objeto mais brilhante do céu noturno, atingindo uma magnitude aparente de -4,6, o suficiente para produzir sombras.",
                "•\tVênus vênus vênus vênus vênus vênus vênus vênus vênus vênus vênus;\n" +
                        "•\tMassa: 4,87 x 10 ^ 24 kg (81,5% do tamanho da Terra);\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",
                "•\tVênus vênus vênus vênus vênus vênus vênus vênus vênus vênus vênus.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",
                "Vênus vênus vênus vênus vênus vênus vênus vênus vênus vênus vênus\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"),
            Planet(
                R.drawable.terra,
                "Terra",
                "A Terra é o terceiro planeta mais próximo do Sol, o mais denso e o quinto maior dos oito planetas do Sistema Solar. É também o maior dos quatro planetas telúricos. É por vezes designada como Mundo ou Planeta Azul. Lar de milhões de espécies de seres vivos, incluindo os humanos, a Terra é o único corpo celeste onde é conhecida a existência de vida.",
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
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"),
            Planet(
                R.drawable.marte,
                "Marte",
                "Quarto planeta do Sistema Solar, Marte é um mundo empoeirado, frio e desértico com uma atmosfera muito fina. \n" +
                        "Foi batizado pelos antigos romanos em homenagem ao deus da guerra (Marte), pois sua cor avermelhada lembrava sangue.\n",
                "•\tDistância do Sol/ Distância da órbita:  1.52 AU, o equivalente a 228 milhões de quilômetros.;\n" +
                        "•\tDuração do ano/Período de órbita: 687 dias terrestres;\n" +
                        "•\tTempo para incidência da luz solar: aproximadamente 12 minutos;\n" +
                        "•\tLuas: Marte possui 2 luas, Fobos e Deimos.;\n" +
                        "•\tDiâmetro:  6.792 km;\n" +
                        "•\tMassa: 6,417 x 1023 kg (10,7% da massa da Terra);\n" +
                        "•\tTemperatura da superfície:  20°C a -153°C;\n" +
                        "•\tPrimeiro registro:  Já era conhecido pelos antigos. Registros egípcios já citavam Marte, há mais de 4.000 anos.;\n" +
                        "•\tIdade: 4,6 bilhôes de anos.\n",
                "•\tO planeta recebeu esse nome em homenagem a Marte, deus romano da guerra;\n" +
                        "•\tMarte é conhecido como o Planeta Vermelho porque os minerais de ferro do solo marciano se oxidam, ou enferrujam, fazendo com que o solo e a atmosfera pareçam vermelhos;\n" +
                        "•\tMarte possui estações, calotas polares, cânions e vulcões extintos, além de evidências de que foi muito mais ativo no passado;\n" +
                        "•\tMarte possui a maior montanha do Sistema Solar, chamada de Monte Olimpo;\n" +
                        "•\tMarte é um planeta facilmente visível a olho nu;\n" +
                        "•\tUm dia em Marte leva pouco mais de 24 horas (24 horas e 39 minutos);\n" +
                        "•\tMarte tem uma atmosfera fina composta principalmente de dióxido de carbono (CO2), argônio (Ar), nitrogênio (N2) e uma pequena quantidade de oxigênio e vapor de água;\n" +
                        "•\tVárias missões visitaram este planeta, desde sondas “flybys” (sondas que passam próximo a um astro e o analisam sem entrar em órbita)  e orbitadores a rovers na superfície. O primeiro verdadeiro sucesso da missão a Marte foi o da sonda Mariner 4 em 1965;\n" +
                        "•\tNeste momento, a superfície de Marte não pode suportar a vida como a conhecemos. As missões atuais estão determinando o potencial passado e futuro de Marte para a vida;\n" +
                        "•\tMarte é um dos corpos mais explorados em nosso sistema solar e é o único planeta para onde enviamos rovers para percorrer a paisagem alienígena ( Sojourner(1997), Spirit e Opportunity(2003), Curiosity(2011), Perseverance(2020));\n" +
                        "•\tA NASA atualmente tem três espaçonaves em órbita, um rover e um módulo de pouso na superfície;\n" +
                        "•\tA Índia e a Agência Espacial Europeia possuem espaçonaves em órbita acima de Marte. Esses exploradores encontraram diversas evidências de que Marte era muito mais quente e úmido e possuía uma atmosfera mais densa há bilhões de anos atrás;\n" +
                        "•\tMarte possui um lugar especial na cultura popular, servindo de inspiração para gerações de escritores de ficção científica como por exemplo H.G.Wells. Seu romance “A Guerra dos Mundos”, teve uma produção para rádio feita por Orson Welles,em 30 de outubro de 1938, que chegou a convencer milhares de ouvintes que extraterrestres vindos de Marte estavam invadindo a Terra, causando um pânico generalizado;\n" +
                        "•\t Vários filmes também têm histórias que se passam em Marte como por exemplo, “O Vingador do Futuro” (1990 e 2012) e “Perdido em Marte” (2015).\n",
                "•\tA NASA lançou a próxima geração de rovers, o Perseverance  para Marte em 30 de julho de 2020;\n" +
                        "•\tAgência Espacial Europeia anunciou a descoberta de novos reservatórios água em estado líquido em Marte;\n" +
                        "•\tUm novo estudo aborda uma maneira de se produzir H2 e O2 em Marte a partir dos reservatórios de salmoura encontrados;\n" +
                        "•\tElon Musk, CEO da SpaceX  anuncia que espera levar pessoas a Marte até 2026 e criar uma colônia de Humanos até 2050."),
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
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"),
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
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"),
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
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"),
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
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide")
        )
    }
}