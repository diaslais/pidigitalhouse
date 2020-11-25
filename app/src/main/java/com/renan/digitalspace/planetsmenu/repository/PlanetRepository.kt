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
                "O Sol (do latim sol, solis) é a estrela central do Sistema Solar. Todos os outros corpos do Sistema Solar, como planetas, planetas anões, asteroides, cometas e poeira, bem como todos os satélites associados a estes corpos, giram ao seu redor.",
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
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"
            ),
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
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"
            ),
            Planet(
                R.drawable.venus,
                "Vênus",
                "Vênus vênus vênus vênus vênus vênus vênus vênus vênus vênus vênus.",
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
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"
            ),
            Planet(
                R.drawable.terra,
                "Terra",
                "A Terra é o terceiro planeta mais próximo do Sol, o mais denso e o quinto maior dos oito planetas do Sistema Solar. É também o maior dos quatro planetas telúricos. É por vezes designada como Mundo ou Planeta Azul.",
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
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"
            ),
            Planet(
                R.drawable.jupiter,
                "Júpiter",
                "Júpiter é o maior planeta do Sistema Solar, tanto em diâmetro quanto em massa, e é o quinto mais próximo do Sol.[11] Possui menos de um milésimo da massa solar, contudo tem 2,5 vezes a massa de todos os outros planetas em conjunto. É um planeta gasoso, junto com Saturno, Urano e Netuno.",
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
                "Saturno é o sexto planeta a partir do Sol e o segundo maior do Sistema Solar atrás de Júpiter. Pertencente ao grupo dos gigantes gasosos, possui cerca de 95 massas terrestres e orbita a uma distância média de 9,5 unidades astronômicas.",
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
                "Urano[11] (Úrano em Portugal[12]) é o sétimo planeta a partir do Sol, o terceiro maior e o quarto mais massivo dos oito planetas do Sistema Solar. Foi nomeado em homenagem ao deus grego do céu, Urano, o pai de Cronos (Saturno) e o avô de Zeus (Júpiter).",
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
                "Netuno (pt-BR) ou Neptuno (pt) (AO 1990: Netuno ou Neptuno)[2][3] é o oitavo planeta do Sistema Solar, o último a partir do Sol desde a reclassificação de Plutão para a categoria de planeta anão, em 2006. Pertencente ao grupo dos gigantes gasosos, possui um tamanho ligeiramente menor que o de Urano, mas maior massa, equivalente a 17 massas terrestres. Netuno orbita o Sol a uma distância média de 30,1 unidades astronômicas. O planeta é formado por um pequeno núcleo rochoso ao redor do qual encontra -se uma camada formada possivelmente por água, amônia e metano sobre a qual situa - se sua turbulenta atmosfera, constituída predominantemente de hidrogênio e hélio.",
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