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
                "Mercúrio é o menor e mais interno planeta do Sistema Solar, orbitando o Sol a cada 87,969 dias terrestres. A sua órbita tem a maior excentricidade e o seu eixo apresenta a menor inclinação em relação ao plano da órbita dentre todos os planetas do Sistema Solar.",
                "•\tMercúrio mercúrio mercúrio mercúrio mercúrio mercúrio;\n" +
                        "•\tMassa: 4,87 x 10 ^ 24 kg (81,5% do tamanho da Terra);\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",
                "•\tmercúrio mercúrio mercúrio mercúrio mercúrio mercúrio mercúrio.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",
                "mercúrio mercúrio mercúrio mercúrio mercúrio mercúrio\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"),
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
                "Marte é o quarto planeta a partir do Sol, o segundo menor do Sistema Solar. Batizado em homenagem ao deus romano da guerra, muitas vezes é descrito como o \"Planeta Vermelho\", porque o óxido de ferro predominante em sua superfície lhe dá uma aparência avermelhada.",
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